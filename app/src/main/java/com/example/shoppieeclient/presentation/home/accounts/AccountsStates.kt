package com.example.shoppieeclient.presentation.home.accounts

data class AccountsStates(
    val isAlertBoxOpen: Boolean = false,
    val profileName: String = "",
    val email: String = "",
    val profileImageUrl: String = "",
    val isUploadingProfilePic: Boolean = false,
    val isLoading: Boolean = false,
    val error: String? = null,
    val uploadError: String? = null,
    val cameraPermissionGranted: Boolean = false,
    val galleryPermissionGranted: Boolean = false,
    val showCameraDialog: Boolean = false,
    val showGalleryDialog: Boolean = false,
    val goToCameraSettings: Boolean = false,
    val goToGallerySettings: Boolean = false,
    val updatingProfile: Boolean = false,
    val updateProfileError: String? = null,
    val updateProfileSuccess: Boolean = false,
    val updateProfileSuccessAlertBox: Boolean = false
)