package com.example.shoppieeclient.data.auth.remote.dto.auth

import kotlinx.serialization.Serializable

@Serializable
data class ValidUserResponseDto(
    val status: Int,
    val message: String,
    val result: Nothing?
)
