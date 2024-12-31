package com.example.shoppieeclient.data.home.home.remote.mapper.home

import com.example.shoppieeclient.data.home.home.remote.dto.details.AddToCartResultDto
import com.example.shoppieeclient.domain.home.home.models.AddToCartResultModel

fun AddToCartResultDto.toAddToCartResultModel(): AddToCartResultModel {
    return AddToCartResultModel(
        cartCount = cartCount,
        status = status
    )
}