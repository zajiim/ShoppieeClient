package com.example.shoppieeclient.presentation.home.checkout

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.shoppieeclient.domain.address.use_cases.GetSelectedAddressUseCase
import com.example.shoppieeclient.domain.cart.use_cases.GetCartTotalUseCase
import com.example.shoppieeclient.domain.checkout.use_cases.StartRPPaymentUseCase
import com.example.shoppieeclient.domain.payment.use_cases.GetSelectedCardUseCase
import com.example.shoppieeclient.utils.Resource
import com.example.shoppieeclient.utils.roundToTwoDecimalPlaces
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

private const val TAG = "CheckOutViewModel"
class CheckOutViewModel(
    private val getSelectedCardUseCase: GetSelectedCardUseCase,
    private val getSelectedAddressUseCase: GetSelectedAddressUseCase,
    private val getCartTotalUseCase: GetCartTotalUseCase,
    private val startRPPaymentUseCase: StartRPPaymentUseCase
): ViewModel() {
    var checkOutState by mutableStateOf(CheckOutStates())
        private set

    init {
        getSelectedCard()
        getSelectedAddress()
        calculateTotals()
    }

    fun refreshData() {
        getSelectedAddress()
        getSelectedCard()
    }

    fun onEvent(event: CheckoutEvents) {
        when(event) {
            is CheckoutEvents.InitiatePayment -> {
                checkOutState = checkOutState.copy(
                    isLoading = true,
                    paymentStatus = PaymentStatus.PROCESSING
                )
                try {
                    startRPPaymentUseCase(
                        amount = checkOutState.totalCost,
                        activity = event.activity,
                        description = event.description
                    )
                }catch (e: Exception) {
                    checkOutState = checkOutState.copy(
                        isLoading = false,
                        paymentStatus = PaymentStatus.FAILED,
                        error = e.message
                    )
                }
            }
            CheckoutEvents.PaymentCancelled -> {
                checkOutState = checkOutState.copy(
                    isLoading = false,
                    paymentStatus = PaymentStatus.CANCELLED
                )
            }
            is CheckoutEvents.PaymentError -> {
                checkOutState = checkOutState.copy(
                    isLoading = false,
                    paymentStatus = PaymentStatus.FAILED,
                    error = event.errorDescription
                )
            }
            is CheckoutEvents.PaymentSuccess -> {
                checkOutState = checkOutState.copy(
                    isLoading = false,
                    paymentStatus = PaymentStatus.SUCCESS,
                    paymentId = event.paymentId
                )
            }
        }
    }

    private fun getSelectedAddress() = viewModelScope.launch {
        getSelectedAddressUseCase().collectLatest { result ->
            Log.e(TAG, "result is ===> $result", )
            when (result) {
                is Resource.Loading -> {
                    checkOutState = checkOutState.copy(isLoading = true)
                }
                is Resource.Success -> {
                    val firstAddress = result.data
                    checkOutState = checkOutState.copy(
                        selectAddress = firstAddress,
                        isLoading = false
                    )
                    Log.e(TAG, "getSelectedAddress: Address result is ${checkOutState.selectAddress}", )
                }
                is Resource.Error -> {
                    Log.e(TAG, "Error is ===> ${result.message}", )
                    checkOutState = checkOutState.copy(
                        selectAddress = emptyList(),
                        isLoading = false
                    )
                }
            }

        }
    }

    private fun getSelectedCard() = viewModelScope.launch {
        getSelectedCardUseCase().collectLatest { selectedCard ->
            checkOutState = checkOutState.copy(
                selectedCard = selectedCard
            )
        }
        Log.e(TAG, "getSelectedCard: ${checkOutState.selectedCard}", )
    }

    private fun calculateTotals() = viewModelScope.launch {
        getCartTotalUseCase().collect { result ->
            when(result) {
                is Resource.Error -> {
                    checkOutState = checkOutState.copy(error = result.message, isLoading = false)
                }
                is Resource.Loading -> {
                    checkOutState = checkOutState.copy(isLoading = true)
                }
                is Resource.Success -> {
                    val cartTotal = result.data
                    checkOutState = checkOutState.copy(
                        isLoading = false,
                        subTotal = cartTotal?.totalPrice?.roundToTwoDecimalPlaces() ?: 0.0,
                        platformFees = cartTotal?.platformFee?.roundToTwoDecimalPlaces() ?: 0.0,
                        totalCost = cartTotal?.grandTotal?.roundToTwoDecimalPlaces() ?: 0.0
                    )
                }
            }
        }
    }


}