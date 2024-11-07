package com.example.shoppieeclient.utils

import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.unit.IntSize
import com.example.shoppieeclient.ui.theme.PrimaryBlue
import com.example.shoppieeclient.R

//fun Modifier.shimmerEffect(): Modifier = composed {
//    var size by remember {
//        mutableStateOf(IntSize.Zero)
//    }
//    val transition = rememberInfiniteTransition(label = "")
//    val startOffsetX by transition.animateFloat(
//        initialValue = -2 * size.width.toFloat(),
//        targetValue = 2 * size.width.toFloat(),
//        animationSpec = infiniteRepeatable(
//            animation = tween(
//                durationMillis = 1000
//            )
//        ), label = ""
//    )
//
//    background(
//        brush = Brush.linearGradient(
//            colors = listOf(
//                Color.Transparent,
//                PrimaryBlue.copy(alpha = 0.2f),
//                Color(0xFFF7EED8),
//            ),
//            start = Offset(startOffsetX, 0f),
//            end = Offset(startOffsetX + size.width.toFloat(), size.height.toFloat())
//        )
//    ).onGloballyPositioned {
//        size = it.size
//    }
//}


fun Modifier.shimmerEffect() = composed {
    val transition = rememberInfiniteTransition(label = "")
    val alpha = transition.animateFloat(
        initialValue = 0.2f,
        targetValue = 0.9f,
        animationSpec = infiniteRepeatable(
            animation = tween(800),
            repeatMode = RepeatMode.Reverse
        ), label = ""
    ).value
    background(color = Color.Gray.copy(alpha = alpha))
}