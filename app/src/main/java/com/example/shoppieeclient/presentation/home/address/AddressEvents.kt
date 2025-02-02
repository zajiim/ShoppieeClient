package com.example.shoppieeclient.presentation.home.address

import com.example.shoppieeclient.domain.address.models.AddressModel

sealed class AddressEvents {
    data object AddAddressClicked: AddressEvents()
    data object DismissBottomSheet: AddressEvents()
    data class AddAddressSubmit(val address: AddressModel): AddressEvents()
    data class EditButtonClicked(val address: AddressModel): AddressEvents()
}
