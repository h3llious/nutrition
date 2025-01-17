package com.nhatbui.foodscan.presentation.ui.scanner.util

import android.content.Context
import android.net.Uri
import android.os.Environment
import androidx.camera.core.ImageCapture
import androidx.camera.core.ImageCaptureException
import androidx.camera.view.CameraController
import java.io.File
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale
import java.util.concurrent.ExecutorService

private const val TIME_STAMP = "yyyyMMdd_HHmmss"
private const val JPG_EXT = ".jpg"

object CameraUtil {
    fun takePicture(
        cameraController: CameraController,
        context: Context,
        executor: ExecutorService,
        onImageCaptured: (Uri) -> Unit,
    ) {
        val photoFile = createPictureFile(context)
        val outputOptions = ImageCapture.OutputFileOptions.Builder(photoFile).build()

        cameraController.takePicture(
            outputOptions,
            executor,
            object : ImageCapture.OnImageSavedCallback {
                override fun onImageSaved(outputFileResults: ImageCapture.OutputFileResults) {
                    onImageCaptured(Uri.fromFile(photoFile))
                }

                override fun onError(exception: ImageCaptureException) {
                    // Handle error
                }
            }
        )
    }

     private fun createPictureFile(context: Context): File {
        val locale = Locale.getDefault()
        val timeStamp = SimpleDateFormat(TIME_STAMP, locale).format(Date())
        val fileName = "JPEG_" + timeStamp + "_"
        val storageDir = context.getExternalFilesDir(Environment.DIRECTORY_PICTURES)
        return File.createTempFile(fileName, JPG_EXT, storageDir)
    }
}
