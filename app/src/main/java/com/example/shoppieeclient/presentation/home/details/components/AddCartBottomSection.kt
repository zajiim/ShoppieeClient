package com.example.shoppieeclient.presentation.home.details.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawingPadding
import androidx.compose.foundation.layout.safeGesturesPadding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.shoppieeclient.ui.theme.PrimaryBlue
import com.example.shoppieeclient.ui.theme.SubTitleColor

@Composable
fun AddCartBottomSection(
    modifier: Modifier = Modifier,
    price: String?,
    selectedRegion: String,
    selectedSize: Int,
//    onAddToCartClick: (String, Int) -> Unit,
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .safeDrawingPadding()
            .height(100.dp)
            .clip(RoundedCornerShape(topStart = 16.dp, topEnd = 16.dp))
            .background(Color.White),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Column(
            modifier = Modifier
                .fillMaxHeight()
                .padding(horizontal = 16.dp),
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                text = "Price", style = TextStyle(
                    fontSize = 18.sp,
                    color = SubTitleColor,
                    fontWeight = FontWeight.Normal
                )
            )

            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = "₹${price}", style = TextStyle(
                    fontSize = 18.sp,
                    color = Color.Black,
                    fontWeight = FontWeight.Bold
                )
            )
        }
        CustomButton(
            modifier = Modifier.padding(horizontal = 16.dp),
            text = "Add To Cart",
            backgroundColor = PrimaryBlue,
            contentColor = Color.White,
            onButtonClicked = {
//                onAddToCartClick(selectedRegion, selectedSize)
            },
            isLoading = false
        )
    }
}
