package com.example.shoppieeclient.data.checkout.remote.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable


@Serializable
data class OrderResponseDto(
    val status: Int,
    val message: String,
    val result: RazorPayOrderResponseModelDto
)

@Serializable
data class RazorPayOrderResponseModelDto(
    val orderId: String,
    val razorpayId: String,
    val amount: Int,
    val currency: String,
    @SerialName("key_id") val keyId: String
)
