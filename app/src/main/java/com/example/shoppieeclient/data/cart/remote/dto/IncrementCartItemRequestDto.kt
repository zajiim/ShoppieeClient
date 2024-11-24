package com.example.shoppieeclient.data.cart.remote.dto

import kotlinx.serialization.Serializable

@Serializable
data class IncrementCartItemRequestDto(
    val id: String,
    val size: String
)



