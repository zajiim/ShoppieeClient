package com.example.shoppieeclient.data.cart.remote.dto

import kotlinx.serialization.Serializable

@Serializable
data class DecrementDeleteCartItemRequestDto(
    val id: String
)
