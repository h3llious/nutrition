package com.nhatbui.foodscan.presentation.ui.scanner.component

import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.InfiniteTransition
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ProgressIndicatorDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.PathEffect
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.graphics.drawscope.rotate
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.nhatbui.foodscan.presentation.R

private const val ANIMATE_DURATION = 5000

@Composable
fun ScannerLoadingModal(
    progressPercentage: Int,
    modifier: Modifier = Modifier
) {
    val infiniteTransition = rememberInfiniteTransition(label = "loadingTransition")
    val angle by circularLoadingAngleTransition(infiniteTransition)

    Box(
        modifier = modifier.padding(horizontal = 28.dp),
        contentAlignment = Alignment.Center
    ) {
        Column(
            modifier = Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            CircularLoadingSpinner(angle)
            Spacer(modifier = Modifier.height(48.dp))
            Text(
                style = MaterialTheme.typography.labelMedium,
                textAlign = TextAlign.Center,
                text = stringResource(R.string.food_scan_in_progress_label)
            )
            Spacer(modifier = Modifier.height(12.dp))
            ScanningProgressIndicator(progressPercentage)
        }
    }
}

@Composable
private fun CircularLoadingSpinner(angle: Float) {
    Box(
        Modifier
            .size(144.dp)
            .dashedCircular(
                brush = Brush.sweepGradient(
                    colors = listOf(
                        Color(0x1AFFA726),
                        Color(0xFF66BB6A)
                    )
                ),
                strokeWidth = 24.dp,
                dashLength = 5.dp,
                gapLength = 8.dp,
                rotateAngle = angle
            )
    )
}

@Composable
private fun ScanningProgressIndicator(progressPercentage: Int) {
    val animatedProgress by animateFloatAsState(
        targetValue = progressPercentage / 100f,
        animationSpec = ProgressIndicatorDefaults.ProgressAnimationSpec,
        label = "progressAnimation"
    )
    LinearProgressIndicator(
        progress = { animatedProgress },
        modifier = Modifier
            .fillMaxWidth()
            .height(6.dp),
        trackColor = Color(0xFFEDEDED),
        color = Color(0xFF66BB6A),
        drawStopIndicator = {}
    )
    Spacer(modifier = Modifier.height(14.dp))
    Text(
        fontWeight = FontWeight.W600,
        fontSize = 20.sp,
        lineHeight = 24.sp,
        textAlign = TextAlign.Center,
        text = stringResource(R.string.food_scan_progress, progressPercentage)
    )
}

@Composable
private fun circularLoadingAngleTransition(infiniteTransition: InfiniteTransition) =
    infiniteTransition.animateFloat(
        initialValue = 0f,
        targetValue = 360f,
        animationSpec = infiniteRepeatable(
            animation = tween(ANIMATE_DURATION, easing = FastOutSlowInEasing),
            repeatMode = RepeatMode.Restart
        ),
        label = "loadingAngle"
    )

fun Modifier.dashedCircular(
    brush: Brush,
    strokeWidth: Dp,
    dashLength: Dp,
    gapLength: Dp,
    rotateAngle: Float
) = this.drawBehind {
    val dashedStroke = Stroke(
        width = strokeWidth.toPx(),
        pathEffect = PathEffect.dashPathEffect(
            intervals = floatArrayOf(dashLength.toPx(), gapLength.toPx())
        )
    )

    rotate(rotateAngle) {
        drawCircle(
            style = dashedStroke,
            brush = brush
        )
    }
}