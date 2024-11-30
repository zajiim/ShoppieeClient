package com.example.shoppieeclient.presentation.home.profile

data class ProfileStates(
    val isBiometricEnabled: Boolean = false,
    val isPushNotificationEnabled: Boolean = false,
    val isLocationServiceEnabled: Boolean = false,
    val isDarkModeEnabled: Boolean = false
)