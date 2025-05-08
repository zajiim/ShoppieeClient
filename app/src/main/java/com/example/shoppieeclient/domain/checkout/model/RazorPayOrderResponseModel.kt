package com.example.shoppieeclient.domain.checkout.model

import kotlinx.serialization.SerialName

data class RazorPayOrderResponseModel(
    val orderId: String,
    val razorpayId: String,
    val amount: Int,
    val currency: String,
    @SerialName("key_id") val keyId: String
)
