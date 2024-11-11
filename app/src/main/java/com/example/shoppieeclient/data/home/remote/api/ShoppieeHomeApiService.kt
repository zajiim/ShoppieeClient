package com.example.shoppieeclient.data.home.remote.api

import com.example.shoppieeclient.data.home.remote.dto.details.AddToCartRequestDto
import com.example.shoppieeclient.data.home.remote.dto.details.AddToCartResponseDto
import com.example.shoppieeclient.data.home.remote.dto.details.AddToCartResultDto
import com.example.shoppieeclient.data.home.remote.dto.details.DetailsResponseDto
import com.example.shoppieeclient.data.home.remote.dto.home.HomeResponseDto
import com.example.shoppieeclient.utils.Constants
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.client.request.post
import io.ktor.client.request.setBody
import io.ktor.http.ContentType
import io.ktor.http.contentType

class ShoppieeHomeApiService(
    private val unauthorizedHttpClient: HttpClient,
    private val authorizedHttpClient: HttpClient
) {
    suspend fun getBrandProductsData(brand: String): HomeResponseDto {
        return unauthorizedHttpClient.get("${Constants.SHOPPIEE_URL}/popularBrand") {
            url {
                parameters.append("brand", brand)
            }
            contentType(ContentType.Application.Json)
        }.body()
    }

    suspend fun fetchProductDetails(productId: String): DetailsResponseDto {
        return unauthorizedHttpClient.get("${Constants.SHOPPIEE_URL}/details") {
            url {
                parameters.append("productId", productId)
            }
        }.body()
    }

    suspend fun addToCart(addToCartRequestDto: AddToCartRequestDto): AddToCartResponseDto {
        return authorizedHttpClient.post("${Constants.SHOPPIEE_URL}/add-to-cart") {
            contentType(ContentType.Application.Json)
            setBody(addToCartRequestDto)
        }.body()
    }
}