package com.example.shoppieeclient.presentation.home.accounts

import android.Manifest
import android.app.Activity
import android.content.Context
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Build
import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.shoppieeclient.domain.auth.use_cases.auth.siginin.SaveUserDetailsUseCase
import com.example.shoppieeclient.domain.home.account.use_cases.GetProfileDataUseCase
import com.example.shoppieeclient.domain.home.account.use_cases.UpdateProfileDataUseCase
import com.example.shoppieeclient.domain.home.account.use_cases.UploadImageUseCase
import com.example.shoppieeclient.utils.Resource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

private const val TAG = "AccountsViewModel"
class AccountsViewModel(
    private val uploadImageUseCase: UploadImageUseCase,
    private val updateProfileDataUseCase: UpdateProfileDataUseCase,
    private val getUserDataUseCase: GetProfileDataUseCase,
    private val saveUserDetailsUseCase: SaveUserDetailsUseCase
) : ViewModel() {
    var uiState by mutableStateOf(AccountsStates())
        private set

    fun onEvent(event: AccountsEvent) {
        when (event) {
            AccountsEvent.OnInitial -> {
                fetchUserDetail()
            }


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
                addImage(event.imageUrl, event.userName)
            }

            AccountsEvent.GrantedCameraPermission -> {
                uiState = uiState.copy(
                    cameraPermissionGranted = true, isAlertBoxOpen = false
                )
            }

            AccountsEvent.GrantedGalleryPermission -> {
                uiState = uiState.copy(
                    galleryPermissionGranted = true, isAlertBoxOpen = false
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

            is AccountsEvent.NameChanged -> {
                uiState = uiState.copy(
                    profileName = event.name
                )

            }

            AccountsEvent.ShowAlertBox -> {
                uiState = uiState.copy(
                    isAlertBoxOpen = true
                )
            }

            is AccountsEvent.UpdateProfile -> {
                updateProfile(event.name, event.profileImage)
            }
            AccountsEvent.DismissUpdateAlertBox -> {
                uiState = uiState.copy(
                    updateProfileSuccessAlertBox = false
                )
            }

        }
    }

    private fun fetchUserDetail() = viewModelScope.launch {
        withContext(Dispatchers.IO) {
            getUserDataUseCase().collect { result ->
                Log.e(TAG, "fetchUserDetail: result is $result", )
                when (result) {
                    is Resource.Error -> {
                        uiState = uiState.copy(
                            isLoading = false,
                            error = result.message ?: "Something went wrong"
                        )
                    }

                    is Resource.Loading -> {
                        uiState = uiState.copy(
                            isLoading = true,
                            error = null
                        )
                    }

                    is Resource.Success -> {
                        Log.e(TAG, "fetchUserDetail:>>>>>> ${uiState.profileName} ", )
                        uiState = uiState.copy(
                            isLoading = false,
                            error = null,
                            profileName = result.data?.username ?: "",
                            email = result.data?.email ?: "",
                            profileImageUrl = result.data?.profileImage ?: ""
                        )
                    }
                }
            }
        }
    }

    private fun updateProfile(name: String, profileImage: String) {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                updateProfileDataUseCase(name, profileImage).collect { result ->
                    when (result) {
                        is Resource.Error -> {
                            uiState = uiState.copy(
                                updatingProfile = false,
                                updateProfileSuccess = false,
                                updateProfileError = result.message ?: "Something went wrong"
                            )
                        }

                        is Resource.Loading -> {
                            uiState = uiState.copy(
                                updatingProfile = true,
                                updateProfileError = null
                            )
                        }

                        is Resource.Success -> {
                            try {
                                saveUserDetailsUseCase(uiState.profileName, uiState.profileImageUrl)
                                uiState = uiState.copy(
                                    updatingProfile = false,
                                    updateProfileError = null,
                                    updateProfileSuccess = true,
                                    updateProfileSuccessAlertBox = true
                                )
                            } catch (e: Exception) {
                                uiState = uiState.copy(
                                    updatingProfile = false,
                                    updateProfileError = e.message ?: "Something went wrong"
                                )
                            }
                        }
                    }
                }
            }
        }
    }

    private fun addImage(imageUrl: String, userName: String) {
        viewModelScope.launch {
            uiState = uiState.copy(isUploadingProfilePic = true, uploadError = null)
            try {
                val result = uploadImageUseCase(imageUri = Uri.parse(imageUrl), userName = userName)
                uiState = uiState.copy(
                    profileImageUrl = result, isUploadingProfilePic = false
                )
            } catch (e: Exception) {
                uiState = uiState.copy(
                    isUploadingProfilePic = false, uploadError = e.message ?: "Upload failed"
                )
            }
        }
    }

    fun checkCameraPermission(context: Context): Boolean {
        return ContextCompat.checkSelfPermission(
            context, Manifest.permission.CAMERA
        ) == PackageManager.PERMISSION_GRANTED
    }

    fun checkGalleryPermission(context: Context): Boolean {
        val galleryPermission = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            Manifest.permission.READ_MEDIA_IMAGES
        } else {
            Manifest.permission.READ_EXTERNAL_STORAGE
        }
        return ContextCompat.checkSelfPermission(
            context, galleryPermission
        ) == PackageManager.PERMISSION_GRANTED
    }


    fun shouldShowCameraPermissionRationale(activity: Activity): Boolean {
        return ActivityCompat.shouldShowRequestPermissionRationale(
            activity, Manifest.permission.CAMERA
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