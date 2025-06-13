package com.example.shoppieeclient.data.order.remote.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable


@Serializable
data class OrderResponseDto(
    val status: Int,
    val message: String,
    val result: OrderResultDto
)

@Serializable
data class OrderResultDto(
    val orders: List<OrderItemsDto>,
    val currentPage: Int,
    val totalOrders: Int,
    val totalPages: Int
)

@Serializable
data class OrderItemsDto(
    val createdAt: String,
    @SerialName("id") val orderId: String,
    val items: List<OrderProductDto>,
    @SerialName("status") val orderStatus: String,
    val total: Double,
)

@Serializable
data class OrderProductDto(
    val image: String,
    val name: String,
    val price: Double,
    val quantity: Int,
    val size: String
)