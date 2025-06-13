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
import com.example.shoppieeclient.domain.home.track_order.models.ItemModel
import com.example.shoppieeclient.ui.theme.BackGroundColor
import com.example.shoppieeclient.ui.theme.SubTitleColor

@Composable
fun OrderHeaderSection(
    modifier: Modifier = Modifier,
    item: ItemModel? = null
) {
    val sum = item?.quantity?.toInt()?.let { item.product?.price?.times( it) }
    Row(
        modifier = modifier
            .fillMaxWidth()
            .height(80.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        AsyncImage(
            model = item?.product?.images?.get(0),
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
                text = item?.product?.name ?: "Product Name",
                style = MaterialTheme.typography.titleSmall
            )
            Text(
                text = "Size: ${item?.product?.size} || Quantity: ${item?.quantity} pcs",
                style = MaterialTheme.typography.bodyMedium.copy(color = SubTitleColor)
            )
            Text(
                text = "${sum.toString()}/-",
                style = MaterialTheme.typography.titleMedium
            )

        }
        Spacer(modifier = Modifier.width(12.dp))
    }
}

