package com.example.shoppieeclient.data.home.home.remote.dto.details

import kotlinx.serialization.Serializable

@Serializable
data class AddToCartRequestDto(
    val id: String,
    val region: String ,
    val size: Int
)