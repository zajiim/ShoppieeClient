package com.example.shoppieeclient.data.home.remote.dto.details

import kotlinx.serialization.Serializable

@Serializable
data class DetailsResponseDto(
    val status: Int,
    val message: String,
    val result: ProductResultDto? = null
)

@Serializable
data class ProductResultDto(
    val product: DetailsProductDto? = null,
    val status: String? = null
)

@Serializable
data class DetailsProductDto(
    val productId: String,
    val name: String,
    val brand: String,
    val description: String,
    val quantity: Int,
    val price: Double,
    val category: String,
    val images: List<String>,
    val inCart: Boolean
)