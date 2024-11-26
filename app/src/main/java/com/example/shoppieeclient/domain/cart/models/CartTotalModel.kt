package com.example.shoppieeclient.domain.cart.models

data class CartTotalModel(
    val totalPrice: Double,
    val platformFee: Double,
    val grandTotal: Double
)