package com.example.shoppieeclient.presentation.home.profile

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue

class ProfileViewModel: ViewModel() {

    var profileStates by mutableStateOf(ProfileStates())
        private set

    fun onEvent(event: ProfileEvents) {
        when(event) {
            is ProfileEvents.ToggleBiometric -> {
                profileStates = profileStates.copy(
                    isBiometricEnabled = event.isEnabled
                )
            }
            is ProfileEvents.ToggleDarkMode -> {
                profileStates = profileStates.copy(
                    isDarkModeEnabled = event.isEnabled
                )
            }
            is ProfileEvents.ToggleLocationService -> {
                profileStates = profileStates.copy(
                    isLocationServiceEnabled = event.isEnabled
                )
            }
            is ProfileEvents.TogglePushNotification -> {
                profileStates = profileStates.copy(
                    isPushNotificationEnabled = event.isEnabled
                )

            }
        }
    }


}