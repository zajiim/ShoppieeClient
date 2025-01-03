package com.example.shoppieeclient.domain.auth.use_cases.auth.siginin

import com.example.shoppieeclient.domain.auth.datamanager.LocalUserManager
import kotlinx.coroutines.flow.Flow

class ReadUsernameUseCase(private val localUserManager: LocalUserManager) {
    operator fun invoke(): Flow<String?> {
        return localUserManager.readUserName()
    }
}
