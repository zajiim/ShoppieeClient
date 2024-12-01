package com.example.shoppieeclient.presentation.home.accounts.components

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun CustomImagePickerDialog(
    modifier: Modifier = Modifier,
    onDismiss: () -> Unit,
    onCameraClick: () -> Unit,
    onGalleryClick: () -> Unit
) {
    AlertDialog(
        onDismissRequest = onDismiss,
        title = { Text(text = "Choose an option") },
        text = {
            Column {
                TextButton(onClick = onCameraClick) {
                    Text(text = "Camera")
                }
                TextButton(onClick = onGalleryClick) {
                    Text(text = "Gallery")
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