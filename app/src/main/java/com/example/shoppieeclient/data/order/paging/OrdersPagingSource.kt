package com.example.shoppieeclient.data.order.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.shoppieeclient.data.order.remote.api.ShoppieeOrderApiService
import com.example.shoppieeclient.data.order.remote.mapper.toOrderItemModel
import com.example.shoppieeclient.data.order.remote.mapper.toOrderModel
import com.example.shoppieeclient.data.order.remote.mapper.toOrderProductModel
import com.example.shoppieeclient.domain.order.model.OrderItemModel
import com.example.shoppieeclient.domain.order.model.OrderProductModel
import com.example.shoppieeclient.utils.Constants

class OrdersPagingSource(
    private val shoppieeOrderApiService: ShoppieeOrderApiService
): PagingSource<Int, OrderProductModel>() {

    override fun getRefreshKey(state: PagingState<Int, OrderProductModel>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            state.closestPageToPosition(anchorPosition)?.prevKey
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, OrderProductModel> {
        val currentPage = params.key ?: Constants.INITIAL_PAGE_INDEX
        return try {
            val response = shoppieeOrderApiService.getOrdersData(
                page = currentPage,
                limit = params.loadSize
            )
            val endOfPaginationReached = response.result.orders.isEmpty()
            LoadResult.Page(
                data = response.result.toOrderModel().orders,
                nextKey = if (endOfPaginationReached) null else currentPage + 1,
                prevKey = if (currentPage == Constants.INITIAL_PAGE_INDEX) null else currentPage - 1
            )
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }
}