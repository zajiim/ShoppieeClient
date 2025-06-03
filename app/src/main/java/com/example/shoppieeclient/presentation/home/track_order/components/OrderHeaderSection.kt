package com.example.shoppieeclient.presentation.home.track_order.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.example.shoppieeclient.ui.theme.BackGroundColor
import com.example.shoppieeclient.ui.theme.SubTitleColor

@Composable
fun OrderHeaderSection(
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .height(80.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        AsyncImage(
            model = "https://picsum.photos/200/300",
            contentDescription = "Order Image",
            modifier = Modifier
                .size(72.dp)
                .clip(RoundedCornerShape(12.dp)),
            contentScale = ContentScale.Crop
        )

        Spacer(modifier = Modifier.width(12.dp))

        Column(
            modifier = Modifier
                .weight(1f),
            verticalArrangement = Arrangement.SpaceEvenly
        ) {
            Text(
                text = "Brown Shoes",
                style = MaterialTheme.typography.titleSmall
            )
            Text(
                text = "Size: 42 || Quantity: 1pcs",
                style = MaterialTheme.typography.bodyMedium.copy(color = SubTitleColor)
            )
            Text(
                text = "$120.00",
                style = MaterialTheme.typography.titleMedium
            )

        }
    }
}


@Preview
@Composable
private fun PreviewOrderHeader() {
    OrderHeaderSection()
}