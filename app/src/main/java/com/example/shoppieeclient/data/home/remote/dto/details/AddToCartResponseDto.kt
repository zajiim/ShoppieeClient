package com.example.shoppieeclient.data.home.remote.dto.details

import kotlinx.serialization.Serializable


@Serializable
data class AddToCartResponseDto(
    val status: Int,
    val message: String,
    val result: AddToCartResultDto
)


@Serializable
data class AddToCartResultDto(
    val cartCount: Int,
    val status: String
)