package com.example.shoppieeclient.data.home.remote.dto.home

import kotlinx.serialization.Serializable

@Serializable
data class ProductDetailsResponseDto(
    val status: Int,
    val message: String,
    val result: ProductDetailsDto? = null
)

@Serializable
data class ProductDetailsDto(
    val productId: String,
    val name: String,
    val brand: String,
    val description: String,
    val quantity: Int,
    val price: Double,
    val category: String,
    val images: List<String>
)
