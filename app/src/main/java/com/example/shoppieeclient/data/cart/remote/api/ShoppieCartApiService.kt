package com.example.shoppieeclient.data.cart.remote.api

import com.example.shoppieeclient.data.cart.remote.dto.CartResponseDto
import com.example.shoppieeclient.data.cart.remote.dto.CartResultDto
import com.example.shoppieeclient.data.cart.remote.dto.DecrementDeleteCartItemRequestDto
import com.example.shoppieeclient.data.cart.remote.dto.IncrementCartItemRequestDto
import com.example.shoppieeclient.data.cart.remote.dto.IncrementDecrementDeleteCartResponseDto
import com.example.shoppieeclient.data.cart.remote.dto.IncrementDecrementDeleteResultDto
import com.example.shoppieeclient.utils.Constants
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.client.request.post
import io.ktor.client.request.setBody
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

    suspend fun incrementCart(incrementCartItemRequestDto: IncrementCartItemRequestDto): IncrementDecrementDeleteCartResponseDto {
        return authorizedHttpClient.post("${Constants.SHOPPIEE_URL}/add-to-cart-from-cart") {
            contentType(ContentType.Application.Json)
            setBody(incrementCartItemRequestDto)
        }.body()
    }


    suspend fun decrementCart(decrementDeleteCartResponseDto: DecrementDeleteCartItemRequestDto): IncrementDecrementDeleteCartResponseDto {
        return authorizedHttpClient.post("${Constants.SHOPPIEE_URL}/decrement-from-cart") {
            contentType(ContentType.Application.Json)
            setBody(decrementDeleteCartResponseDto)
        }.body()
    }

    suspend fun removeFromCart(decrementDeleteCartResponseDto: DecrementDeleteCartItemRequestDto): IncrementDecrementDeleteCartResponseDto {
        return authorizedHttpClient.post("${Constants.SHOPPIEE_URL}/remove-from-cart") {
            contentType(ContentType.Application.Json)
            setBody(decrementDeleteCartResponseDto)
        }.body()
    }





}