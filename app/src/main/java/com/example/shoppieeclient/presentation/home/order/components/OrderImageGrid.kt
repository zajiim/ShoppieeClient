package com.example.shoppieeclient.presentation.home.order.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.example.shoppieeclient.domain.order.model.OrderItemModel

@Composable
fun OrderImageGrid(
    modifier: Modifier = Modifier,
    orderItems: List<OrderItemModel>,
    ) {
    val displayItems = orderItems.take(3)
    val remainingItemsCount = orderItems.size - 3

    Column(
        modifier = modifier
            .size(100.dp)
            .clip(RoundedCornerShape(6.dp))
            .background(Color.LightGray)
            .padding(4.dp)
    ) {
        Row(modifier = Modifier.weight(1f)) {
            for (i in 0 until minOf(2, displayItems.size)) {
                val item = displayItems[i]
                AsyncImage(
                    model = item.image,
                    contentDescription = null,
                    modifier = Modifier
                        .weight(1f)
                        .aspectRatio(1f)
                        .padding(2.dp)
                        .clip(RoundedCornerShape(4.dp)),
                    contentScale = ContentScale.Crop
                )
            }
        }




//        if (displayItems.size > 2) {
//            Row(
//                modifier = Modifier.weight(1f)
//            ) {
//                AsyncImage(
//                    modifier = Modifier
//                        .weight(1f)
//                        .aspectRatio(1f)
//                        .padding(2.dp)
//                        .clip(RoundedCornerShape(4.dp)),
//                    model = displayItems[2].image,
//                    contentDescription = "order image",
//                    contentScale = ContentScale.Crop
//                )

//                if (orderItems.size > 3) {
//                    Box(
//                        modifier = Modifier
//                            .weight(1f)
//                            .aspectRatio(1f)
//                            .padding(2.dp)
//                            .clip(RoundedCornerShape(4.dp))
//                            .background(Color.DarkGray),
//                        contentAlignment = Alignment.Center
//                    ) {
//                        Text(
//                            text = "+$remainingItemsCount",
//                            color = Color.White,
//                            style = MaterialTheme.typography.bodySmall
//                        )
//                    }
//                } else if (displayItems.size > 3) {
//                    AsyncImage(
//                        modifier = Modifier
//                            .weight(1f)
//                            .aspectRatio(1f)
//                            .padding(2.dp)
//                            .clip(RoundedCornerShape(4.dp)),
//                        model = displayItems[3].image,
//                        contentDescription = "order image",
//                        contentScale = ContentScale.Crop
//                    )
//                }
//            }
//        }
    }

}