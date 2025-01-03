package com.example.shoppieeclient.data.auth.remote.dto.auth.signin

import com.example.shoppieeclient.data.cart.remote.dto.CartItemsDto
import kotlinx.serialization.Serializable

@Serializable
data class SignInResponseDto(
    val status: Int,
    val message: String,
    val result: Result?
)

@Serializable
data class Result(
    val data: SignInUserData,
)


@Serializable
data class SignInUserData(
    val id: String,
    val name: String,
    val email: String,
    val type: String,
    val cart: List<CartItemDto>,
    val token: String,
    val profileImage: String,
)

@Serializable
data class CartItemDto(
    val product: Product,
    val quantity: Int
)

@Serializable
data class Product(
    val productId: String,
    val name: String,
    val brand: String,
    val description: String,
    val quantity: Int,
    val price: Double,
    val category: String,
    val images: List<String>,
    val inCart: Boolean,
    val size: String
)