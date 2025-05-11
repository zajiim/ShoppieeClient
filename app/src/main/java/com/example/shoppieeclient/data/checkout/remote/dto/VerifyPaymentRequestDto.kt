package com.example.shoppieeclient.data.checkout.remote.dto

import kotlinx.serialization.Serializable

@Serializable
data class VerifyPaymentRequestDto(
    val orderId: String,
    val paymentId: String,
    val signature: String,
    val razorpayId: String
)
