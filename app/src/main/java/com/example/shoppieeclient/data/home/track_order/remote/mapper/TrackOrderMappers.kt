package com.example.shoppieeclient.data.home.track_order.remote.mapper

import com.example.shoppieeclient.data.home.track_order.remote.dto.Item
import com.example.shoppieeclient.data.home.track_order.remote.dto.Order
import com.example.shoppieeclient.data.home.track_order.remote.dto.Product
import com.example.shoppieeclient.data.home.track_order.remote.dto.Result
import com.example.shoppieeclient.domain.home.track_order.models.ItemModel
import com.example.shoppieeclient.domain.home.track_order.models.OrderModel
import com.example.shoppieeclient.domain.home.track_order.models.ProductModel
import com.example.shoppieeclient.domain.home.track_order.models.TrackOrderModel

fun Result.toTrackOrderModel(): TrackOrderModel {
    return TrackOrderModel(
        order = this.order?.toTrackOrderDetailsModel()
    )
}

fun Order.toTrackOrderDetailsModel(): OrderModel {
    return OrderModel(
        id = this.id ?: "",
        status = this.status ?: "",
        createdAt = this.createdAt ?: "",
        updatedAt = this.updatedAt ?: "",
        totalAmount = this.totalAmount ?: 0.0,
        addressId = this.addressId ?: "",
        paymentId = this.paymentId ?: "",
        paymentStatus = this.paymentStatus ?: "",
        razorpayId = this.razorpayId ?: "",
        userId = this.userId ?: "",
        items = this.items?.map { it?.toItemModel() } ?: emptyList()
    )
}

fun Item.toItemModel(): ItemModel {
    return ItemModel(
        productId = this.productId ?: "",
        quantity = this.quantity ?: 0,
        size = this.size ?: "",
        product = this.product?.toProductModel()
    )
}


fun Product.toProductModel(): ProductModel {
    return ProductModel(
        productId = this.productId ?: "",
        name = this.name ?: "",
        description = this.description ?: "",
        price = this.price ?: 0,
        brand = this.brand ?: "",
        category = this.category ?: "",
        images = this.images?.map { it ?: "" } ?: emptyList(),
        inCart = false,
        quantity = 0,
        size = this.size ?: ""
    )
}