package com.example.shoppieeclient.data.auth.remote.api

import com.example.shoppieeclient.data.auth.remote.dto.signup.SignUpRequestDto
import com.example.shoppieeclient.data.auth.remote.dto.signup.SignUpResponseDto
import com.example.shoppieeclient.utils.Constants
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.post
import io.ktor.client.request.setBody
import io.ktor.http.ContentType
import io.ktor.http.contentType


class ShoppieApiImpl(
    private val client: HttpClient
): ShoppieeApi {
    override suspend fun signUp(signUpRequestDto: SignUpRequestDto): SignUpResponseDto {
        return client.post("${Constants.SHOPPIEE_URL}/signup") {
            contentType(ContentType.Application.Json)
            setBody(signUpRequestDto)
        }.body()
    }
}