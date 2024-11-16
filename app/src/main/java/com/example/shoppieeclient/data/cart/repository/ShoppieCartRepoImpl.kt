package com.example.shoppieeclient.data.cart.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.example.shoppieeclient.data.cart.paging.CartsPagingSource
import com.example.shoppieeclient.data.cart.remote.api.ShoppieCartApiService
import com.example.shoppieeclient.domain.cart.models.CartProductModel
import com.example.shoppieeclient.domain.cart.models.CartResultModel
import com.example.shoppieeclient.domain.cart.repository.ShoppieCartRepo
import com.example.shoppieeclient.utils.Constants
import com.example.shoppieeclient.utils.Resource
import kotlinx.coroutines.flow.Flow

class ShoppieCartRepoImpl(
    private val shoppieCartApi: ShoppieCartApiService
): ShoppieCartRepo {
    override fun getCartData(
        page: Int,
        limit: Int
    ): Flow<PagingData<CartProductModel>> {
        return Pager(
            config = PagingConfig(
                pageSize = Constants.PER_PAGE_ITEMS
            ),
            pagingSourceFactory = {
                CartsPagingSource(shoppieCartApi)
            }
        ).flow
    }
}