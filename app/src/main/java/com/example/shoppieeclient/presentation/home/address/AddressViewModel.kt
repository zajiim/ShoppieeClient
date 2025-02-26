package com.example.shoppieeclient.presentation.home.address

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.shoppieeclient.data.address.local.dao.SelectedAddressDao
import com.example.shoppieeclient.data.address.local.entity.SelectedAddressEntity
import com.example.shoppieeclient.domain.address.models.AddressModel
import com.example.shoppieeclient.domain.address.use_cases.AddAddressUseCase
import com.example.shoppieeclient.domain.address.use_cases.DeleteAddressUseCase
import com.example.shoppieeclient.domain.address.use_cases.EditAddressUseCase
import com.example.shoppieeclient.domain.address.use_cases.GetAddressListUseCase
import com.example.shoppieeclient.domain.address.use_cases.SelectAddressUseCase
import com.example.shoppieeclient.utils.Resource
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

private const val TAG = "AddressViewModel"

class AddressViewModel(
    private val getAddressListUseCase: GetAddressListUseCase,
    private val deleteAddressUseCase: DeleteAddressUseCase,
    private val addAddressUseCase: AddAddressUseCase,
    private val editAddressUseCase: EditAddressUseCase,
    private val selectAddressUseCase: SelectAddressUseCase
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
                    addressState = addressState.copy(
                        isLoading = false,
                        addresses = result.data
                    )
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
    }

    private fun selectAddress(id: String) = viewModelScope.launch{
        selectAddressUseCase(id = id).collect { result ->
            when (result) {
                is Resource.Error -> {
                    addressState = addressState.copy(
                        isLoading = false,
                        error = result.message
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
                            error = result.message
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
                            error = result.message
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