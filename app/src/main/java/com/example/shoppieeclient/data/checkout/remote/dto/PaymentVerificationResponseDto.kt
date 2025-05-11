package com.example.shoppieeclient.data.checkout.remote.dto

import kotlinx.serialization.Serializable

@Serializable
data class PaymentVerificationResponseDto(
    val status: Int,
    val message: String,
    val result: PaymentVerificationResultDto
)

@Serializable
data class PaymentVerificationResultDto(
    val orderId: String,
    val paymentId: String,
    val razorpayId: String
)