package com.example.shoppieeclient.data.home.account.remote.dto

import kotlinx.serialization.Serializable

@Serializable
data class UpdateProfileResponseDto(
    val status: Int,
    val message: String,
    val result: UpdateProfileDataDto
)

@Serializable
data class UpdateProfileDataDto(
    val data: UpdateProfileResultDto
)

@Serializable
data class UpdateProfileResultDto(
    val name: String?= null,
    val profileImage: String?= null
)