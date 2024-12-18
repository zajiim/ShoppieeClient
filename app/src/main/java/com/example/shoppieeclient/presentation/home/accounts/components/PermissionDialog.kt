package com.example.shoppieeclient.presentation.home.accounts.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun PermissionDialog(
    permissionTextProvider: PermissionTextProvider,
    isPermanentlyDeclined: Boolean,
    onDismiss: () -> Unit,
    onOkClick: () -> Unit,
    onGoToAppSettingsClick: () -> Unit,
    modifier: Modifier = Modifier
    ) {

    AlertDialog(
        onDismissRequest = onDismiss,
        title = { Text(text = "Permission Required") },
        text = {
            Column(modifier = modifier.fillMaxWidth()) {
                Text(
                    text = permissionTextProvider.getDescription(
                        isPermanentlyDeclined = isPermanentlyDeclined
                    )
                )

                TextButton(
                    onClick = if (isPermanentlyDeclined) {
                        onGoToAppSettingsClick
                    } else {
                        onOkClick
                    }
                ) {
                    Text(text = if (isPermanentlyDeclined) "Grant Permission" else "Ok")
                }
            }
        },
        confirmButton = {},
        dismissButton = {
            TextButton(onClick = onDismiss) {
                Text("Cancel")
            }
        }

    )

}

interface PermissionTextProvider {
    fun getDescription(isPermanentlyDeclined: Boolean): String
}


class CameraPermissionTextProvider: PermissionTextProvider {
    override fun getDescription(isPermanentlyDeclined: Boolean): String {
        return if (isPermanentlyDeclined) {
            "It seems you permanently declined camera permission." +
                    "You can go to app settings to grant it."
        } else {
            "This app needs access to your camera so that you can upload the profile picture"
        }
    }
}

class GalleryPermissionTextProvider: PermissionTextProvider {
    override fun getDescription(isPermanentlyDeclined: Boolean): String {
        return if (isPermanentlyDeclined) {
            "It seems you permanently declined gallery permission." +
                    "You can go to app settings to grant it."
        } else {
            "This app needs access to your gallery so that you can upload your best picture from gallery"
        }
    }
}

