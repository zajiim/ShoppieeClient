package com.example.shoppieeclient.presentation.home.accounts

import android.Manifest
import android.app.Activity
import android.content.Context
import android.graphics.Bitmap
import android.net.Uri
import android.os.Build
import android.os.Environment
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
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.core.content.FileProvider
import coil.compose.AsyncImage
import com.example.shoppieeclient.R
import com.example.shoppieeclient.presentation.auth.components.CustomButton
import com.example.shoppieeclient.presentation.auth.components.CustomTextField
import com.example.shoppieeclient.presentation.home.accounts.components.CustomImagePickerDialog
import com.example.shoppieeclient.presentation.home.details.components.CustomNavigationTopAppBar
import com.example.shoppieeclient.ui.theme.PrimaryBlue
import org.koin.androidx.compose.koinViewModel
import java.io.File
import java.io.FileOutputStream
import java.io.IOException
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale
import kotlin.contracts.contract

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

    val cameraLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.TakePicturePreview(),
        onResult = { bitmap ->
            bitmap?.let {
                val imageUri = saveBitmapToFile(ctx, bitmap)
                accountsViewModel.updateProfileImage(imageUri.toString())
            }
        }
    )

    val galleryLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.GetContent(),
        onResult = { uri ->
            uri?.let { imageUri ->
                accountsViewModel.updateProfileImage(imageUri.toString())
            }

        }
    )

    val cameraPermissionLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.RequestPermission(),
        onResult = { isGranted ->
            if (isGranted) {
                accountsViewModel.grantedCameraPermission()
                cameraLauncher.launch(null)
            } else {
                Log.e(TAG, "AccountsScreen: Camera permission denied", )
            }
        }
    )

    val galleryPermissionLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.RequestPermission(),
        onResult = { isGranted ->
            Log.e(TAG, "Gallery permission launcher invoked. Is granted: $isGranted")
            if (isGranted) {
                accountsViewModel.grantedGalleryPermission()
                galleryLauncher.launch("image/*")
            } else {
                Log.e(TAG, "AccountsScreen: Gallery permission denied",)
            }
        }
    )



    if (uiState.isAlertBoxOpen) {
        Log.e(TAG, "AccountsScreen: invoked" )
        CustomImagePickerDialog (
            onDismiss = { accountsViewModel.onEvent(AccountsEvent.DismissDialog) },
            onCameraClick = {
                Log.e(TAG, "AccountsScreen: camera clicked", )
                if (accountsViewModel.checkCameraPermission(ctx)) {
                    cameraLauncher.launch(null)
                    accountsViewModel.dismissAlertBox()
                } else {
                    cameraPermissionLauncher.launch(Manifest.permission.CAMERA)
                }
            },
            onGalleryClick = {
                Log.e(TAG, "AccountsScreen: gallery clicked", )
                val galleryPermission = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
                    Manifest.permission.READ_MEDIA_IMAGES
                } else {
                    Manifest.permission.READ_EXTERNAL_STORAGE
                }
                if (accountsViewModel.checkGalleryPermission(ctx)) {
                    galleryLauncher.launch("image/*")
                    accountsViewModel.dismissAlertBox()
                    // TODO: launch gallery
                } else {
                    Log.e(TAG, "AccountsScreen: else case for gallery per", )
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
            AsyncImage(
//                model = "https://picsum.photos/200",
                model = uiState.profileImageUrl.ifEmpty { "https://picsum.photos/200" },
                contentDescription = "Profile Image",
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .align(Alignment.Center)
                    .size(90.dp)
                    .clip(CircleShape)
            )
            Box(modifier = Modifier
                .align(Alignment.BottomCenter)
                .offset(y = 16.dp)
                .size(32.dp)
                .clip(CircleShape)
                .background(PrimaryBlue)
                .clickable {
                    accountsViewModel.showAlertBox()
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
            text = "John Doe",
            style = MaterialTheme.typography.bodyLarge.copy(fontWeight = FontWeight.Bold),
            textAlign = TextAlign.Center
        )

        Spacer(modifier = Modifier.height(32.dp))

        CustomTextField(
            modifier = Modifier.fillMaxWidth(),
            title = "Full Name",
            titleTextColor = Color.Black,
            textValue = "John Doe",
            onValueChange = { },
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
            textValue = "Johndoe@gmail.com",
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
            onButtonClicked = {},
            isLoading = false,
            contentColor = Color.White
        )

    }

}



fun saveBitmapToFile(context: Context, bitmap: Bitmap): Uri? {
    return try {
        val timeStamp = SimpleDateFormat("yyyyMMdd_HHmmss", Locale.getDefault()).format(Date())
        val storageDir: File? = context.getExternalFilesDirs(Environment.DIRECTORY_PICTURES)?.firstOrNull()
        val imageFile = File.createTempFile("IMG_${timeStamp}", ".jpg", storageDir)

        FileOutputStream(imageFile).use { out ->
            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, out)
        }
        FileProvider.getUriForFile(context, "${context.packageName}.fileprovider", imageFile)
    } catch (e: IOException) {
        e.printStackTrace()
        null
    }
}
