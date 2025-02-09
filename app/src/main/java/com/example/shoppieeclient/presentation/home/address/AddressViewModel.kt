package com.example.shoppieeclient.presentation.home.address

import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.lifecycle.viewModelScope
import com.example.shoppieeclient.domain.address.use_cases.GetAddressListUseCase
import com.example.shoppieeclient.utils.Resource
import kotlinx.coroutines.launch

private const val TAG = "AddressViewModel"
class AddressViewModel(
    private val getAddressListUseCase: GetAddressListUseCase
): ViewModel() {

    var addressState by mutableStateOf(AddressStates())
        private set

    init {
        fetchAddresses()
    }

    private fun fetchAddresses() = viewModelScope.launch {
        addressState = addressState.copy(isLoading = true)
        getAddressListUseCase().collect { result ->
            when(result) {
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
        when(events) {
            AddressEvents.AddAddressClicked -> {
                addressState = addressState.copy(
                    isAddAddressClicked = true
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
                    isAddAddressClicked = true,
                    selectedAddress = events.address
                )
            }

            is AddressEvents.UpdateCity -> {
                addressState.selectedAddress?.let { currentVal ->
                    addressState = addressState.copy(
                        selectedAddress = currentVal.copy(city = events.city)
                    )
                }
            }
            is AddressEvents.UpdateState -> {
                addressState.selectedAddress?.let { currentVal ->
                    addressState = addressState.copy(
                        selectedAddress = currentVal.copy(state = events.state)
                    )
                }
            }
            is AddressEvents.UpdateStreetAddress -> {
                addressState.selectedAddress?.let { currentVal ->
                    addressState = addressState.copy(
                        selectedAddress = currentVal.copy(streetAddress = events.streetAddress)
                    )
                }
            }
            is AddressEvents.UpdateZipCode -> {
                addressState.selectedAddress?.let { currentVal ->
                    addressState = addressState.copy(
                        selectedAddress = currentVal.copy(zipCode = events.zipCode)
                    )
                }
            }

            AddressEvents.CancelDelete -> {
                addressState = addressState.copy(
                    showDeleteConfirmation = false,
                    selectedForDeletion = null
                )
            }
            AddressEvents.ConfirmDelete -> {
                // TODO: Delete api 
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

}