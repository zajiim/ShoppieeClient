package com.example.shoppieeclient.presentation.home.details

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.example.shoppieeclient.presentation.navigation.Destination

@Composable
fun DetailsScreen(
    modifier: Modifier = Modifier,
    userDetails: Destination.Details
) {

    Column(
        modifier = modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = "Name: ${userDetails.name}")
        Text(text = "ID: ${userDetails.id}")

    }

}