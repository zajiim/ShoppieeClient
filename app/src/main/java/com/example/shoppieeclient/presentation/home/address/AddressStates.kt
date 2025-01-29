package com.example.shoppieeclient.presentation.home.address

data class AddressStates(
    val isAddAddressClicked: Boolean = false,
    val addresses: List<Address>?=emptyList<Address>(),
)


data class Address(
    val streetAddress: String?="null",
    val city: String?="",
    val state: String?="",
    val zipCode: String?="",
)