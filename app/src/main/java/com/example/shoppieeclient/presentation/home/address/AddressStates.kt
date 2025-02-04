package com.example.shoppieeclient.presentation.home.address

import com.example.shoppieeclient.domain.address.models.AddressModel

data class AddressStates(
    val isAddAddressClicked: Boolean = false,
    val addresses: List<AddressModel>?=emptyList<AddressModel>(),
    val isLoading: Boolean = false,
    val error: String? = null,
    val selectedAddress: AddressModel? = null
)


