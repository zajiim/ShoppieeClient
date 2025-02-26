package com.example.shoppieeclient.presentation.home.address

import com.example.shoppieeclient.domain.address.models.AddressModel

sealed class AddressEvents {
    data object AddAddressClicked: AddressEvents()
    data object DismissBottomSheet: AddressEvents()
    data object AddAddressSubmit: AddressEvents()
    data class EditAddressSubmit(val addressId: String): AddressEvents()
    data class EditButtonClicked(val address: AddressModel): AddressEvents()
    data class UpdateStreetAddress(val streetAddress: String): AddressEvents()
    data class UpdateCity(val city: String): AddressEvents()
    data class UpdateState(val state: String): AddressEvents()
    data class UpdateZipCode(val zipCode: String): AddressEvents()
    data class LongPressAddress(val address: AddressModel): AddressEvents()
    data object CancelDelete: AddressEvents()
    data class ConfirmDelete(val address: AddressModel): AddressEvents()
    data object UnSelectAddress: AddressEvents()
    data object DeleteClicked: AddressEvents()
    data class SelectAddress(val address: AddressModel): AddressEvents()
    data class ConfirmAddressSelection(val addressId: String): AddressEvents()
}
