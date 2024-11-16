package com.example.shoppieeclient.presentation.home.cart

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.example.shoppieeclient.domain.cart.models.CartProductModel
import com.example.shoppieeclient.domain.cart.use_cases.GetCartUseCase
import com.example.shoppieeclient.utils.Constants
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class CartViewModel(
    private val getCartUseCase: GetCartUseCase
): ViewModel() {
//    private val _cartItems = MutableStateFlow<PagingData<CartProductModel>>(PagingData.empty())
//    val cartItems = _cartItems.asStateFlow()


//    init {
//        getCartItems()
//    }

//    private fun getCartItems() = viewModelScope.launch {
//        withContext(Dispatchers.IO) {
//            getCartUseCase(
//                page = Constants.INITIAL_PAGE_INDEX,
//                limit = Constants.PER_PAGE_ITEMS
//            ).collect { cartsPagingData ->
//                _cartItems.value = cartsPagingData
//            }
//        }
//
//    }

    val cartItems: Flow<PagingData<CartProductModel>> =
        getCartUseCase(
            page = Constants.INITIAL_PAGE_INDEX,
            limit = Constants.PER_PAGE_ITEMS
        ).cachedIn(viewModelScope)
}