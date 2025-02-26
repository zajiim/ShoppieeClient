package com.example.shoppieeclient.domain.payment

data class PaymentCardModel(
    val id: String,
    val userId: String,
    val cardNumber: String,
    val cardHolderName: String,
    val expirationDate: String,
    val cvv: String,
)
