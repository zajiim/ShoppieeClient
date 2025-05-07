package com.example.shoppieeclient.presentation.home.address

import com.example.shoppieeclient.domain.address.models.AddressModel

data class AddressStates(
    //Home related states
    val isError: Boolean = false,
    val addresses: List<AddressModel> = emptyList(),
    val isLoading: Boolean = false,
    val showDeleteDialog: Boolean = false,
    val addressToDeleteId: String? = null,

    //Detail related states
    val streetAddressText: String = "",
    val cityText: String = "",
    val stateText: String = "",
    val zipCodeText: String = "",
    val isEditing: Boolean = false,
    val currentAddressId: String = "",

    //BottomSheet State
    val showBottomSheet: Boolean = false,

    //Address Selection State
    val showSelectionDialog: Boolean = false,
    val addressToSelect: AddressModel? = null,
    val selectedAddress: AddressModel? = null,
)


