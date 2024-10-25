package com.example.shoppieeclient.presentation.home.home.components

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.example.shoppieeclient.ui.theme.Primary
import androidx.compose.runtime.getValue
import androidx.compose.ui.draw.alpha
import com.example.shoppieeclient.ui.theme.PrimaryBlue

@Composable
fun CustomSuggestionChip(
    brand: String,
    iconResId: Int,
    isExpanded: Boolean,
    onClick: () -> Unit
) {
    val backgroundColor by animateColorAsState(
        targetValue = if (isExpanded) PrimaryBlue else Color.White,
        label = "",
    )

    val textAlpha by animateFloatAsState(
        targetValue = if (isExpanded) 1f else 0f,
        label = ""
    )

    Surface(
        onClick = onClick,
        shape = RoundedCornerShape(40.dp),
        color = backgroundColor,
    ) {
        Row(
            modifier = Modifier.padding(8.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {
            AsyncImage(
                modifier = Modifier.size(24.dp), model = iconResId, contentDescription = null
            )
            if (isExpanded) {
                Spacer(modifier = Modifier.width(8.dp))
                Text(
                    text = brand, color = Color.White,
                    modifier = Modifier.alpha(textAlpha)
                )
            }
        }
    }

}