package com.example.shoppieeclient.domain.order.repository

import androidx.paging.PagingData
import com.example.shoppieeclient.domain.order.model.OrderItemModel
import com.example.shoppieeclient.domain.order.model.OrderProductModel
import kotlinx.coroutines.flow.Flow

interface ShoppieeOrderRepo {
    fun getAllOrders(page: Int, limit: Int): Flow<PagingData<OrderProductModel>>
}