package com.example.shoppieeclient.data.auth.remote.dto.auth.signin

import kotlinx.serialization.Serializable

@Serializable
data class SingInRequestDto(
    val email: String,
    val password: String
)
