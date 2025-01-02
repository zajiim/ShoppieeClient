package com.example.shoppieeclient.presentation.home.accounts

import android.Manifest
import android.app.Activity
import android.os.Build
import android.util.Log
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.annotation.RequiresApi
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.outlined.Edit
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.CachePolicy
import coil.request.ImageRequest
import com.example.shoppieeclient.R
import com.example.shoppieeclient.presentation.auth.components.CustomButton
import com.example.shoppieeclient.presentation.auth.components.CustomTextField
import com.example.shoppieeclient.presentation.common.components.CustomAlertBox
import com.example.shoppieeclient.presentation.common.components.CustomLineProgressIndicator
import com.example.shoppieeclient.presentation.home.accounts.components.CustomImagePickerDialog
import com.example.shoppieeclient.presentation.home.accounts.components.CustomProfileImage
import com.example.shoppieeclient.presentation.home.accounts.components.PermissionRationaleDialogBox
import com.example.shoppieeclient.presentation.home.details.components.CustomNavigationTopAppBar
import com.example.shoppieeclient.ui.theme.PrimaryBlue
import com.example.shoppieeclient.utils.openAppSettings
import com.example.shoppieeclient.utils.saveBitmapToFile
import org.koin.androidx.compose.koinViewModel

private const val TAG = "AccountsScreen"
@RequiresApi(Build.VERSION_CODES.TIRAMISU)
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AccountsScreen(
    modifier: Modifier = Modifier,
    onNavigateClick: () -> Unit,
    accountsViewModel: AccountsViewModel = koinViewModel()
) {
    val ctx = LocalContext.current
    val activity = ctx as Activity
    val uiState = accountsViewModel.uiState

    LaunchedEffect(Unit) {
        accountsViewModel.onEvent(AccountsEvent.OnInitial)
    }

    Log.e(TAG, "AccountsScreen: profileName=====> ${uiState.profileName}")

    val cameraLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.TakePicturePreview(),
        onResult = { bitmap ->
            bitmap?.let {
                val imageUri = saveBitmapToFile(ctx, bitmap)
                Log.e(TAG, "Image: $imageUri")
//                accountsViewModel.updateProfileImage(imageUri.toString())
//                accountsViewModel.onEvent(AccountsEvent.UpdateProfileImage(imageUrl = imageUri.toString()))
                imageUri?.let {
                    accountsViewModel.onEvent(AccountsEvent.UpdateProfileImage(imageUrl = it.toString(), userName = uiState.profileName))
                }
            }
        }
    )

    val galleryLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.GetContent(),
        onResult = { uri ->
            uri?.let { imageUri ->
//                accountsViewModel.updateProfileImage(imageUri.toString())
//                accountsViewModel.onEvent(AccountsEvent.UpdateProfileImage(imageUrl = imageUri.toString()))
                imageUri.let {
                    Log.e(TAG, "ImageURI: $it", )
                    accountsViewModel.onEvent(AccountsEvent.UpdateProfileImage(imageUrl = it.toString(), userName = uiState.profileName))
                }
            }

        }
    )

    val cameraPermissionLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.RequestPermission(),
        onResult = { isGranted ->
            if (isGranted) {
//                accountsViewModel.grantedCameraPermission()
                accountsViewModel.onEvent(AccountsEvent.GrantedCameraPermission)
                cameraLauncher.launch(null)
            } else {
                Log.e(TAG, "AccountsScreen: Camera permission denied")
                if (accountsViewModel.shouldShowCameraPermissionRationale(activity)) {
//                    accountsViewModel.setGoToCameraSettings(true)
                    accountsViewModel.onEvent(AccountsEvent.GoToCameraSettings(true))
                } else {
                    openAppSettings(ctx)
                }
            }
        }
    )

    val galleryPermissionLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.RequestPermission(),
        onResult = { isGranted ->
            Log.e(TAG, "Gallery permission launcher invoked. Is granted: $isGranted")
            if (isGranted) {
//                accountsViewModel.grantedGalleryPermission()
                accountsViewModel.onEvent(AccountsEvent.GrantedGalleryPermission)
                galleryLauncher.launch("image/*")
            } else {
                Log.e(TAG, "AccountsScreen: Gallery permission denied")
                if (accountsViewModel.shouldShowGalleryPermissionRationale(activity)) {
//                    accountsViewModel.setGoToGallerySettings(true)
                    accountsViewModel.onEvent(AccountsEvent.GoToGallerySettings(true))
                } else {
                    openAppSettings(ctx)
                }
            }
        }
    )

    if (uiState.goToCameraSettings) {
        PermissionRationaleDialogBox(
            title = "Camera Permission",
            message = "Camera permission is required to take photos for your profile",
            onConfirm = {
                cameraPermissionLauncher.launch(Manifest.permission.CAMERA)
//                accountsViewModel.setGoToCameraSettings(false)
                accountsViewModel.onEvent(AccountsEvent.GoToCameraSettings(false))
            },
            onDismiss = {
//                accountsViewModel.setGoToCameraSettings(false)
                accountsViewModel.onEvent(AccountsEvent.GoToCameraSettings(false))
            }
        )
    }


    if (uiState.goToGallerySettings) {
        PermissionRationaleDialogBox(
            title = "Gallery Permission",
            message = "Gallery permission is required to upload photos for your profile",
            onConfirm = {
                val galleryPermission = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
                    Manifest.permission.READ_MEDIA_IMAGES
                } else {
                    Manifest.permission.READ_EXTERNAL_STORAGE
                }
                galleryPermissionLauncher.launch(galleryPermission)
//                accountsViewModel.setGoToGallerySettings(false)
                accountsViewModel.onEvent(AccountsEvent.GoToGallerySettings(false))
            },
            onDismiss = {
//                accountsViewModel.setGoToGallerySettings(false)
                accountsViewModel.onEvent(AccountsEvent.GoToGallerySettings(false))
            }
        )
    }


    if (uiState.updateProfileSuccessAlertBox) {
        CustomAlertBox(
            onDismiss = {
                accountsViewModel.onEvent(AccountsEvent.DismissUpdateAlertBox)
            },
            onButtonClick = {
                accountsViewModel.onEvent(AccountsEvent.DismissUpdateAlertBox)
            },
            animationRes = if (uiState.updateProfileSuccess) R.raw.success else R.raw.failure,
            message = "User profile updated successfully",
            buttonText = "Ok"
        )
    }





    if (uiState.isAlertBoxOpen) {
        Log.e(TAG, "AccountsScreen: invoked" )
        CustomImagePickerDialog (
            onDismiss = { accountsViewModel.onEvent(AccountsEvent.DismissDialog) },
            onCameraClick = {
                Log.e(TAG, "AccountsScreen: camera clicked")
                if (accountsViewModel.checkCameraPermission(ctx)) {
                    cameraLauncher.launch(null)
//                    accountsViewModel.dismissAlertBox()
                    accountsViewModel.onEvent(AccountsEvent.DismissAlertBox)
                } else {
                    cameraPermissionLauncher.launch(Manifest.permission.CAMERA)
                }
            },
            onGalleryClick = {
                Log.e(TAG, "AccountsScreen: gallery clicked")
                val galleryPermission = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
                    Manifest.permission.READ_MEDIA_IMAGES
                } else {
                    Manifest.permission.READ_EXTERNAL_STORAGE
                }
                if (accountsViewModel.checkGalleryPermission(ctx)) {
                    galleryLauncher.launch("image/*")
//                    accountsViewModel.dismissAlertBox()
                    accountsViewModel.onEvent(AccountsEvent.DismissAlertBox)
                } else {
                    Log.e(TAG, "AccountsScreen: else case for gallery per")
                    galleryPermissionLauncher.launch(galleryPermission)
                }
            }
        )
    }


    Column(modifier = modifier
        .fillMaxSize()
        .padding(horizontal = 20.dp),
    ) {
        CustomNavigationTopAppBar(
            modifier = Modifier
                .fillMaxWidth(),
            title = "Profile",
            navigationIcon = {
                IconButton(
                    onClick = onNavigateClick,
                    modifier = Modifier
                        .wrapContentSize()
                        .clip(CircleShape)
                        .background(Color.White)
                ) {
                    Icon(
                        imageVector = Icons.AutoMirrored.Default.ArrowBack,
                        contentDescription = "Go back"
                    )
                }
            },
            actions = {
                IconButton(
                    onClick = { },
                    modifier = Modifier
                        .wrapContentSize()
                        .clip(CircleShape)
                        .background(Color.White)
                ) {
                    Icon(
                        imageVector = Icons.Outlined.Edit,
                        contentDescription = "Edit Success",
                        tint = PrimaryBlue
                    )
                }
            }

        )

        Box(modifier = Modifier
            .fillMaxWidth()
        ) {
            CustomProfileImage(
                modifier = Modifier.fillMaxWidth(),
                profileImage = uiState.profileImageUrl,
                isLoading = uiState.isUploadingProfilePic
            )
            Box(modifier = Modifier
                .align(Alignment.BottomCenter)
                .offset(y = 16.dp)
                .size(32.dp)
                .clip(CircleShape)
                .background(PrimaryBlue)
                .clickable {
                    accountsViewModel.onEvent(AccountsEvent.ShowAlertBox)
                },
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    painter = painterResource(R.drawable.ic_add_image),
                    contentDescription = "Edit profile image",
                    tint = Color.White,
                    modifier = Modifier.size(20.dp)
                )
            }
        }

        Text(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 24.dp),
            text = uiState.profileName,
            style = MaterialTheme.typography.bodyLarge.copy(fontWeight = FontWeight.Bold),
            textAlign = TextAlign.Center
        )

        Spacer(modifier = Modifier.height(32.dp))

        CustomTextField(
            modifier = Modifier.fillMaxWidth(),
            title = "Full Name",
            titleTextColor = Color.Black,
            textValue = accountsViewModel.uiState.profileName,
            onValueChange = { accountsViewModel.onEvent(AccountsEvent.NameChanged(it)) },
            hasError = false,
            hint = "Full name",
            keyboardType = KeyboardType.Text,
            cursorColor = PrimaryBlue,
            onTrailingIconClicked = {},
        )

        Spacer(modifier = Modifier.height(24.dp))

        CustomTextField(
            modifier = Modifier.fillMaxWidth(),
            title = "Email Address",
            titleTextColor = Color.Black,
            readOnly = true,
            textValue = accountsViewModel.uiState.email,
            onValueChange = { },
            hasError = false,
            hint = "Email address",
            keyboardType = KeyboardType.Text,
            cursorColor = PrimaryBlue,
            onTrailingIconClicked = {},
        )

        Spacer(modifier = Modifier.height(24.dp))

        CustomButton(
            modifier = Modifier.fillMaxWidth(),
            text = "Update",
            backgroundColor = PrimaryBlue,
            onButtonClicked = {
                accountsViewModel.onEvent(AccountsEvent.UpdateProfile(name = uiState.profileName, profileImage = uiState.profileImageUrl))
            },
            isLoading = false,
            contentColor = Color.White
        )

    }

}

