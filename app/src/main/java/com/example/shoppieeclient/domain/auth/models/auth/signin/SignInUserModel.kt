package com.example.shoppieeclient.domain.auth.models.auth.signin


data class SignInUserModel(
    val id: String,
    val name: String,
    val email: String,
    val userType: String,
    val cartItems: List<CartItemModel>,
    val token: String,
    val profileImage: String,
)

data class CartItemModel(
    val product: ProductModel,
    val quantity: Int
)

data class ProductModel(
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
