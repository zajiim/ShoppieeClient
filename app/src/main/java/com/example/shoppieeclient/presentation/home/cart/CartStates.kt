package com.example.shoppieeclient.presentation.home.cart

import androidx.paging.PagingData
import com.example.shoppieeclient.domain.cart.models.CartProductModel
import kotlinx.coroutines.flow.Flow

data class CartStates(
    val isLoading: Boolean = false,
    val error: String? = null,
    val cartItems: Flow<PagingData<CartProductModel>>? = null,
)
