package com.example.shoppieeclient.data.checkout.remote.api

import com.example.shoppieeclient.data.checkout.remote.dto.CreateOrderRequestDto
import com.example.shoppieeclient.data.checkout.remote.dto.OrderResponseDto
import com.example.shoppieeclient.utils.Constants
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.post
import io.ktor.client.request.setBody
import io.ktor.http.ContentType
import io.ktor.http.contentType

class ShoppieCheckoutApiService(
    private val authorizedHttpClient: HttpClient
) {
    suspend fun createOrder(orderRequestDto: CreateOrderRequestDto): OrderResponseDto {
        return authorizedHttpClient.post("${Constants.SHOPPIEE_URL}/create-order") {
            contentType(ContentType.Application.Json)
            setBody(orderRequestDto)
        }.body()
    }
}