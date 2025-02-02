package com.example.shoppieeclient.presentation.home.address

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue

class AddressViewModel: ViewModel() {

    var addressState by mutableStateOf(AddressStates())
        private set

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
        }
    }

}