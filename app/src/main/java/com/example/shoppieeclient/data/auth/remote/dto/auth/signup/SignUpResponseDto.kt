package com.example.shoppieeclient.data.auth.remote.dto.auth.signup

import kotlinx.serialization.Serializable

@Serializable
data class SignUpResponseDto(
    val status: Int,
    val message: String,
    val result: Result?
)

@Serializable
data class Result(
    val data: SignUpUserData?
)

@Serializable
data class SignUpUserData (
    val id: String,
    val name: String,
    val email: String,
    val type: String,
    val cart: List<Product>
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
    val images: List<String>
)