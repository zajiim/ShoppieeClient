package com.example.shoppieeclient.data.address.remote.api

import com.example.shoppieeclient.data.address.remote.dto.AddressResponse
import com.example.shoppieeclient.utils.Constants
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.http.ContentType
import io.ktor.http.contentType

class AddressApiService(
    private val httpClient: HttpClient
) {
    suspend fun getAddresses(): AddressResponse {
        return httpClient.get("${Constants.SHOPPIEE_URL}/get-addresses") {
            contentType(ContentType.Application.Json)
        }.body()
    }
}