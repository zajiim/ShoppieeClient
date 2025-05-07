package com.example.shoppieeclient.presentation.home.address

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.shoppieeclient.domain.address.use_cases.AddAddressUseCase
import com.example.shoppieeclient.domain.address.use_cases.DeleteAddressUseCase
import com.example.shoppieeclient.domain.address.use_cases.EditAddressUseCase
import com.example.shoppieeclient.domain.address.use_cases.GetAddressListUseCase
import com.example.shoppieeclient.domain.address.use_cases.SelectAddressUseCase
import com.example.shoppieeclient.utils.Resource
import kotlinx.coroutines.launch


class AddressViewModel(
    private val getAddressListUseCase: GetAddressListUseCase,
    private val deleteAddressUseCase: DeleteAddressUseCase,
    private val addAddressUseCase: AddAddressUseCase,
    private val editAddressUseCase: EditAddressUseCase,
    private val selectAddressUseCase: SelectAddressUseCase,
) : ViewModel() {

    var addressState by mutableStateOf(AddressStates())
        private set

    init {
        fetchAddresses()
    }

    private fun fetchAddresses() = viewModelScope.launch {
        addressState = addressState.copy(isLoading = true)
        getAddressListUseCase().collect { result ->
            when (result) {
                is Resource.Error -> {
                    addressState = addressState.copy(isLoading = false, isError = true)
                }

                is Resource.Loading -> {
                    addressState = addressState.copy(isLoading = true)
                }

                is Resource.Success -> {
                    addressState = addressState.copy(
                        isLoading = false,
                        addresses = result.data ?: emptyList(),
                    )
                }
            }
        }
    }

    fun onEvent(events: AddressEvents) {
        when(events) {
            is AddressEvents.StreetAddressChanged ->  {
                addressState = addressState.copy(
                    streetAddressText = events.streetAddress
                )
            }
            is AddressEvents.CityChanged -> {
                addressState = addressState.copy(
                    cityText = events.city
                )
            }
            is AddressEvents.StateChanged -> {
                addressState = addressState.copy(
                    stateText = events.state
                )
            }
            is AddressEvents.ZipCodeChanged -> {
                addressState = addressState.copy(
                    zipCodeText = events.zipCode
                )
            }

            is AddressEvents.LoadAddressDetails -> {
                val address = events.selectedAddress
                addressState = addressState.copy(
                    selectedAddress = address,
                    streetAddressText = address.streetAddress,
                    cityText = address.city,
                    stateText = address.state,
                    zipCodeText = address.zipCode,
                    showBottomSheet = true,
                    isEditing = true,
                    currentAddressId = address.id
                )
            }

            AddressEvents.ShowAddAddressSheet -> {
                addressState = addressState.copy(
                    showBottomSheet = true,
                    streetAddressText = "",
                    cityText = "",
                    stateText = "",
                    zipCodeText = "",
                    isEditing = false,
                    currentAddressId = ""
                )
            }

            AddressEvents.DismissAddAddressSheet -> {
                addressState = addressState.copy(
                    showBottomSheet = false
                )
            }


            AddressEvents.SaveAddress -> {
                if (addressState.isEditing) {
                    editAddressApi(
                        id = addressState.currentAddressId.toString(),
                        streetAddress = addressState.streetAddressText,
                        city = addressState.cityText,
                        state = addressState.stateText,
                        zipCode = addressState.zipCodeText
                    )
                } else {
                    addAddressApi(
                        streetAddress = addressState.streetAddressText,
                        city = addressState.cityText,
                        state = addressState.stateText,
                        zipCode = addressState.zipCodeText
                    )
                }
                addressState = addressState.copy(
                    showBottomSheet = false
                )
            }

            AddressEvents.ConfirmAddressSelection -> {
                selectAddress(id = addressState.currentAddressId)
                addressState = addressState.copy(
                    showSelectionDialog = false
                )
            }
            is AddressEvents.DeleteAddress -> {
                deleteAddressApi(events.addressId)
            }
            AddressEvents.DismissDeleteDialog -> {
                addressState = addressState.copy(
                    showDeleteDialog = false,
                    addressToDeleteId = null
                )
            }
            AddressEvents.DismissSelectionDialog -> {
                addressState = addressState.copy(
                    showSelectionDialog = false,
                    addressToSelect = null
                )
            }
            is AddressEvents.ShowDeleteDialog -> {
                addressState = addressState.copy(
                    showDeleteDialog = true,
                    addressToDeleteId = events.addressModel.id
                )
            }
            is AddressEvents.ShowSelectionDialog -> {
                addressState = addressState.copy(
                    showSelectionDialog = true,
                    addressToSelect = events.addressModel,
                    currentAddressId = events.addressModel.id
                )
            }
        }
    }


    /*fun onEvent(events: AddressEvents) {
        when (events) {
            AddressEvents.AddAddressClicked -> {
                addressState = addressState.copy(
                    isAddAddressClicked = true,
                    // Reset the form with empty values
                    selectedAddress = AddressModel(
                        id = "",
                        userId = "",
                        streetAddress = "",
                        city = "",
                        state = "",
                        zipCode = ""
                    )
                )
            }

            AddressEvents.DismissBottomSheet -> {
                addressState = addressState.copy(
                    isAddAddressClicked = false
                )

            }

            is AddressEvents.AddAddressSubmit -> {
                val selectedAddress = addressState.selectedAddress
                if (selectedAddress != null) {
                    addAddressApi(
                        streetAddress = selectedAddress.streetAddress,
                        city = selectedAddress.city,
                        state = selectedAddress.state,
                        zipCode = selectedAddress.zipCode
                    )
                }
                addressState = addressState.copy(
                    isAddAddressClicked = false
                )
            }

            is AddressEvents.EditButtonClicked -> {
                addressState = addressState.copy(
                    isAddAddressClicked = true, selectedAddress = events.address
                )
            }

            is AddressEvents.UpdateCity -> {
                *//*addressState.selectedAddress?.let { currentVal ->
                    addressState = addressState.copy(
                        selectedAddress = currentVal.copy(city = events.city)
                    )
                }*//*
                addressState = addressState.copy(
                    selectedAddress = (addressState.selectedAddress ?: AddressModel(
                        id = "",
                        userId = "",
                        streetAddress = "",
                        city = "",
                        state = "",
                        zipCode = ""
                    )).copy(city = events.city)
                )
            }

            is AddressEvents.UpdateState -> {
                *//*addressState.selectedAddress?.let { currentVal ->
                    addressState = addressState.copy(
                        selectedAddress = currentVal.copy(state = events.state)
                    )
                }*//*
                addressState = addressState.copy(
                    selectedAddress = (addressState.selectedAddress ?: AddressModel(
                        id = "",
                        userId = "",
                        streetAddress = "",
                        city = "",
                        state = "",
                        zipCode = ""
                    )).copy(state = events.state)
                )
            }

            is AddressEvents.UpdateStreetAddress -> {
                *//*addressState.selectedAddress?.let { currentVal ->
                    addressState = addressState.copy(
                        selectedAddress = currentVal.copy(streetAddress = events.streetAddress)
                    )
                }*//*
                addressState = addressState.copy(
                    selectedAddress = (addressState.selectedAddress ?: AddressModel(
                        id = "",
                        userId = "",
                        streetAddress = "",
                        city = "",
                        state = "",
                        zipCode = ""
                    )).copy(streetAddress = events.streetAddress)
                )
            }

            is AddressEvents.UpdateZipCode -> {
                *//* addressState.selectedAddress?.let { currentVal ->
                     addressState = addressState.copy(
                         selectedAddress = currentVal.copy(zipCode = events.zipCode)
                     )
                 }*//*
                addressState = addressState.copy(
                    selectedAddress = (addressState.selectedAddress ?: AddressModel(
                        id = "",
                        userId = "",
                        streetAddress = "",
                        city = "",
                        state = "",
                        zipCode = ""
                    )).copy(zipCode = events.zipCode)
                )
            }

            AddressEvents.CancelDelete -> {
                addressState = addressState.copy(
                    showDeleteConfirmation = false, selectedForDeletion = null
                )
            }

            is AddressEvents.ConfirmDelete -> {
                deleteAddressApi(events.address.id)
            }

            is AddressEvents.LongPressAddress -> {
                addressState = addressState.copy(
                    selectedForDeletion = events.address
                )
            }

            AddressEvents.UnSelectAddress -> {
                addressState = addressState.copy(
                    selectedForDeletion = null
                )
            }

            AddressEvents.DeleteClicked -> {
                addressState = addressState.copy(
                    showDeleteConfirmation = true
                )
            }

            is AddressEvents.EditAddressSubmit -> {
                val selectedAddress = addressState.selectedAddress
                if (selectedAddress != null) {
                    editAddressApi(
                        id = events.addressId,
                        streetAddress = selectedAddress.streetAddress,
                        city = selectedAddress.city,
                        state = selectedAddress.state,
                        zipCode = selectedAddress.zipCode
                    )
                }
                addressState = addressState.copy(
                    isAddAddressClicked = false
                )
            }

            is AddressEvents.ConfirmAddressSelection -> {
                Log.e(TAG, "started >>>>> func", )
                selectAddress(events.addressId)
                addressState = addressState.copy(confirmSelectedAddress = true)
            }
            is AddressEvents.SelectAddress -> {
                val updatedAddress = addressState.addresses?.map {address ->
                    address.copy(isSelected = address.id == events.address.id)
                }
                addressState = addressState.copy(addresses = updatedAddress, selectedAddress = events.address)
            }
        }
    }*/

    private fun selectAddress(id: String) = viewModelScope.launch{
        selectAddressUseCase(id = id).collect { result ->
            when (result) {
                is Resource.Error -> {
                    addressState = addressState.copy(
                        isLoading = false,
                        isError = true
                    )
                }

                is Resource.Loading -> {
                    addressState = addressState.copy(isLoading = true)
                }
                is Resource.Success -> {
                    addressState = addressState.copy(
                        isLoading = false,
                        addresses = result.data ?: emptyList()
                    )
                }
            }
        }
    }

    private fun editAddressApi(
        id: String,
        streetAddress: String,
        city: String,
        state: String?,
        zipCode: String?
    ) {
        viewModelScope.launch {
            editAddressUseCase(
                id = id,
                streetAddress = streetAddress,
                city = city,
                state = state,
                zipCode = zipCode
            ).collect { result ->
                when (result) {
                    is Resource.Error -> {
                        addressState = addressState.copy(
                            isLoading = false,
                        )
                    }

                    is Resource.Loading -> {
                        addressState = addressState.copy(isLoading = true)
                    }
                    is Resource.Success -> {
                        addressState = addressState.copy(
                            isLoading = false,
                            addresses = result.data ?: emptyList()
                        )
                    }
                }
            }
        }
    }

    private fun addAddressApi(
        streetAddress: String,
        city: String,
        state: String?,
        zipCode: String?
    ) {
        viewModelScope.launch {
            addAddressUseCase(
                streetAddress = streetAddress,
                city = city,
                state = state,
                zipCode = zipCode
            ).collect { result ->
                when (result) {
                    is Resource.Error -> {
                        addressState = addressState.copy(
                            isLoading = false,
                            isError = true
                        )
                    }

                    is Resource.Loading -> {
                        addressState = addressState.copy(isLoading = true)
                    }

                    is Resource.Success -> {
                        addressState = addressState.copy(
                            isLoading = false,
                            addresses = result.data ?: emptyList()
                        )
                    }

                }

            }
        }
    }


    private fun deleteAddressApi(id: String) = viewModelScope.launch {
        deleteAddressUseCase(id).collect { result ->
            when (result) {
                is Resource.Error -> {
                    addressState = addressState.copy(
                        isLoading = false,
                        showDeleteDialog = false
                    )
                }

                is Resource.Loading -> {
                    addressState = addressState.copy(isLoading = true)
                }

                is Resource.Success -> {
                    addressState = addressState.copy(
                        isLoading = false,
                        addresses = result.data ?: emptyList(),
                        showDeleteDialog = false,
                        addressToDeleteId = null
                    )
                }
            }

        }
    }


}