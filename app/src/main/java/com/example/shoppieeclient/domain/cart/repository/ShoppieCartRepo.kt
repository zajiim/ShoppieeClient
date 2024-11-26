package com.example.shoppieeclient.domain.cart.repository

import androidx.paging.PagingData
import com.example.shoppieeclient.domain.cart.models.CartProductModel
import com.example.shoppieeclient.domain.cart.models.CartResultModel
import com.example.shoppieeclient.domain.cart.models.CartTotalModel
import com.example.shoppieeclient.domain.cart.models.IncrementDecrementDeleteCartResponseModel
import com.example.shoppieeclient.domain.cart.models.IncrementDecrementDeleteResultModel
import com.example.shoppieeclient.utils.Resource
import kotlinx.coroutines.flow.Flow

interface ShoppieCartRepo {
    fun getCartData(page: Int, limit: Int): Flow<PagingData<CartProductModel>>

    fun incrementCart(productId: String, size: String): Flow<Resource<IncrementDecrementDeleteResultModel>>

    fun decrementCart(productId: String): Flow<Resource<IncrementDecrementDeleteResultModel>>

    fun removeFromCart(productId: String): Flow<Resource<IncrementDecrementDeleteResultModel>>

    fun getCartTotal(): Flow<Resource<CartTotalModel>>


}