package com.example.shoppieeclient.presentation.home.cart

import android.annotation.SuppressLint
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import androidx.paging.map
import com.example.shoppieeclient.domain.cart.use_cases.DecrementItemUseCase
import com.example.shoppieeclient.domain.cart.use_cases.GetCartTotalUseCase
import com.example.shoppieeclient.domain.cart.use_cases.GetCartUseCase
import com.example.shoppieeclient.domain.cart.use_cases.IncrementItemUseCase
import com.example.shoppieeclient.domain.cart.use_cases.RemoveItemUseCase
import com.example.shoppieeclient.utils.Constants
import com.example.shoppieeclient.utils.Resource
import com.example.shoppieeclient.utils.roundToTwoDecimalPlaces
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import kotlin.coroutines.cancellation.CancellationException

class CartViewModel(
    private val getCartUseCase: GetCartUseCase,
    private val incrementItemUseCase: IncrementItemUseCase,
    private val decrementItemUseCase: DecrementItemUseCase,
    private val removeItemUseCase: RemoveItemUseCase,
    private val getCartTotalUseCase: GetCartTotalUseCase
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


    private fun calculateTotals() = viewModelScope.launch {
        getCartTotalUseCase().collect { result ->
            when(result) {
                is Resource.Error -> {
                    uiState = uiState.copy(error = result.message, isItemLoading = false)
                }
                is Resource.Loading -> {
                    uiState = uiState.copy(isItemLoading = true)
                }
                is Resource.Success -> {
                    val cartTotal = result.data
                    uiState = uiState.copy(
                        isItemLoading = false,
                        subTotal = cartTotal?.totalPrice?.roundToTwoDecimalPlaces() ?: 0.0,
                        platformFees = cartTotal?.platformFee?.roundToTwoDecimalPlaces() ?: 0.0,
                        totalCost = cartTotal?.grandTotal?.roundToTwoDecimalPlaces() ?: 0.0
                    )
                }
            }
        }
    }

    fun onEvent(events: CartEvents) {
        when(events) {
            CartEvents.Checkout -> handleCheckOut()
            is CartEvents.DecrementItem -> handleDecrementItem(events.id)
            is CartEvents.IncrementItem -> handleIncrementItem(events.id, events.size)
            is CartEvents.RemoveCartItem -> showDeleteDialog(events.id)
        }
    }
    private fun showDeleteDialog(id: String) {
        uiState = uiState.copy(
            showDeleteDialog = true,
            selectedItemId = id
        )
    }

    fun confirmDeletion() = viewModelScope.launch {
        uiState.selectedItemId?.let { id ->
            uiState = uiState.copy(showDeleteDialog = false)
            handleRemoveCartItem(id)
        }
    }
    fun dismissDeletionDialog() {
        uiState = uiState.copy(showDeleteDialog = false)
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
    private fun handleRemoveCartItem(id: String) = viewModelScope.launch{
        removeItemUseCase(id).collect { result ->
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

    private fun handleDecrementItem(id: String) = viewModelScope.launch{
        decrementItemUseCase(id).collect { result ->
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

    private fun handleCheckOut() {
        TODO("Not yet implemented")
    }

    fun showToast() {
        uiState = uiState.copy(showToast = true)
        viewModelScope.launch {
            delay(3000)
            uiState = uiState.copy(showToast = false)
        }
    }

}