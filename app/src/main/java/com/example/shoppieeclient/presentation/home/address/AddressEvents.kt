package com.example.shoppieeclient.presentation.home.address

import com.example.shoppieeclient.domain.address.models.AddressModel

sealed class AddressEvents {
    //Home Events
    data class ShowDeleteDialog(val addressModel: AddressModel): AddressEvents()
    data object DismissDeleteDialog: AddressEvents()
    data class DeleteAddress(val addressId: String): AddressEvents()

    //Details Event
    data class StreetAddressChanged(val streetAddress: String): AddressEvents()
    data class CityChanged(val city: String): AddressEvents()
    data class StateChanged(val state: String): AddressEvents()
    data class ZipCodeChanged(val zipCode: String): AddressEvents()
    data class LoadAddressDetails(val selectedAddress: AddressModel): AddressEvents()

    data object ShowAddAddressSheet: AddressEvents()
    data object DismissAddAddressSheet: AddressEvents()
    data object SaveAddress: AddressEvents()

    data class ShowSelectionDialog(val addressModel: AddressModel): AddressEvents()
    data object DismissSelectionDialog: AddressEvents()
    data object ConfirmAddressSelection: AddressEvents()
}
