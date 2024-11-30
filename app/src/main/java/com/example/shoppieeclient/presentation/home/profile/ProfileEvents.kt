package com.example.shoppieeclient.presentation.home.profile

sealed class ProfileEvents {
    data class ToggleBiometric(val isEnabled: Boolean) : ProfileEvents()
    data class TogglePushNotification(val isEnabled: Boolean) : ProfileEvents()
    data class ToggleLocationService(val isEnabled: Boolean) : ProfileEvents()
    data class ToggleDarkMode(val isEnabled: Boolean) : ProfileEvents()
}