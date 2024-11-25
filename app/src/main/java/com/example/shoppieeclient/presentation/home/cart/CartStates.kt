package com.example.shoppieeclient.presentation.home.cart

import androidx.paging.PagingData
import com.example.shoppieeclient.domain.cart.models.CartProductModel
import kotlinx.coroutines.flow.Flow

data class CartStates(
    val isLoading: Boolean = false,
    val error: String? = null,
    val cartItems: Flow<PagingData<CartProductModel>>? = null,
    val subTotal: Double = 0.0,
    val platformFees: Double = 0.0,
    val totalCost: Double = 0.0,
    val showDeleteDialog: Boolean = false,
    val showDecrementDialog: Boolean = false,
    val selectedItemId: String? = null,
    val selectedItemSize: String? = null,
    val isItemLoading: Boolean = false,
    val showToast: Boolean = false
)
