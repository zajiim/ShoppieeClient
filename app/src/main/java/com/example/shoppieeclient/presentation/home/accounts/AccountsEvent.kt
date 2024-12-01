package com.example.shoppieeclient.presentation.home.accounts

sealed class AccountsEvent {
    data object OpenCamera: AccountsEvent()
    data object OpenGallery: AccountsEvent()
    data object DismissDialog: AccountsEvent()
}