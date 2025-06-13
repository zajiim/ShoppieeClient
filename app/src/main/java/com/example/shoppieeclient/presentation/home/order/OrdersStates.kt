package com.example.shoppieeclient.presentation.home.order

import androidx.paging.PagingData
import com.example.shoppieeclient.domain.order.model.OrderProductModel
import kotlinx.coroutines.flow.Flow

data class OrdersStates(
    val isLoading: Boolean = false,
    val error: String? = null,
    val orders: Flow<PagingData<OrderProductModel>>? = null,
)