package com.example.shoppieeclient.presentation.home.track_order.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.shoppieeclient.domain.home.track_order.models.OrderStep
import com.example.shoppieeclient.R
import com.example.shoppieeclient.domain.home.track_order.models.StepStatus
import com.example.shoppieeclient.ui.theme.BackGroundColor

@Composable
fun OrderStatusSection(
    modifier: Modifier = Modifier,
    currentStep: Int
) {
    val steps = listOf(
        OrderStep(1, "Order Placed", "23 Aug 2023, 04:25 PM", R.drawable.ic_track_order),
        OrderStep(2, "In Progress", "23 Aug 2023, 03:54 PM", R.drawable.ic_track_packaged),
        OrderStep(3, "Shipped", "Expected 02 Sep 2023", R.drawable.ic_shipping_order),
        OrderStep(4, "Delivered", "23 Aug 2023, 2023", R.drawable.ic_order_delivered)
    )

    var selectedStep by remember { mutableIntStateOf(currentStep) }

    Column(modifier = modifier
        .fillMaxWidth()
        .background(BackGroundColor)
    )
    {
        Text(
            text = "Order Status",
            style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.Medium),
            modifier = Modifier.padding(bottom = 32.dp)
        )

        steps.forEachIndexed { index, step ->
            val status = when {
                step.id < selectedStep -> StepStatus.COMPLETED
                step.id == selectedStep -> StepStatus.CURRENT
                else -> StepStatus.PENDING
            }
            StepItem(
                step = step,
                status = status,
                isLast = index == steps.size - 1,
                modifier = Modifier.fillMaxWidth()
            )
        }
    }

}