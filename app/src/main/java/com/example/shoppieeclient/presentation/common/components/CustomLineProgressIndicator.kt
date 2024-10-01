package com.example.shoppieeclient.presentation.common.components

import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.keyframes
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.focusable
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.progressSemantics
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.lerp
import androidx.compose.ui.unit.max


private const val DefaultAnimationDuration = 600
private const val DefaultAnimationDelay = 400
private const val DefaultStartDelay = 0
private const val DefaultLineCount = 5

private val DefaultMaxLineHeight = 32.dp
private val DefaultMinLineHeight = 16.dp
private val DefaultLineWidth = 3.dp
private val DefaultLineSpacing = 6.dp
private val DefaultLineCornerRadius = 3.dp


@Composable
fun CustomLineProgressIndicator(
    modifier: Modifier = Modifier,
    color: Color = MaterialTheme.colorScheme.primary,
    animationDuration: Int = DefaultAnimationDuration,
    animationDelay: Int = DefaultAnimationDelay,
    startDelay: Int = DefaultStartDelay,
    lineCount: Int = DefaultLineCount,
    maxLineHeight: Dp = DefaultMaxLineHeight,
    minLineHeight: Dp = DefaultMinLineHeight,
    lineWidth: Dp = DefaultLineWidth,
    lineSpacing: Dp = DefaultLineSpacing,
    lineCornerRadius: Dp = DefaultLineCornerRadius
) {

    val transition = rememberInfiniteTransition(label = "animate")
    val duration = startDelay + animationDuration + animationDelay
    val height = arrayListOf<Float>().apply {
        for (i in 0 until lineCount) {
            val delay = startDelay + animationDelay / (lineCount - 1) * i
            val height by transition.animateFloat(
                initialValue = 0f,
                targetValue = 1f,
                animationSpec = infiniteRepeatable(
                    animation = keyframes {
                        durationMillis = duration
                        0f at delay using LinearEasing
                        1f at delay + (animationDuration / 2) using LinearEasing
                        0f at delay + animationDuration
                        0f at  duration
                    }
                ), label = "animation keyframes"
            )
            add(height)
        }
    }

    val width = (lineWidth + lineSpacing) * lineCount - lineSpacing

    ProgressIndicator(
        modifier = modifier,
        width = width,
        height = maxLineHeight) {
        drawIndeterminateLineScaleIndicator(
            maxLineHeight = maxLineHeight.toPx(),
            height = height.map { lerp(minLineHeight, maxLineHeight, it).toPx() },
            width = lineWidth.toPx(),
            cornerRadius = lineCornerRadius.toPx(),
            spacing = lineSpacing.toPx(),
            color = color
        )
    }
}


private fun DrawScope.drawIndeterminateLineScaleIndicator(
    maxLineHeight: Float,
    height: List<Float>,
    width: Float,
    cornerRadius: Float,
    spacing: Float,
    color: Color
) {
    for(i in height.indices) {
        val x = i * (width + spacing)
        val y = (maxLineHeight - height[i]) / 2
        drawRoundRect(
            color = color,
            topLeft = Offset(x, y),
            size = Size(width, height[i]),
            cornerRadius = CornerRadius(cornerRadius)
        )
    }

}




@Composable
internal fun ProgressIndicator(
    modifier: Modifier,
    width: Dp,
    height: Dp,
    onDraw: DrawScope.() -> Unit
) {
    Canvas(
        modifier = modifier
            .progressSemantics()
            .size(width, height)
            .focusable(),
        onDraw = onDraw
    )
}