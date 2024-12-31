package com.example.shoppieeclient.domain.home.account.repository

import android.net.Uri

interface AccountsCloudinaryRepo {
    suspend fun uploadImage(imageUri: Uri, userName: String): String
}