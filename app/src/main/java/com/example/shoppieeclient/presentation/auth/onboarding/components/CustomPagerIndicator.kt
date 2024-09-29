package com.example.shoppieeclient.presentation.auth.onboarding.components

import android.annotation.SuppressLint
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.spring
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color.Companion.LightGray
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.unit.dp
import com.example.shoppieeclient.ui.theme.Primary
import com.example.shoppieeclient.utils.pxToDp

@Composable
fun CustomPagerIndicator(
    pageCount: Int,
    currentPage: Int,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier.wrapContentWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        repeat(pageCount) { index ->
            Indicators(
                isSelected = index == currentPage,
                modifier = Modifier.padding(horizontal = 1.pxToDp())
            )
        }

    }

}

@Composable
fun Indicators(
    isSelected: Boolean, modifier: Modifier
) {

    val dotWidth =
        animateDpAsState(
            targetValue = if (isSelected) 24.dp else 6.dp,
            label = "dot width",
            animationSpec = tween(
                durationMillis = 500,
                easing = FastOutSlowInEasing)
            )

    Box(
        modifier = modifier
            .padding(2.dp)
            .size(width = dotWidth.value, height = 6.dp)
            .clip(RoundedCornerShape(6.dp))
            .background(if (isSelected) Primary else LightGray)
    )
}
