package com.example.shoppieeclient.data.cart.remote.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class CartResponseDto(
    val status: Int,
    val message: String,
    val result: CartResultDto
)

@Serializable
data class CartResultDto(
    val cartItems: List<CartItemsDto>,
    val currentPage: Int,
    val status: String,
    val totalCartItems: Int,
    val totalPages: Int
)

@Serializable
data class CartItemsDto(
    @SerialName("product")
    val product: CartProductDto,
    val quantity: Int
)

@Serializable
data class CartProductDto(
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