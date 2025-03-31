package com.example.shoppieeclient.domain.payment.models

data class PaymentCardModel(
    val id: Int = 0,
    val cardNumber: String,
    val cardHolderName: String,
    val expirationDate: String,
    val cvv: String,
)
