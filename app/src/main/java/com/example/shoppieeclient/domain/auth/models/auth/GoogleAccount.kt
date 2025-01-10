package com.example.shoppieeclient.domain.auth.models.auth

data class GoogleAccount(
    val token: String,
    val displayName: String,
    val profileImageUrl: String
)
