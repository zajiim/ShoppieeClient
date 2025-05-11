package com.example.shoppieeclient.domain.checkout.model

data class PaymentVerificationResponseModel(
    val orderId: String,
    val paymentId: String,
    val razorpayId: String
)
