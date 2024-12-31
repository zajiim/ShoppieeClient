package com.example.shoppieeclient.data.home.account.remote.dto

import kotlinx.serialization.Serializable

@Serializable
data class UpdateProfileBody(
    val name: String,
    val profileImage: String
)
