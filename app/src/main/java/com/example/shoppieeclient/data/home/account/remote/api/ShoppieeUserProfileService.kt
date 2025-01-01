package com.example.shoppieeclient.data.home.account.remote.api

import com.example.shoppieeclient.data.home.account.remote.dto.GetProfileResponseDto
import com.example.shoppieeclient.data.home.account.remote.dto.UpdateProfileBody
import com.example.shoppieeclient.data.home.account.remote.dto.UpdateProfileResponseDto
import com.example.shoppieeclient.utils.Constants
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.client.request.post
import io.ktor.client.request.setBody
import io.ktor.http.ContentType
import io.ktor.http.contentType

class ShoppieeUserProfileService(
    private val authorizedHttpClient: HttpClient
) {
    suspend fun updateProfile(updateProfileBody: UpdateProfileBody): UpdateProfileResponseDto {
        return authorizedHttpClient.post("${Constants.SHOPPIEE_URL}/update-profile") {
            contentType(ContentType.Application.Json)
            setBody(updateProfileBody)
        }.body()
    }

    suspend fun getProfileData(): GetProfileResponseDto {
        return authorizedHttpClient.get("${Constants.SHOPPIEE_URL}/get-user-profile") {
            contentType(ContentType.Application.Json)
        }.body()
    }
}