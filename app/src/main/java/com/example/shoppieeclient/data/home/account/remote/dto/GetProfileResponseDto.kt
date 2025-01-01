package com.example.shoppieeclient.data.home.account.remote.dto

import kotlinx.serialization.Serializable

@Serializable
data class GetProfileResponseDto(
    val status: Int,
    val message: String,
    val result: ProfileDataDto
)



@Serializable
data class ProfileDataDto(
    val email: String,
    val username: String,
    val profileImage: String
)
