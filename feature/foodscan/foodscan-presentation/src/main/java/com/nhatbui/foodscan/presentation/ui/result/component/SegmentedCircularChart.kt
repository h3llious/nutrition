package com.nhatbui.foodscan.presentation.ui.result.component

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.nhatbui.foodscan.presentation.ui.result.model.CircularSegmentModel

@Composable
fun SegmentedCircularChart(
    totalWeight: Int,
    segmentData: List<CircularSegmentModel>,
    modifier: Modifier = Modifier,
    startAngle: Float = -90.0f,
    endAngle: Float = 270.0f,
    strokeWidth: Dp = 4.dp,
    trackColor: Color = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.1f)
) {
    val localDensity = LocalDensity.current

    val stroke = with(localDensity) {
        Stroke(width = strokeWidth.toPx(), cap = StrokeCap.Round)
    }
    val drawableAngle = endAngle - startAngle
    Canvas(
        modifier = modifier.fillMaxSize(),
    ) {
        val diameterOffset = stroke.width / 2
        val arcDimen = size.minDimension - 2 * diameterOffset
        var currentStartAngle = startAngle
        var remainingAngle = drawableAngle
        for (segment in segmentData) {
            val segmentAngle = (segment.weight * drawableAngle) / totalWeight

            drawSegmentArc(
                color = segment.color,
                currentStartAngle = currentStartAngle,
                sweepAngle = segmentAngle,
                diameterOffset = diameterOffset,
                diameter = size.minDimension,
                arcDimen = arcDimen,
                stroke = stroke
            )

            currentStartAngle += segmentAngle
            remainingAngle -= segmentAngle
        }
        if (remainingAngle > 0f) {
            drawSegmentArc(
                color = trackColor,
                currentStartAngle = currentStartAngle,
                sweepAngle = remainingAngle,
                diameterOffset = diameterOffset,
                diameter = size.minDimension,
                arcDimen = arcDimen,
                stroke = stroke
            )
        }
    }
}

private fun DrawScope.drawSegmentArc(
    color: Color,
    currentStartAngle: Float,
    sweepAngle: Float,
    diameterOffset: Float,
    diameter: Float,
    arcDimen: Float,
    stroke: Stroke
) {
    val offset = Offset(
        diameterOffset + (size.width - diameter) / 2,
        diameterOffset + (size.height - diameter) / 2,
    )

    drawArc(
        color = color,
        startAngle = currentStartAngle,
        sweepAngle = sweepAngle,
        useCenter = false,
        topLeft = offset,
        size = Size(arcDimen, arcDimen),
        style = stroke
    )
}
