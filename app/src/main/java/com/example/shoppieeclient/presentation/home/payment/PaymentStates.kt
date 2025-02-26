package com.example.shoppieeclient.presentation.home.payment

import com.example.shoppieeclient.domain.payment.PaymentCardModel

data class PaymentStates(
    val paymentCards: List<PaymentCardModel>?=null,
    val isAddCardClicked: Boolean = false,
)