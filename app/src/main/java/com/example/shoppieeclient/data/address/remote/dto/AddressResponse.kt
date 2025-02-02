package com.example.shoppieeclient.data.address.remote.dto

import kotlinx.serialization.Serializable

@Serializable
data class AddressResponse(
    val status: Int,
    val message: String,
    val result: AddressResult
)

@Serializable
data class AddressResult(
    val addresses: List<AddressDto>
)


@Serializable
data class AddressDto(
    val id: String,
    val userId: String,
    val streetAddress: String,
    val city: String,
    val state: String,
    val zipCode: String
)