package com.example.shoppieeclient.presentation.home.cart

import android.annotation.SuppressLint
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.LoadState
import androidx.paging.cachedIn
import androidx.paging.compose.LazyPagingItems
import com.example.shoppieeclient.domain.cart.use_cases.GetCartUseCase
import com.example.shoppieeclient.utils.Constants
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import kotlin.coroutines.cancellation.CancellationException
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.paging.map
import com.example.shoppieeclient.domain.cart.models.CartProductModel
import com.example.shoppieeclient.domain.cart.use_cases.DecrementItemUseCase
import com.example.shoppieeclient.domain.cart.use_cases.IncrementItemUseCase
import com.example.shoppieeclient.domain.cart.use_cases.RemoveItemUseCase
import com.example.shoppieeclient.utils.Resource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.flowOn

class CartViewModel(
    private val getCartUseCase: GetCartUseCase,
    private val incrementItemUseCase: IncrementItemUseCase,
    private val decrementItemUseCase: DecrementItemUseCase,
    private val removeItemUseCase: RemoveItemUseCase
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
            calculateTotals()

        } catch (e: Exception) {
            uiState = uiState.copy(
                isLoading = false,
                error = e.message
            )
            if (e is CancellationException) throw e
        }
    }


    @SuppressLint("CheckResult")
    private fun calculateTotals() = viewModelScope.launch {
        uiState.cartItems?.collectLatest { pagingData ->
            var subTotal = 0.0
            pagingData.map { cartItem ->
                subTotal += cartItem.price * cartItem.cartItemCount
            }
            val platformFees = subTotal * 0.01
            val totalCost = subTotal + platformFees

            uiState = uiState.copy(
                subTotal = subTotal,
                platformFees = platformFees,
                totalCost = totalCost
            )
        }

    }




    fun onEvent(events: CartEvents) {
        when(events) {
            CartEvents.Checkout -> handleCheckOut()
            is CartEvents.DecrementItem -> handleDecrementItem(events.id)
            is CartEvents.IncrementItem -> handleIncrementItem(events.id, events.size)
            is CartEvents.RemoveCartItem -> handleRemoveCartItem(events.id)
        }
    }

    private fun handleIncrementItem(id: String, size: String) = viewModelScope.launch {
        incrementItemUseCase(id, size).collect { result ->
            when(result) {
                is Resource.Error -> {
                    uiState = uiState.copy(error = result.message, isItemLoading = false)
                }
                is Resource.Loading -> {
                    uiState = uiState.copy(isItemLoading = true)
                }
                is Resource.Success -> {
                    uiState = uiState.copy(isItemLoading = false)
                    fetchCartItems()
                }
            }
        }
    }

    private fun handleRemoveCartItem(id: String) {}

    private fun handleDecrementItem(id: String) {}

    private fun handleCheckOut() {
        TODO("Not yet implemented")
    }

}