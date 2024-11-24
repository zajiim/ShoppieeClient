package com.example.shoppieeclient.domain.cart.repository

import androidx.paging.PagingData
import com.example.shoppieeclient.domain.cart.models.CartProductModel
import com.example.shoppieeclient.domain.cart.models.CartResultModel
import com.example.shoppieeclient.utils.Resource
import kotlinx.coroutines.flow.Flow

interface ShoppieCartRepo {
    fun getCartData(page: Int, limit: Int): Flow<PagingData<CartProductModel>>

//    fun addToCart()
}