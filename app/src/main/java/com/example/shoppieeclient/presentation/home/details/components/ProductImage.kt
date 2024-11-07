package com.example.shoppieeclient.presentation.home.details.components

import androidx.compose.animation.AnimatedVisibilityScope
import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.animation.SharedTransitionScope
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import coil.compose.AsyncImage

@OptIn(ExperimentalSharedTransitionApi::class)
@Composable
fun SharedTransitionScope.ProductImage(
    modifier: Modifier = Modifier,
    imageUrl: String,
    animatedVisibilityScope: AnimatedVisibilityScope
) {
    AsyncImage(
        modifier = modifier
            .sharedElement(
                state = rememberSharedContentState(key = imageUrl),
                animatedVisibilityScope = animatedVisibilityScope
            )
            .fillMaxSize(),
        model = imageUrl,
        contentDescription = null ,
        contentScale = ContentScale.Crop
    )

}