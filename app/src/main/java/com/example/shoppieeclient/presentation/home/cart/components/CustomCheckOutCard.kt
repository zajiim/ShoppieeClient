package com.example.shoppieeclient.presentation.home.cart.components

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBars
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawingPadding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.PathEffect
import androidx.compose.ui.unit.dp
import com.example.shoppieeclient.presentation.auth.components.CustomButton
import com.example.shoppieeclient.ui.theme.PrimaryBlue
import kotlin.io.path.Path

@Composable
fun CustomCheckOutCard(
    modifier: Modifier = Modifier
) {
    val safeBottomBarPaddingVal = WindowInsets.navigationBars.asPaddingValues().calculateBottomPadding()
    Box(
        modifier = modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .clip(RoundedCornerShape(topStart = 16.dp, topEnd = 16.dp))
            .background(Color.White)
            .padding(bottom = safeBottomBarPaddingVal)
    ) {
        val pathEffect = PathEffect.dashPathEffect(floatArrayOf(20f, 8f), 0f)
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 24.dp, horizontal = 20.dp)
        ) {
            CustomTotalRow(
                titleText = "Subtotal",
                valueText = "$1250.00",
                titleColor = Color.LightGray,
                valueColor = Color.Black
            )
            Spacer(modifier = Modifier.height(16.dp))

            CustomTotalRow(
                titleText = "Platform fees",
                valueText = "$12.00",
                titleColor = Color.LightGray,
                valueColor = Color.Black
            )
            Spacer(modifier = Modifier.height(24.dp))


            Canvas(
                Modifier.fillMaxWidth()
            ) {
                drawLine(
                    color = Color.LightGray,
                    strokeWidth = 2.dp.toPx(),
                    start = Offset(0f, 0f),
                    end = Offset(size.width, 0f),
                    pathEffect = pathEffect
                )
            }
            Spacer(modifier = Modifier.height(16.dp))


            CustomTotalRow(
                titleText = "Total Cost",
                valueText = "$1262.00",
                titleColor = Color.Black,
                valueColor = Color.Black
            )

            Spacer(modifier = Modifier.height(24.dp))
            CustomButton(
                modifier = Modifier.fillMaxWidth().padding( horizontal = 20.dp),
                text = "Checkout",
                backgroundColor = PrimaryBlue,
                contentColor = Color.White,
                onButtonClicked = { },
                isLoading = false,
                enabled = true
            )
        }
    }

}

@Composable
private fun CustomTotalRow(
    titleText: String,
    valueText: String,
    titleColor: Color = Color.LightGray,
    valueColor: Color = Color.Black
) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(
            text = titleText,
            style = MaterialTheme.typography.titleMedium.copy(color = titleColor)
        )
        Text(text = valueText,
            style = MaterialTheme.typography.titleMedium.copy(valueColor))
    }
}