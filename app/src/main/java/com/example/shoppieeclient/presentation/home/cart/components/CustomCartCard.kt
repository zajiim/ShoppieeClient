package com.example.shoppieeclient.presentation.home.cart.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Delete
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.example.shoppieeclient.domain.cart.models.CartProductModel

@Composable
fun CustomCartCard(
    modifier: Modifier = Modifier,
    cartItem: CartProductModel
) {
    Row(
        modifier = modifier.fillMaxWidth()
            .height(80.dp)
    ) {
        AsyncImage(
            modifier = Modifier.fillMaxHeight().clip(RoundedCornerShape(12.dp)),
            model = cartItem.images[0],
            contentDescription = cartItem.name,
            contentScale = ContentScale.FillHeight
        )
        Spacer(modifier = Modifier.width(16.dp))
        Column(
            modifier = Modifier.fillMaxHeight(),
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            Text(text = cartItem.name,
                style = MaterialTheme.typography.titleMedium
            )
            Text(text = cartItem.price.toString(),
                style = MaterialTheme.typography.titleSmall
            )

            CustomItemCounter(
                count = cartItem.cartItemCount
            )
        }
        Spacer(modifier = Modifier.weight(1f))

        Column(
            modifier = Modifier.fillMaxHeight(),
            verticalArrangement = Arrangement.SpaceBetween,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "L", style = TextStyle(
                    fontSize = 16.sp, color = Color.Black
                )
            )

            IconButton(onClick = { /*TODO*/ }) {
                Icon(
                    imageVector = Icons.Outlined.Delete,
                    contentDescription = "Delete product"
                )
            }
        }
    }

}