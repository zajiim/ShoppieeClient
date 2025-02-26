package com.example.shoppieeclient.presentation.home.payment

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue

class PaymentViewModel: ViewModel() {
    var paymentState by mutableStateOf(PaymentStates())
        private set


    fun onEvent(event: PaymentEvents) {
        when(event) {
            PaymentEvents.AddCardClicked -> {
                paymentState = paymentState.copy(
                    isAddCardClicked = true
                )
            }

            PaymentEvents.DismissBottomSheet -> {
                paymentState = paymentState.copy(
                    isAddCardClicked = false
                )
            }

            is PaymentEvents.CardCvvChanged -> {

            }
            is PaymentEvents.CardExpiryChanged -> {

            }
            is PaymentEvents.CardHolderNameChanged -> {

            }
            is PaymentEvents.CardNumberChanged -> {

            }
        }
    }
}