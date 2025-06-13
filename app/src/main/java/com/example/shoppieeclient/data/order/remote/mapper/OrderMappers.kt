package com.example.shoppieeclient.data.order.remote.mapper

import com.example.shoppieeclient.data.order.remote.dto.OrderItemsDto
import com.example.shoppieeclient.data.order.remote.dto.OrderProductDto
import com.example.shoppieeclient.data.order.remote.dto.OrderResultDto
import com.example.shoppieeclient.domain.cart.models.CartResultModel
import com.example.shoppieeclient.domain.order.model.OrderItemModel
import com.example.shoppieeclient.domain.order.model.OrderProductModel
import com.example.shoppieeclient.domain.order.model.OrderResultResponseModel

fun OrderResultDto.toOrderModel(): OrderResultResponseModel {
    return OrderResultResponseModel(
        orders = this.orders.map { it.toOrderProductModel() },
        currentPage = this.currentPage,
        totalOrders = this.totalOrders,
        totalPages = this.totalPages
    )
}

fun OrderItemsDto.toOrderProductModel(): OrderProductModel {
    return OrderProductModel(
        createdAt = this.createdAt,
        orderId = this.orderId,
        items = this.items.map { it.toOrderItemModel() },
        orderStatus = this.orderStatus,
        total = this.total
    )
}

fun OrderProductDto.toOrderItemModel(): OrderItemModel {
    return OrderItemModel(
        image = this.image,
        name = this.name,
        price = this.price,
        quantity = this.quantity,
        size = this.size
    )
}