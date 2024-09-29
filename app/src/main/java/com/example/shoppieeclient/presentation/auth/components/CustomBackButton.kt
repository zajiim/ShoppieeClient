package com.example.shoppieeclient.presentation.auth.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun CustomBackButton(
    onBackClicked: () -> Unit,
    modifier: Modifier
) {
    Box(
        modifier = modifier.fillMaxWidth().padding(top = 16.dp)
    ) {
        IconButton(
            modifier = Modifier.size(56.dp).clip(CircleShape).background(Color.White),
            onClick = { onBackClicked() }) {
            Icon(
                imageVector = Icons.AutoMirrored.Default.ArrowBack, contentDescription = null
            )
        }
    }
}