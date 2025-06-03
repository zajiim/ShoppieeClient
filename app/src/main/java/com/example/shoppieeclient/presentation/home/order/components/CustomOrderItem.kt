package com.example.shoppieeclient.presentation.home.order.components

import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.paging.compose.LazyPagingItems
import com.example.shoppieeclient.domain.order.model.OrderProductModel

private const val TAG = "CustomOrderItem"

@Composable
fun CustomOrderItem(
    modifier: Modifier = Modifier,
    orderItems: LazyPagingItems<OrderProductModel>?,
    onTrackOrderClick: (String) -> Unit,
) {

    LazyColumn(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        items(orderItems?.itemCount ?: 0) { orderItem ->
            val order = orderItems?.get(orderItem)
            Log.e(TAG, "orders == > $order: ")
            if (order != null) {
                CustomOrderCard(
                    orderItems = order,
                    onTrackOrderClick = {
                        onTrackOrderClick(order.orderId)
                    }
                )
            }
        }
    }
}
