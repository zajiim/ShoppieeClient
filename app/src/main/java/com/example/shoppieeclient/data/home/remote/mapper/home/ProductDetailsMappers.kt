package com.example.shoppieeclient.data.home.remote.mapper.home

import com.example.shoppieeclient.data.home.remote.dto.details.DetailsProductDto
import com.example.shoppieeclient.data.home.remote.dto.home.ProductDetailsDto
import com.example.shoppieeclient.domain.auth.models.home.DetailsProductModel

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
    )
}
