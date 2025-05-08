package com.example.shoppieeclient.data.checkout.remote.dto

import kotlinx.serialization.Serializable


@Serializable
data class CreateOrderRequestDto(
    val addressId: String,
    val amount: Double,
    val currency: String
)
