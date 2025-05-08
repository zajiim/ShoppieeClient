package com.example.shoppieeclient.domain.address.models

data class AddressModel(
    val id: String,
    val userId: String,
    val streetAddress: String,
    val city: String,
    val state: String,
    val zipCode: String,
    val isSelected: Boolean = false
)