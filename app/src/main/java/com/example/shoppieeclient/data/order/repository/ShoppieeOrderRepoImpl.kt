package com.example.shoppieeclient.data.order.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.example.shoppieeclient.data.order.paging.OrdersPagingSource
import com.example.shoppieeclient.data.order.remote.api.ShoppieeOrderApiService
import com.example.shoppieeclient.domain.order.model.OrderItemModel
import com.example.shoppieeclient.domain.order.model.OrderProductModel
import com.example.shoppieeclient.domain.order.repository.ShoppieeOrderRepo
import com.example.shoppieeclient.utils.Constants
import kotlinx.coroutines.flow.Flow

class ShoppieeOrderRepoImpl(
    private val shoppieeOrderApiService: ShoppieeOrderApiService
): ShoppieeOrderRepo {
    override fun getAllOrders(
        page: Int,
        limit: Int
    ): Flow<PagingData<OrderProductModel>> {
        return Pager(
            config = PagingConfig(
                pageSize = Constants.PER_PAGE_ITEMS,
            ),
            pagingSourceFactory = {
                OrdersPagingSource(shoppieeOrderApiService)
            }
        ).flow
    }
}