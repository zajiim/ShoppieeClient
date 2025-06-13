package com.example.shoppieeclient.data.home.track_order.remote.api

import com.example.shoppieeclient.data.home.track_order.remote.dto.TrackOrderResponseDto
import com.example.shoppieeclient.utils.Constants
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.http.ContentType
import io.ktor.http.contentType

class ShoppieeTrackOrderApiService(
    private val authorizedHttpClient: HttpClient
) {
    suspend fun getTrackOrderData(orderId: String): TrackOrderResponseDto {
        return authorizedHttpClient.get("${Constants.SHOPPIEE_URL}/get-order") {
            url {
                parameters.append("id", orderId)
            }
            contentType(ContentType.Application.Json)
        }.body()
    }
}