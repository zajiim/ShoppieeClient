package com.example.shoppieeclient.domain.cart.models



data class IncrementDecrementDeleteCartResponseModel(
    val status: Int,
    val message: String,
    val result: IncrementDecrementDeleteResultModel
)

data class IncrementDecrementDeleteResultModel(
    val cartCount: Int,
    val status: String
)

