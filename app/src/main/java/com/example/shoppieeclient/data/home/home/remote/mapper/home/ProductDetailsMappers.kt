package com.example.shoppieeclient.data.home.home.remote.mapper.home

import com.example.shoppieeclient.data.home.home.remote.dto.details.DetailsProductDto
import com.example.shoppieeclient.domain.home.home.models.DetailsProductModel

fun DetailsProductDto.toProductDetailsModel(): DetailsProductModel {
    return DetailsProductModel(
        productId = this.productId,
        name = this.name,
        brand = this.brand,
        description = this.description,
        quantity = this.quantity,
        price = this.price,
        category = this.category,
        images = this.images,
        inCart = this.inCart,
    )
}
