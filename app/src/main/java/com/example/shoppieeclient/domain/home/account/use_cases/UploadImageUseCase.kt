package com.example.shoppieeclient.domain.home.account.use_cases

import android.net.Uri
import com.example.shoppieeclient.domain.home.account.repository.AccountsCloudinaryRepo
import com.example.shoppieeclient.domain.home.home.models.HomeResultModel
import com.example.shoppieeclient.utils.Resource
import kotlinx.coroutines.flow.Flow

class UploadImageUseCase(
    private val accountsCloudinaryRepo: AccountsCloudinaryRepo
) {
    suspend operator fun invoke(imageUri: Uri, userName: String):String {
        return accountsCloudinaryRepo.uploadImage(imageUri, userName)
    }
}