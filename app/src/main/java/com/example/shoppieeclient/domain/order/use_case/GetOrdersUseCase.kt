package com.example.shoppieeclient.domain.order.use_case

import androidx.paging.PagingData
import com.example.shoppieeclient.domain.order.model.OrderProductModel
import com.example.shoppieeclient.domain.order.repository.ShoppieeOrderRepo
import kotlinx.coroutines.flow.Flow

class GetOrdersUseCase(
    private val shoppieeOrderRepo: ShoppieeOrderRepo
) {
    operator fun invoke(page: Int, limit: Int): Flow<PagingData<OrderProductModel>> {
        return shoppieeOrderRepo.getAllOrders(page, limit)
    }
}