package com.example.shoppieeclient.data.auth.remote.dto.auth.oauth_signin

import kotlinx.serialization.Serializable

@Serializable
data class OAuthSignInRequest(
    val provider: String,
    val token: String
)
