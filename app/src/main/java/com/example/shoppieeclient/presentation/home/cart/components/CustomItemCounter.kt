package com.example.shoppieeclient.presentation.home.cart.components

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.shoppieeclient.ui.theme.PrimaryBlue
import com.example.shoppieeclient.R
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import com.example.shoppieeclient.presentation.common.components.CustomToast
import kotlinx.coroutines.delay

@Composable
fun CustomItemCounter(
    modifier: Modifier = Modifier,
    count: Int,
    totalItemCount: Int,
    onIncrement: () -> Unit,
    onDecrement: () -> Unit,
    onDelete: () -> Unit,
    showToast: () -> Unit
) {
    val ctx = LocalContext.current

    Row(
        modifier = modifier,
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        Card(
            shape = CircleShape,
            colors = CardDefaults
                .cardColors(
                    containerColor = Color.White
                )
        ) {
            Image(
                painter = painterResource(id = R.drawable.ic_minus),
                contentDescription = "decrement",
                modifier = Modifier.clickable {
                    if (count <= 1) {
                        onDelete()
                    } else {
                        onDecrement()
                    }
                }
            )
        }
        Text(
            text = count.toString()
        )
        Card(
            shape = CircleShape,
            colors = CardDefaults
                .cardColors(
                    containerColor = PrimaryBlue
                )
        ){
            Image(
                painter = painterResource(id = R.drawable.ic_plus),
                colorFilter = ColorFilter.tint(Color.White),
                contentDescription = "increment",
                modifier = Modifier.clickable {
                    if (count < totalItemCount) {
                        onIncrement()
                    } else {
                        showToast()
//                        Toast.makeText(ctx, "No more items available", Toast.LENGTH_SHORT).show()
                    }
                }
            )
        }

    }


}