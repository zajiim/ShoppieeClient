package com.example.shoppieeclient.data.cart.remote.dto

import kotlinx.serialization.Serializable


@Serializable
data class IncrementDecrementDeleteCartResponseDto(
    val status: Int,
    val message: String,
    val result: IncrementDecrementDeleteResultDto
)

@Serializable
data class IncrementDecrementDeleteResultDto(
    val cartCount: Int,
    val status: String
)
