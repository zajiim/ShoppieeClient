package com.example.shoppieeclient.data.cart.remote.dto

import kotlinx.serialization.Serializable

@Serializable
data class CartTotalResponseDto(
    val status: Int,
    val message: String,
    val result: CartTotalDto
)


@Serializable
data class CartTotalDto(
    val totalPrice: Double,
    val platformFee: Double,
    val grandTotal: Double
)
