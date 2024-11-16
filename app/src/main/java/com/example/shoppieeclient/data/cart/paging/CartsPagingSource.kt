package com.example.shoppieeclient.data.cart.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.shoppieeclient.data.cart.remote.api.ShoppieCartApiService
import com.example.shoppieeclient.data.cart.remote.mapper.toCartProductModel
import com.example.shoppieeclient.domain.cart.models.CartProductModel
import com.example.shoppieeclient.domain.cart.models.CartResultModel
import com.example.shoppieeclient.utils.Constants

class CartsPagingSource(
    private val shoppieCartApi: ShoppieCartApiService
): PagingSource<Int, CartProductModel>() {
    override fun getRefreshKey(state: PagingState<Int, CartProductModel>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            state.closestPageToPosition(anchorPosition)?.prevKey
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, CartProductModel> {
        val currentPage = params.key ?: Constants.INITIAL_PAGE_INDEX
        return try {
            val response = shoppieCartApi.getCartsData(
                page = currentPage,
                limit = params.loadSize
            )
            val endOfPaginationReached = response.result.cartItems.isEmpty()
            LoadResult.Page(
                data = response.result.toCartProductModel().cartItems.map { it.product },
                nextKey = if (endOfPaginationReached) null else currentPage + 1,
                prevKey = if (currentPage == Constants.INITIAL_PAGE_INDEX) null else currentPage - 1
            )

        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }
}