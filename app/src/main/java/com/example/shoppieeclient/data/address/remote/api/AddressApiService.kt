package com.example.shoppieeclient.data.address.remote.api

import android.util.Log
import com.example.shoppieeclient.data.address.remote.dto.AddressRequest
import com.example.shoppieeclient.data.address.remote.dto.AddressResponse
import com.example.shoppieeclient.utils.Constants
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.delete
import io.ktor.client.request.get
import io.ktor.client.request.parameter
import io.ktor.client.request.post
import io.ktor.client.request.setBody
import io.ktor.http.ContentType
import io.ktor.http.contentType
import io.ktor.http.parameters

class AddressApiService(
    private val httpClient: HttpClient
) {
    suspend fun getAddresses(): AddressResponse {
        return httpClient.get("${Constants.SHOPPIEE_URL}/get-addresses") {
            contentType(ContentType.Application.Json)
        }.body()
    }

    suspend fun deleteAddress(id: String): AddressResponse {
        return httpClient.delete("${Constants.SHOPPIEE_URL}/address") {
            contentType(ContentType.Application.Json)
            url {
                parameters.append("id", id)
            }
        }.body()
    }

    suspend fun addAddress(addressRequest: AddressRequest): AddressResponse {
        return httpClient.post("${Constants.SHOPPIEE_URL}/add-address") {
            contentType(ContentType.Application.Json)
            setBody(addressRequest)
        }.body()
    }
}