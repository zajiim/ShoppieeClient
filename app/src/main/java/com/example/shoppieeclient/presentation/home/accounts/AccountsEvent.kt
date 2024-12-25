package com.example.shoppieeclient.presentation.home.accounts

sealed class AccountsEvent {
    data object OpenCamera: AccountsEvent()
    data object OpenGallery: AccountsEvent()
    data object DismissDialog: AccountsEvent()
    data object DismissAlertBox: AccountsEvent()
    data object ShowAlertBox: AccountsEvent()
    data class UpdateProfileImage(val imageUrl: String): AccountsEvent()
    data object GrantedCameraPermission: AccountsEvent()
    data object GrantedGalleryPermission: AccountsEvent()
    data class GoToCameraSettings(val value: Boolean): AccountsEvent()
    data class GoToGallerySettings(val value: Boolean): AccountsEvent()

}