package com.example.shoppieeclient.domain.home.track_order.models

data class TrackOrderModel(
    val order: OrderModel?
)


data class OrderModel(
    val addressId: String?,
    val createdAt: String?,
    val id: String?,
    val items: List<ItemModel?>?,
    val paymentId: String?,
    val paymentStatus: String?,
    val razorpayId: String?,
    val status: String?,
    val totalAmount: Double?,
    val updatedAt: String?,
    val userId: String?
)



data class ItemModel(
    val product: ProductModel?,
    val productId: String?,
    val quantity: Int?,
    val size: String?
)

data class ProductModel(
    val brand: String?,
    val category: String?,
    val description: String?,
    val images: List<String?>?,
    val inCart: Boolean?,
    val name: String?,
    val price: Int?,
    val productId: String?,
    val quantity: Int?,
    val size: String?
)
