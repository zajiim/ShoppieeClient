package com.example.shoppieeclient.domain.auth.models.auth

data class FacebookAccount(
    val token: String,
    val displayName: String,
    val profileImageUrl: String
)
