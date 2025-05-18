package com.example.shoppieeclient.presentation.home.order.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.example.shoppieeclient.domain.order.model.OrderProductModel
import com.example.shoppieeclient.presentation.home.cart.components.CustomShimmer
import com.example.shoppieeclient.ui.theme.PrimaryBlue

@Composable
fun CustomOrderCard(
    modifier: Modifier = Modifier,
    orderItems: OrderProductModel,
    isLoading: Boolean = false,
    onTrackOrderClick: () -> Unit
) {
    if (isLoading) {
        CustomShimmer(
            modifier
                .fillMaxWidth()
                .height(80.dp)
        )
    } else {
        Box(
            modifier = modifier
                .fillMaxWidth()
                .wrapContentHeight()
                .padding(horizontal = 16.dp, vertical = 8.dp)
                .clip(RoundedCornerShape(8.dp))
                .background(Color.White)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
            ) {
                /*AsyncImage(
                    modifier = Modifier
                        .size(120.dp)
                        .fillMaxHeight()
                        .padding(start = 12.dp)
                        .clip(RoundedCornerShape(6.dp)),
                    model = orderItems.items[0].image,
                    contentDescription = "order pic",
                    contentScale = ContentScale.Crop
                )*/

                OrderImageGrid(
                    orderItems = orderItems.items
                )

                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 20.dp, horizontal = 16.dp),
                    verticalArrangement = Arrangement.Center,
                ) {
                    Text(
                        text = "Order Status: ${orderItems.orderStatus}",
                        color = Color.Black,
                        modifier = Modifier.padding(bottom = 4.dp),
                        style = MaterialTheme.typography.bodySmall
                    )
                    Text(
                        text = "Order Amount: ${orderItems.total}",
                        color = Color.Black,
                        modifier = Modifier.padding(bottom = 4.dp),
                        style = MaterialTheme.typography.bodySmall
                    )
                    TextButton(
                        modifier = Modifier.align(alignment = Alignment.End),
                        onClick = onTrackOrderClick,
                    ) {
                        Text(
                            text = "Track order",
                            color = PrimaryBlue,
                            style = MaterialTheme.typography.bodySmall
                        )
                    }
                }
            }
        }
    }

}

