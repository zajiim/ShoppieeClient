package com.example.shoppieeclient.presentation.home.details.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.shoppieeclient.ui.theme.PrimaryBlue
import com.example.shoppieeclient.ui.theme.SubTitleColor


@Composable
fun RegionButtons(
    region: String,
    selectedRegion: String = "EU",
    onClick: () -> Unit
) {
    Text(text = region,
        modifier = Modifier
            .clickable { onClick() }
            .padding(8.dp),
        color = if (region == selectedRegion) PrimaryBlue else SubTitleColor,
        fontWeight = if (region == selectedRegion) FontWeight.Bold else FontWeight.Normal
    )

}