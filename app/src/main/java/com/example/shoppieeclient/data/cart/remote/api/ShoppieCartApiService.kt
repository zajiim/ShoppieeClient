package com.example.shoppieeclient.data.cart.remote.api

import com.example.shoppieeclient.data.cart.remote.dto.CartResponseDto
import com.example.shoppieeclient.data.cart.remote.dto.CartResultDto
import com.example.shoppieeclient.utils.Constants
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.http.ContentType
import io.ktor.http.contentType

class ShoppieCartApiService(
    private val authorizedHttpClient: HttpClient
) {
    suspend fun getCartsData(page: Int, limit: Int): CartResponseDto {
        return authorizedHttpClient.get("${Constants.SHOPPIEE_URL}/fetchCartItems") {
            url {
                parameters.append("page", page.toString())
                parameters.append("limit", limit.toString())
            }
            contentType(ContentType.Application.Json)
        }.body()
    }
}