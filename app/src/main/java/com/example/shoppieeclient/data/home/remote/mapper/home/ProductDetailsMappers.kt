package com.example.shoppieeclient.data.home.remote.mapper.home

import com.example.shoppieeclient.data.home.remote.dto.home.ProductDetailsDto
import com.example.shoppieeclient.domain.auth.models.home.HomeProductModel

fun ProductDetailsDto.toProductDetailsModel(): HomeProductModel {
    return HomeProductModel(
        productId = this.productId,
        name = this.name,
        brand = this.brand,
        description = this.description,
        quantity = this.quantity,
        price = this.price,
        category = this.category,
        images = this.images,
    )
}