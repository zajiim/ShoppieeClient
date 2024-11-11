package com.example.shoppieeclient.data.home.remote.mapper.home

import com.example.shoppieeclient.data.home.remote.dto.details.AddToCartResultDto
import com.example.shoppieeclient.domain.auth.models.home.AddToCartResultModel

fun AddToCartResultDto.toAddToCartResultModel(): AddToCartResultModel {
    return AddToCartResultModel(
        cartCount = cartCount,
        status = status
    )
}