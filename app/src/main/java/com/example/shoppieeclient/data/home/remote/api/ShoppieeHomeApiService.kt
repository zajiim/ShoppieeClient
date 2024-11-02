package com.example.shoppieeclient.data.home.remote.api

import com.example.shoppieeclient.data.home.remote.dto.home.HomeResponseDto
import com.example.shoppieeclient.data.home.remote.dto.home.ProductDetailsResponseDto
import com.example.shoppieeclient.utils.Constants
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.http.ContentType
import io.ktor.http.contentType

class ShoppieeHomeApiService(
    private val client: HttpClient
) {
    suspend fun getBrandProductsData(brand: String): HomeResponseDto {
        return client.get("${Constants.SHOPPIEE_URL}/popularBrand") {
            url {
                parameters.append("brand", brand)
            }
            contentType(ContentType.Application.Json)
        }.body()
    }

    suspend fun fetchProductDetails(productId: String): ProductDetailsResponseDto {
        return client.get("${Constants.SHOPPIEE_URL}/details") {
            url {
                parameters.append("productId", productId)
            }
        }.body()
    }
}