package com.example.shoppieeclient.presentation.home.accounts

data class AccountsStates(
    val isAlertBoxOpen: Boolean = false,
    val profileName: String = "",
    val email: String = "",
    val profileImageUrl: String = ""
)