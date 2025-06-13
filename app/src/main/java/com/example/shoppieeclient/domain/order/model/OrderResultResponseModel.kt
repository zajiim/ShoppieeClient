package com.example.shoppieeclient.domain.order.model

data class OrderResultResponseModel(
    val orders: List<OrderProductModel>,
    val currentPage: Int,
    val totalOrders: Int,
    val totalPages: Int
)

data class OrderProductModel(
    val createdAt: String,
    val orderId: String,
    val items: List<OrderItemModel>,
    val orderStatus: String,
    val total: Double,
)

data class OrderItemModel(
    val image: String,
    val name: String,
    val price: Double,
    val quantity: Int,
    val size: String
)