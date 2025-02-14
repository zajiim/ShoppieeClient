package com.example.shoppieeclient.presentation.home.address

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.shoppieeclient.domain.address.models.AddressModel
import com.example.shoppieeclient.domain.address.use_cases.DeleteAddressUseCase
import com.example.shoppieeclient.domain.address.use_cases.GetAddressListUseCase
import com.example.shoppieeclient.utils.Resource
import kotlinx.coroutines.launch

private const val TAG = "AddressViewModel"

class AddressViewModel(
    private val getAddressListUseCase: GetAddressListUseCase,
    private val deleteAddressUseCase: DeleteAddressUseCase
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
                    addressState = addressState.copy(isLoading = false, error = result.message)
                }

                is Resource.Loading -> {
                    addressState = addressState.copy(isLoading = true)
                }

                is Resource.Success -> {
                    addressState = addressState.copy(isLoading = false, addresses = result.data)
                }
            }
        }


    }


    fun onEvent(events: AddressEvents) {
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
                addressState = addressState.copy(

                )
            }

            is AddressEvents.EditButtonClicked -> {
                addressState = addressState.copy(
                    isAddAddressClicked = true, selectedAddress = events.address
                )
            }

            is AddressEvents.UpdateCity -> {
                /*addressState.selectedAddress?.let { currentVal ->
                    addressState = addressState.copy(
                        selectedAddress = currentVal.copy(city = events.city)
                    )
                }*/
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
                /*addressState.selectedAddress?.let { currentVal ->
                    addressState = addressState.copy(
                        selectedAddress = currentVal.copy(state = events.state)
                    )
                }*/
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
                /*addressState.selectedAddress?.let { currentVal ->
                    addressState = addressState.copy(
                        selectedAddress = currentVal.copy(streetAddress = events.streetAddress)
                    )
                }*/
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
               /* addressState.selectedAddress?.let { currentVal ->
                    addressState = addressState.copy(
                        selectedAddress = currentVal.copy(zipCode = events.zipCode)
                    )
                }*/
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
        }
    }

    private fun deleteAddressApi(id: String) = viewModelScope.launch {
        deleteAddressUseCase(id).collect { result ->
            when (result) {
                is Resource.Error -> {
                    addressState = addressState.copy(
                        isLoading = false,
                        error = result.message,
                        showDeleteConfirmation = false,
                        selectedForDeletion = null
                    )
                }
                is Resource.Loading -> {
                    addressState = addressState.copy(isLoading = true)
                }

                is Resource.Success -> {
                    addressState = addressState.copy(
                        isLoading = false,
                        addresses = result.data ?: emptyList(),
                        showDeleteConfirmation = false,
                        selectedForDeletion = null
                    )
                }
            }

        }
    }


}