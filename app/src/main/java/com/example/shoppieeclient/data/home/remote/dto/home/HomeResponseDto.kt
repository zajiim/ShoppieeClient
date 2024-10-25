package com.example.shoppieeclient.data.home.remote.dto.home

import kotlinx.serialization.Serializable


@Serializable
data class HomeResponseDto(
    val status: Int,
    val message: String,
    val result: ResultDto? = null
)


@Serializable
data class ResultDto(
    val popular: List<ProductDto>? = null,
    val newArrivals: List<ProductDto>? = null
)


@Serializable
data class ProductDto(
    val productId: String,
    val name: String,
    val brand: String,
    val description: String,
    val quantity: Int,
    val price: Double,
    val category: String,
    val images: List<String>
)