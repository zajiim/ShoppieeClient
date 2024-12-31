package com.example.shoppieeclient.presentation.home.accounts

import android.Manifest
import android.app.Activity
import android.content.Context
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Build
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.shoppieeclient.domain.home.account.use_cases.UploadImageUseCase
import kotlinx.coroutines.launch

class AccountsViewModel(
    private val uploadImageUseCase: UploadImageUseCase
) : ViewModel() {
    var uiState by mutableStateOf(AccountsStates())
        private set

    fun onEvent(event: AccountsEvent) {
        when (event) {
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

            is AccountsEvent.UpdateProfileImage -> {

                //add image api call

                addImage(event.imageUrl, event.userName)
//                uiState = uiState.copy(
//                    profileImageUrl = event.imageUrl
//                )
            }

            AccountsEvent.GrantedCameraPermission -> {
                uiState = uiState.copy(
                    cameraPermissionGranted = true,
                    isAlertBoxOpen = false
                )
            }

            AccountsEvent.GrantedGalleryPermission -> {
                uiState = uiState.copy(
                    galleryPermissionGranted = true,
                    isAlertBoxOpen = false
                )
            }

            is AccountsEvent.GoToCameraSettings -> {
                uiState = uiState.copy(goToCameraSettings = event.value)
            }

            is AccountsEvent.GoToGallerySettings -> {
                uiState = uiState.copy(goToGallerySettings = event.value)
            }

            AccountsEvent.DismissAlertBox -> {
                uiState = uiState.copy(
                    isAlertBoxOpen = false
                )
            }

            AccountsEvent.ShowAlertBox -> {
                uiState = uiState.copy(
                    isAlertBoxOpen = true
                )
            }
        }
    }

    private fun addImage(imageUrl: String, userName: String) {
        viewModelScope.launch {
            uiState = uiState.copy(isUploadingProfilePic = true, uploadError = null)
            try {
                val result = uploadImageUseCase(imageUri = Uri.parse(imageUrl), userName = userName)
                uiState = uiState.copy(
                    profileImageUrl = result,
                    isUploadingProfilePic = false
                )
            } catch (e: Exception) {
                uiState = uiState.copy(
                    isUploadingProfilePic = false,
                    uploadError = e.message ?: "Upload failed"
                )
            }
        }
    }

    fun checkCameraPermission(context: Context): Boolean {
        return ContextCompat.checkSelfPermission(
            context,
            Manifest.permission.CAMERA
        ) == PackageManager.PERMISSION_GRANTED
    }

    fun checkGalleryPermission(context: Context): Boolean {
        val galleryPermission = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            Manifest.permission.READ_MEDIA_IMAGES
        } else {
            Manifest.permission.READ_EXTERNAL_STORAGE
        }
        return ContextCompat.checkSelfPermission(
            context,
            galleryPermission
        ) == PackageManager.PERMISSION_GRANTED
    }


    fun shouldShowCameraPermissionRationale(activity: Activity): Boolean {
        return ActivityCompat.shouldShowRequestPermissionRationale(
            activity,
            Manifest.permission.CAMERA
        )
    }

    fun shouldShowGalleryPermissionRationale(activity: Activity): Boolean {
        val galleryPermission = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            Manifest.permission.READ_MEDIA_IMAGES
        } else {
            Manifest.permission.READ_EXTERNAL_STORAGE

        }
        return ActivityCompat.shouldShowRequestPermissionRationale(activity, galleryPermission)
    }
}