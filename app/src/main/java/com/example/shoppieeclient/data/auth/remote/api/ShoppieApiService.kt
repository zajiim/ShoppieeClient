package com.example.shoppieeclient.data.auth.remote.api

import com.example.shoppieeclient.data.auth.remote.dto.auth.signin.SignInResponseDto
import com.example.shoppieeclient.data.auth.remote.dto.auth.signin.SingInRequestDto
import com.example.shoppieeclient.data.auth.remote.dto.auth.signup.SignUpRequestDto
import com.example.shoppieeclient.data.auth.remote.dto.auth.signup.SignUpResponseDto
import com.example.shoppieeclient.utils.Constants
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.post
import io.ktor.client.request.setBody
import io.ktor.http.ContentType
import io.ktor.http.contentType


class ShoppieApiService(
    private val client: HttpClient
) {
     suspend fun signUp(signUpRequestDto: SignUpRequestDto): SignUpResponseDto {
        return client.post("${Constants.SHOPPIEE_URL}/signup") {
            contentType(ContentType.Application.Json)
            setBody(signUpRequestDto)
        }.body()
    }

    suspend fun signIn(signInRequestDto: SingInRequestDto): SignInResponseDto {
        return client.post("${Constants.SHOPPIEE_URL}/signin") {
            contentType(ContentType.Application.Json)
            setBody(signInRequestDto)
        }.body()
    }
}