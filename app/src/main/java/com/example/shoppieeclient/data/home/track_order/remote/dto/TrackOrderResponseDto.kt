package com.example.shoppieeclient.data.home.track_order.remote.dto


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class TrackOrderResponseDto(
    @SerialName("message")
    val message: String?,
    @SerialName("result")
    val result: Result?,
    @SerialName("status")
    val status: Int?
)


@Serializable
data class Result(
    @SerialName("order")
    val order: Order?
)


@Serializable
data class Order(
    @SerialName("addressId")
    val addressId: String?,
    @SerialName("createdAt")
    val createdAt: String?,
    @SerialName("id")
    val id: String?,
    @SerialName("items")
    val items: List<Item?>?,
    @SerialName("paymentId")
    val paymentId: String?,
    @SerialName("paymentStatus")
    val paymentStatus: String?,
    @SerialName("razorpayId")
    val razorpayId: String?,
    @SerialName("status")
    val status: String?,
    @SerialName("totalAmount")
    val totalAmount: Double?,
    @SerialName("updatedAt")
    val updatedAt: String?,
    @SerialName("userId")
    val userId: String?
)



@Serializable
data class Item(
    @SerialName("product")
    val product: Product?,
    @SerialName("productId")
    val productId: String?,
    @SerialName("quantity")
    val quantity: Int?,
    @SerialName("size")
    val size: String?
)

@Serializable
data class Product(
    @SerialName("brand")
    val brand: String?,
    @SerialName("category")
    val category: String?,
    @SerialName("description")
    val description: String?,
    @SerialName("images")
    val images: List<String?>?,
    @SerialName("inCart")
    val inCart: Boolean?,
    @SerialName("name")
    val name: String?,
    @SerialName("price")
    val price: Int?,
    @SerialName("productId")
    val productId: String?,
    @SerialName("quantity")
    val quantity: Int?,
    @SerialName("size")
    val size: String?
)


