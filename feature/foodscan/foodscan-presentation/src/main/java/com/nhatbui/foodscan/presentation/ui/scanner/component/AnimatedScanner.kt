package com.nhatbui.foodscan.presentation.ui.scanner.component

import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.VectorConverter
import androidx.compose.animation.core.animateValue
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.keyframes
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.HorizontalDivider
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.blur
import androidx.compose.ui.draw.paint
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.layout.positionInParent
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.toSize
import com.nhatbui.foodscan.presentation.R

private const val animationDuration = 5000

@Composable
fun AnimatedScanner(
    onPositioned: (size: Size, position: Offset) -> Unit,
    modifier: Modifier = Modifier
) {
    var positionedSize by remember { mutableStateOf(Size.Zero) }
    Box(
        modifier = modifier
            .aspectRatio(1f)
            .paint(
                painter = painterResource(R.drawable.bg_side_border),
                contentScale = ContentScale.FillBounds
            )
            .onGloballyPositioned { coordinates ->
                onPositioned(coordinates.size.toSize(), coordinates.positionInParent())
                positionedSize = coordinates.size.toSize()
            },
        contentAlignment = Alignment.Center
    ) {
        val boxSize = with(LocalDensity.current) { positionedSize.height.toDp() }
        val animatedHeight by scannerAnimationTransition(boxSize)

        Box(
            modifier = Modifier
                .size(boxSize)
                .padding(16.dp),
            contentAlignment = Alignment.BottomCenter
        ) {
            Column(
                modifier = Modifier.fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                ScannerBar()
                ScannerFilterMesh(animatedHeight)
            }
        }
    }
}

@Composable
private fun scannerAnimationTransition(
    boxSize: Dp
): State<Dp> {
    val infiniteTransition = rememberInfiniteTransition(label = "scanner")
    return infiniteTransition.animateValue(
        initialValue = 0.dp,
        targetValue = boxSize - 32.dp,
        typeConverter = Dp.VectorConverter,
        animationSpec = infiniteRepeatable(
            animation = keyframes {
                durationMillis = animationDuration
                0.dp at 0 // ms
                (boxSize - 32.dp) at animationDuration / 2
                0.dp at animationDuration using FastOutSlowInEasing
            }
        ),
        label = "scannerBar"
    )
}

@Composable
private fun ScannerFilterMesh(heightAnimation: Dp) {
    Box(
        modifier = Modifier
            .height(heightAnimation)
            .fillMaxWidth()
            .background(
                brush = Brush.verticalGradient(
                    colors = listOf(
                        Color(0x9966BB6A),
                        Color(0x0066BB6A)
                    )
                )
            )
    )
}

@Composable
private fun ScannerBar(modifier: Modifier = Modifier) {
    HorizontalDivider(
        modifier = modifier
            .fillMaxWidth()
            .blur(4.dp),
        thickness = 4.dp,
        color = Color(0xFF26B62D)
    )
}
