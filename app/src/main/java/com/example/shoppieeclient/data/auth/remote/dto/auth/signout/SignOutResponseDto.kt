package com.example.shoppieeclient.data.auth.remote.dto.auth.signout

import kotlinx.serialization.Serializable

@Serializable
data class SignOutResponseDto(
    val status: Int,
    val message: String,
)
