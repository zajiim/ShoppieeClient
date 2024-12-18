package com.example.shoppieeclient.domain.auth.models.auth.signin


data class SignInUserModel(
    val id: String,
    val name: String,
    val email: String,
    val userType: String,
    val cartItems: List<ProductModel>,
    val token: String,
    val profileImage: String,
)
data class ProductModel(
    val productId: String,
    val name: String,
    val brand: String,
    val description: String,
    val quantity: Int,
    val price: Double,
    val category: String,
    val images: List<String>
)
