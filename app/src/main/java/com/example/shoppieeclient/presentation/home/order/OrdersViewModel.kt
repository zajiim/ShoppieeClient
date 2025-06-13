package com.example.shoppieeclient.presentation.home.order

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.example.shoppieeclient.domain.order.use_case.GetOrdersUseCase
import com.example.shoppieeclient.utils.Constants
import kotlinx.coroutines.launch
import kotlin.coroutines.cancellation.CancellationException

class OrdersViewModel(
    private val getOrdersUseCase: GetOrdersUseCase
): ViewModel() {
    var ordersUiStates by mutableStateOf(OrdersStates())
        private set


    init {
        fetchOrders()
    }

    private fun fetchOrders() = viewModelScope.launch {
        ordersUiStates = ordersUiStates.copy(
            isLoading = true
        )
        try {
            val ordersFlow = getOrdersUseCase(
                page = Constants.INITIAL_PAGE_INDEX,
                limit = Constants.PER_PAGE_ITEMS
            ).cachedIn(viewModelScope)

            ordersUiStates = ordersUiStates.copy(
                isLoading = false,
                orders = ordersFlow
            )
        } catch (e: Exception) {
            ordersUiStates = ordersUiStates.copy(
                isLoading = false,
                error = e.message
            )
            if (e is CancellationException) throw e
        }
    }

}