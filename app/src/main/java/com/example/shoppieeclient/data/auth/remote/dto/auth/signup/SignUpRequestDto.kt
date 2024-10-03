package com.example.shoppieeclient.data.auth.remote.dto.auth.signup

import kotlinx.serialization.Serializable

@Serializable
data class SignUpRequestDto(
    val name: String,
    val email: String,
    val password: String,
    val confirmPassword: String
)
