package com.example.shoppieeclient.domain.home.home.models

data class DetailsProductModel(
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