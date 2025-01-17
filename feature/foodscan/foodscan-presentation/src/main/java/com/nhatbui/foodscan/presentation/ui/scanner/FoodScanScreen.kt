package com.nhatbui.foodscan.presentation.ui.scanner

import android.content.Context
import android.net.Uri
import androidx.activity.compose.ManagedActivityResultLauncher
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.result.contract.ActivityResultContracts.RequestPermission
import androidx.camera.view.LifecycleCameraController
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.ripple
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawWithContent
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.BlendMode
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.CompositingStrategy
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.LocalLifecycleOwner
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.nhatbui.common.ui.HeaderBar
import com.nhatbui.foodscan.presentation.R
import com.nhatbui.foodscan.presentation.presentation.FoodScanViewModel
import com.nhatbui.foodscan.presentation.presentation.model.ScanState
import com.nhatbui.foodscan.presentation.ui.scanner.component.AnimatedScanner
import com.nhatbui.foodscan.presentation.ui.scanner.component.CameraPreview
import com.nhatbui.foodscan.presentation.ui.scanner.component.FoodScanGuide
import com.nhatbui.foodscan.presentation.ui.scanner.component.ScannerLoadingModal
import com.nhatbui.foodscan.presentation.ui.scanner.util.CameraUtil
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors

private const val IMAGE_MEDIA_TYPE = "image/*"

@Composable
fun FoodScanScreen(
    onNavigateBack: () -> Unit,
    onNavigateToResult: (uri: String) -> Unit
) {
    val viewModel = hiltViewModel<FoodScanViewModel>()
    val viewState by viewModel.viewState.collectAsStateWithLifecycle()

    var shouldShowCameraPreview by remember { mutableStateOf(false) }
    var capturedImageUri by remember { mutableStateOf<Uri?>(null) }

    val onPermissionResult: (Boolean) -> Unit = { isGranted ->
        if (isGranted) {
            shouldShowCameraPreview = true
        } else {
            onNavigateBack()
        }
    }
    val permissionLauncher = rememberLauncherForActivityResult(
        RequestPermission()
    ) { granted ->
        onPermissionResult(granted)
    }
    val galleryLauncher = rememberLauncherForActivityResult(
        ActivityResultContracts.GetContent()
    ) { uri ->
        uri?.let { capturedImageUri = it }
    }

    LaunchedEffect(capturedImageUri) {
        capturedImageUri?.let {
            viewModel.onScanningFood()
        }
    }
    LaunchedEffect(viewState.scanState) {
        when (viewState.scanState) {
            ScanState.IsScanning -> {
                viewModel.getFoodScanProgress()
            }

            ScanState.ScanCompleted -> {
                onNavigateToResult(capturedImageUri.toString())
            }

            else -> {}
        }
    }
    LaunchedEffect(Unit) {
        permissionLauncher.launch(android.Manifest.permission.CAMERA)
    }

    val context = LocalContext.current
    val lifecycleOwner = LocalLifecycleOwner.current
    val executor = remember { Executors.newSingleThreadExecutor() }
    val cameraController = remember {
        LifecycleCameraController(context).apply {
            bindToLifecycle(lifecycleOwner)
        }
    }

    var scannerSize by remember { mutableStateOf<Size?>(null) }
    var scannerPositionInRoot by remember { mutableStateOf<Offset?>(null) }
    val localDensity = LocalDensity.current
    val scannerRadiusInPx = remember { with(localDensity) { 20.dp.toPx() } }

    AnimatedVisibility(viewState.scanState is ScanState.IsScanning) {
        ScannerLoadingModal(
            modifier = Modifier.fillMaxSize(),
            progressPercentage = viewState.scanProgressPercentage
        )
    }
    AnimatedVisibility(viewState.scanState is ScanState.NotScanning) {
        if (shouldShowCameraPreview) {
            CameraPreview(cameraController)
        }
        FocusLayer(scannerSize, scannerPositionInRoot, scannerRadiusInPx)
        Column(
            modifier = Modifier.fillMaxSize()
        ) {
            HeaderBar(onNavigateBack = onNavigateBack)
            Spacer(Modifier.height(16.dp))
            FoodScanGuide(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 20.dp)
            )
            Spacer(Modifier.height(48.dp))
            AnimatedScanner(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 36.dp),
                onPositioned = { size, position ->
                    scannerSize = size
                    scannerPositionInRoot = position
                }
            )
            Spacer(Modifier.weight(1f))
            Row(
                modifier = Modifier.padding(20.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                GalleryButton(galleryLauncher)
                Column(
                    modifier = Modifier.weight(1f),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    CameraCaptureButton(cameraController, context, executor) { uri ->
                        capturedImageUri = uri
                    }
                }
                Spacer(Modifier.size(40.dp))
            }
        }
    }
}

@Composable
private fun CameraCaptureButton(
    cameraController: LifecycleCameraController,
    context: Context,
    executor: ExecutorService,
    onCaptureImage: (Uri) -> Unit
) {
    Box(
        modifier = Modifier
            .size(64.dp)
            .background(Color(0xFF66BB6A), CircleShape)
            .clickable(
                interactionSource = remember { MutableInteractionSource() },
                indication = ripple()
            ) {
                CameraUtil.takePicture(cameraController, context, executor) { uri ->
                    onCaptureImage(uri)
                }
            }
            .padding(8.dp)
            .background(Color(0xFFFFFFFF), CircleShape)
    )
}

@Composable
private fun GalleryButton(galleryLauncher: ManagedActivityResultLauncher<String, Uri?>) {
    IconButton(
        onClick = {
            galleryLauncher.launch(IMAGE_MEDIA_TYPE)
        },
        modifier = Modifier
            .size(40.dp)
            .background(Color(0x1FFFFFFF), CircleShape)
    ) {
        Icon(
            modifier = Modifier.size(24.dp),
            painter = painterResource(R.drawable.ic_gallery),
            tint = Color.White,
            contentDescription = null
        )
    }
}

@Composable
private fun FocusLayer(
    scannerSize: Size?,
    scannerPositionInRoot: Offset?,
    scannerRadiusInPx: Float
) {
    if (scannerSize != null && scannerPositionInRoot != null) {
        Box(modifier = Modifier
            .fillMaxSize()
            .graphicsLayer {
                compositingStrategy = CompositingStrategy.Offscreen
            }
            .drawWithContent {
                drawContent()
                drawRoundRect(
                    color = Color(0xFFFFFFFF),
                    topLeft = scannerPositionInRoot,
                    size = scannerSize,
                    cornerRadius = CornerRadius(scannerRadiusInPx, scannerRadiusInPx),
                    blendMode = BlendMode.DstOut
                )
            }
        ) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(Color(0x66141414))
            )
        }
    }
}
