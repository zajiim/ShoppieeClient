package com.example.shoppieeclient.presentation.home.details.components

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import coil.compose.AsyncImage

@Composable
fun ProductImage(
    modifier: Modifier = Modifier,
    imageUrl: String
) {
    AsyncImage(
        modifier = modifier.fillMaxSize(),
        model = imageUrl,
        contentDescription = null ,
        contentScale = ContentScale.Crop
    )

}