package com.example.shoppieeclient.presentation.home.home.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import com.example.shoppieeclient.R

@Composable
fun CustomNoInternet(
    modifier: Modifier = Modifier,
    message: String = "No internet found"
) {
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            painter = painterResource(id = R.drawable.ic_no_internet),
            contentDescription = "No internet found",

        )

        Text(
            text = message,
            style = MaterialTheme.typography.bodyLarge,
            color = Color.Gray
        )

    }

}

@Preview
@Composable
private fun NoInPreview() {
    CustomNoInternet(modifier = Modifier.fillMaxSize())
}