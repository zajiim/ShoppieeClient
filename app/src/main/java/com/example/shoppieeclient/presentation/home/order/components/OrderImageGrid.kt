package com.example.shoppieeclient.presentation.home.order.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.example.shoppieeclient.domain.order.model.OrderItemModel

@Composable
fun OrderImageGrid(
    modifier: Modifier = Modifier,
    orderItems: List<OrderItemModel>,
) {
    Column(
        modifier = modifier
            .size(100.dp)
            .clip(RoundedCornerShape(6.dp))
            .background(Color.LightGray)
            .padding(4.dp)
    ) {
        when (orderItems.size) {
            0 -> {
                // No items, show nothing or a placeholder
            }
            1 -> {
                // Single item takes full space
                AsyncImage(
                    model = orderItems[0].image,
                    contentDescription = null,
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(2.dp)
                        .clip(RoundedCornerShape(4.dp)),
                    contentScale = ContentScale.Crop
                )
            }
            2 -> {
                // Two items: top row with two images, bottom row with plus icon in first column
                Column(modifier = Modifier.fillMaxSize()) {
                    Row(modifier = Modifier.weight(1f)) {
                        for (i in 0 until 2) {
                            AsyncImage(
                                model = orderItems[i].image,
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
                    Row(modifier = Modifier.weight(1f)) {
                        // Plus icon in first column, empty space in second
                        Box(
                            modifier = Modifier
                                .weight(1f)
                                .aspectRatio(1f)
                                .padding(2.dp)
                                .clip(RoundedCornerShape(4.dp))
                                .background(Color(0x80000000)),
                            contentAlignment = Alignment.Center
                        ) {
                            Icon(
                                imageVector = Icons.Default.Add,
                                contentDescription = "More",
                                tint = Color.White,
                                modifier = Modifier.size(24.dp)
                            )
                        }
                        Spacer(modifier = Modifier.weight(1f))
                    }
                }
            }
            else -> {
                // 3 or more items
                GridLayout(orderItems = orderItems)
            }
        }
    }
}

@Composable
private fun GridLayout(
    orderItems: List<OrderItemModel>
) {
    val itemCount = minOf(3, orderItems.size)
    val remainingCount = orderItems.size - 3

    Column(modifier = Modifier.fillMaxSize()) {
        // Top row
        Row(modifier = Modifier.weight(1f)) {
            val topRowItems = minOf(2, itemCount)
            for (i in 0 until topRowItems) {
                AsyncImage(
                    model = orderItems[i].image,
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
        // Bottom row
        Row(modifier = Modifier.weight(1f)) {
            if (itemCount > 2) {
                AsyncImage(
                    model = orderItems[2].image,
                    contentDescription = null,
                    modifier = Modifier
                        .weight(1f)
                        .aspectRatio(1f)
                        .padding(2.dp)
                        .clip(RoundedCornerShape(4.dp)),
                    contentScale = ContentScale.Crop
                )
            }
            Box(
                modifier = Modifier
                    .weight(1f)
                    .aspectRatio(1f)
                    .padding(2.dp)
                    .clip(RoundedCornerShape(4.dp))
                    .background(Color(0x80000000)),
                contentAlignment = Alignment.Center
            ) {
                if (remainingCount > 0) {
                    Text(
                        text = "+$remainingCount",
                        color = Color.White,
                        fontWeight = FontWeight.Bold,
                        fontSize = 16.sp
                    )
                } else {
                    Icon(
                        imageVector = Icons.Default.Add,
                        contentDescription = "More",
                        tint = Color.White,
                        modifier = Modifier.size(24.dp)
                    )
                }
            }
        }
    }
}
