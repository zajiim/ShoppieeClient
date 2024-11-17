package com.example.shoppieeclient.domain.cart.models



data class CartResultResponseModel(
    val cartItems: List<CartResultModel>,
    val currentPage: Int,
    val status: String,
    val totalCartItems: Int,
    val totalPages: Int
)

data class CartResultModel(
    val product: CartProductModel,
    val quantity: Int
)

data class CartProductModel(
    val productId: String,
    val name: String,
    val brand: String,
    val description: String,
    val quantity: Int,
    val price: Double,
    val category: String,
    val images: List<String>,
    val inCart: Boolean,
    val cartItemCount: Int
)