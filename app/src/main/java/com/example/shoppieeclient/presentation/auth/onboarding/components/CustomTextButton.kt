package com.example.shoppieeclient.presentation.auth.onboarding.components

import androidx.compose.animation.animateContentSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color.Companion.DarkGray
import androidx.compose.ui.text.font.FontWeight

@Composable
fun CustomTextButton(
    text: String, onClick: () -> Unit
) {
    TextButton(
        onClick = onClick,
        modifier = Modifier.animateContentSize()
    ) {
        Text(
            text = text,
            style = MaterialTheme.typography.labelMedium.copy(
                fontWeight = FontWeight.Bold
            ),
            color = DarkGray.copy(alpha = 0.5f)
        )
    }
}

