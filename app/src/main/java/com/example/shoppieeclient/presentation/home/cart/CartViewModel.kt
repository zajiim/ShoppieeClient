package com.example.shoppieeclient.presentation.home.cart

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import androidx.paging.compose.LazyPagingItems
import com.example.shoppieeclient.domain.cart.use_cases.GetCartUseCase
import com.example.shoppieeclient.utils.Constants
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import kotlin.coroutines.cancellation.CancellationException
import androidx.paging.compose.collectAsLazyPagingItems
import com.example.shoppieeclient.domain.cart.models.CartProductModel

class CartViewModel(
    private val getCartUseCase: GetCartUseCase
) : ViewModel() {


    var uiState by mutableStateOf(CartStates())
        private set


    init {
        fetchCartItems()
    }

    private fun fetchCartItems() = viewModelScope.launch {
        uiState = uiState.copy(isLoading = true)
        try {
            val cartItemsFlow = getCartUseCase(
                page = Constants.INITIAL_PAGE_INDEX,
                limit = Constants.PER_PAGE_ITEMS
            ).cachedIn(viewModelScope)

            uiState = uiState.copy(
                isLoading = false,
                cartItems = cartItemsFlow
            )

        } catch (e: Exception) {
            uiState = uiState.copy(
                isLoading = false,
                error = e.message
            )
            if (e is CancellationException) throw e
        }
    }

}