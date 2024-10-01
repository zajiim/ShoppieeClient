package com.example.shoppieeclient.data.auth.remote.api

import com.example.shoppieeclient.data.auth.remote.dto.signup.SignUpRequestDto
import com.example.shoppieeclient.data.auth.remote.dto.signup.SignUpResponseDto

interface ShoppieeApi {
    suspend fun signUp(
        signUpRequestDto: SignUpRequestDto
    ): SignUpResponseDto
}