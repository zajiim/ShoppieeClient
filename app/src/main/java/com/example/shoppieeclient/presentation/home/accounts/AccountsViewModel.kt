package com.example.shoppieeclient.presentation.home.accounts

import android.content.Context
import android.content.pm.PackageManager
import android.os.Build
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.core.content.ContextCompat
import android.Manifest
import android.app.Activity
import androidx.core.app.ActivityCompat

class AccountsViewModel: ViewModel() {
    var uiState by mutableStateOf(AccountsStates())
        private set

    fun onEvent(event: AccountsEvent) {
        when(event) {
            AccountsEvent.DismissDialog -> {
                uiState = uiState.copy(
                    isAlertBoxOpen = false
                )
            }
            AccountsEvent.OpenCamera -> {
                uiState = uiState.copy(
                    isAlertBoxOpen = false
                )
            }
            AccountsEvent.OpenGallery -> {
                uiState = uiState.copy(
                    isAlertBoxOpen = false
                )

            }
        }
    }

    fun showAlertBox() {
        uiState = uiState.copy(
            isAlertBoxOpen = true
        )
    }

    fun dismissAlertBox() {
        uiState = uiState.copy(
            isAlertBoxOpen = false
        )
    }

    fun grantedCameraPermission() {
        uiState = uiState.copy(
            cameraPermissionGranted = true,
            isAlertBoxOpen = false
        )
    }

    fun grantedGalleryPermission() {
        uiState = uiState.copy(
            galleryPermissionGranted = true,
            isAlertBoxOpen = false
        )
    }

    fun checkCameraPermission(context: Context): Boolean {
        return ContextCompat.checkSelfPermission(context, Manifest.permission.CAMERA) == PackageManager.PERMISSION_GRANTED
    }

    fun checkGalleryPermission(context: Context): Boolean {
        val galleryPermission = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            Manifest.permission.READ_MEDIA_IMAGES
        } else {
            Manifest.permission.READ_EXTERNAL_STORAGE
        }
        return ContextCompat.checkSelfPermission(context, galleryPermission) == PackageManager.PERMISSION_GRANTED
    }


    fun shouldShowCameraPermissionRationale(activity: Activity): Boolean {
        return ActivityCompat.shouldShowRequestPermissionRationale(activity, Manifest.permission.CAMERA)
    }

    fun shouldShowGalleryPermissionRationale(activity: Activity): Boolean {
        val galleryPermission = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            Manifest.permission.READ_MEDIA_IMAGES
        } else {
            Manifest.permission.READ_EXTERNAL_STORAGE

        }
        return ActivityCompat.shouldShowRequestPermissionRationale(activity, galleryPermission)
    }

    fun updateProfileImage(imageUrl: String) {
        uiState = uiState.copy(
            profileImageUrl = imageUrl
        )
    }

    fun setGoToCameraSettings(value: Boolean) {
        uiState = uiState.copy(goToCameraSettings = value)
    }

    fun setGoToGallerySettings(value: Boolean) {
        uiState = uiState.copy(goToGallerySettings = value)
    }

}