package com.example.shoppieeclient.data.address.remote.dto

import kotlinx.serialization.Serializable

@Serializable
data class AddressRequest(
    val streetAddress: String,
    val city: String,
    val state: String?,
    val zipCode: String?
)
