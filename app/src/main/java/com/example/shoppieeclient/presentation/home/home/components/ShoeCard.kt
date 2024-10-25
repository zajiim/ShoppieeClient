package com.example.shoppieeclient.presentation.home.home.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.White
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.example.shoppieeclient.domain.auth.models.home.HomeProductModel
import com.example.shoppieeclient.ui.theme.Primary
import com.example.shoppieeclient.ui.theme.PrimaryBlue
import com.example.shoppieeclient.ui.theme.TitleColor
import com.example.shoppieeclient.utils.shimmerEffect

@Composable
fun ShoeCard(
    shoe: HomeProductModel ?= null,
    isLoading: Boolean,
    onClick: () -> Unit = {}
) {
    Box(
        modifier = Modifier.width(220.dp).height(220.dp)
            .clip(RoundedCornerShape(16.dp))
            .background(Color.White)
            .clickable{onClick()}
    ) {
        if (isLoading) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(Color.Gray.copy(alpha = 0.1f))
                    .shimmerEffect()
            )
        } else {
            Column(
                modifier = Modifier
                    .fillMaxSize(),
                verticalArrangement = Arrangement.spacedBy(4.dp)
            ) {

                AsyncImage(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(137.dp),
                    model = shoe?.images?.getOrNull(0)?: "default_image_url_here",
                    contentDescription = shoe?.name,
                    contentScale = ContentScale.Crop
                )

                Text(
                    modifier = Modifier.padding(start = 8.dp),
                    text = "Best Seller",
                    style = TextStyle(
                        fontSize = 12.sp,
                        fontWeight = FontWeight.Normal,
                        color = PrimaryBlue
                    )
                )

                Text(
                    modifier = Modifier.padding(start = 8.dp),
                    text = shoe?.name ?: "",
                    style = TextStyle(
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Medium,
                        color = TitleColor
                    )
                )
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(
                        modifier = Modifier.padding(start = 8.dp),
                        text = "$${shoe?.price}"
                    )
                    Box(
                        modifier = Modifier
                            .fillMaxHeight()
                            .clip(RoundedCornerShape(topStart = 16.dp))
                            .background(PrimaryBlue)
                    ) {
                        IconButton(onClick = { /*TODO*/ }) {
                            Icon(
                                imageVector = Icons.Default.Add,
                                contentDescription = "Add to cart",
                                tint = White
                            )
                        }
                    }
                }
            }
        }
    }

}