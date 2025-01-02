package com.example.shoppieeclient.presentation.home.accounts.components

import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.graphics.drawscope.rotate
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.example.shoppieeclient.ui.theme.PrimaryBlue

@Composable
fun CustomProfileImage(
    modifier: Modifier = Modifier,
    profileImage: String,
    isLoading: Boolean,
    size: Dp = 90.dp,
    borderWidth: Dp = 4.dp
) {
    val infiniteTransition = rememberInfiniteTransition(
        label = "animateBar"
    )
    val rotation = infiniteTransition.animateFloat(
        initialValue = 0f,
        targetValue = 360f,
        animationSpec = infiniteRepeatable(
            animation = tween(1500, easing = LinearEasing),
            repeatMode = RepeatMode.Restart
        ), label = "infiniteAnimation"
    )

    Box(modifier = modifier) {
        Box(modifier = Modifier.size(size).align(Alignment.Center)) {
            if (isLoading) {
                Canvas(modifier = Modifier.size(size).align(Alignment.Center)) {
                    val strokeWidth = borderWidth.toPx()
                    rotate(rotation.value) {
                        drawArc(
                            color = PrimaryBlue,
                            startAngle = 0f,
                            sweepAngle = 300f,
                            useCenter = false,
                            style = Stroke(width = strokeWidth)
                        )
                    }
                }
            }
            AsyncImage(
                model = profileImage.ifEmpty { "https://picsum.photos/200" },
                contentDescription = "Profile Image",
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .size(size - borderWidth * 2)
                    .clip(CircleShape)
                    .align(Alignment.Center)
            )
        }
    }



}