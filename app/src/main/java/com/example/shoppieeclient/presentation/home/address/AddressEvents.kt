package com.example.shoppieeclient.presentation.home.address

sealed class AddressEvents {
    data object AddAddressClicked: AddressEvents()
    data object DismissBottomSheet: AddressEvents()
}
