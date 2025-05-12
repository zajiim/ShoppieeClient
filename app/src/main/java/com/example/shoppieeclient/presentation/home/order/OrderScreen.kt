package com.example.shoppieeclient.presentation.home.order

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun OrderScreen(
    modifier: Modifier = Modifier
) {
    Column(modifier = modifier.fillMaxSize()) {
        Text(text = "Order Screen")
    }
}