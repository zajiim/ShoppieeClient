package com.example.shoppieeclient.data.order.remote.api

import com.example.shoppieeclient.data.order.remote.dto.OrderResponseDto
import com.example.shoppieeclient.utils.Constants
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.http.ContentType
import io.ktor.http.contentType

class ShoppieeOrderApiService(
    private val authorizedHttpClient: HttpClient
) {

    suspend fun getOrdersData(page: Int, limit: Int): OrderResponseDto {
        return authorizedHttpClient.get("${Constants.SHOPPIEE_URL}/get-orders") {
            url {
                parameters.append("page", page.toString())
                parameters.append("limit", limit.toString())
            }
            contentType(ContentType.Application.Json)
        }.body()
    }
}