package com.example.shoppieeclient.domain.auth.models.home

data class HomeResultModel(
    val popularItemsModel: List<HomeProductModel>,
    val newArrivalItemsModel: List<HomeProductModel>
)


data class HomeProductModel(
    val productId: String,
    val name: String,
    val brand: String,
    val description: String,
    val quantity: Int,
    val price: Double,
    val category: String,
    val images: List<String>
)