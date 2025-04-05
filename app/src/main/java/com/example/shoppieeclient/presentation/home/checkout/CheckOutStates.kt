package com.example.shoppieeclient.presentation.home.checkout

import com.example.shoppieeclient.domain.payment.models.PaymentCardModel

data class CheckOutStates(
    val selectedCard: PaymentCardModel? = null,
    val isLoading: Boolean = false
)