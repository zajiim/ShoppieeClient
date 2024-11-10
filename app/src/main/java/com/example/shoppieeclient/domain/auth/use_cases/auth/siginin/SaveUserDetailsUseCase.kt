package com.example.shoppieeclient.domain.auth.use_cases.auth.siginin

import com.example.shoppieeclient.domain.auth.datamanager.LocalUserManager

class SaveUserDetailsUseCase(private val localUserManager: LocalUserManager) {
    suspend operator fun invoke(name: String, userImage: String) {
        localUserManager.saveUserDetails(name = name, profileImage = userImage)
    }
}