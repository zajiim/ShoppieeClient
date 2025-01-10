package com.example.shoppieeclient.domain.auth.use_cases.auth.siginin

import com.example.shoppieeclient.domain.auth.datamanager.LocalUserManager
import com.example.shoppieeclient.domain.common.model.UserDetails
import kotlinx.coroutines.flow.Flow

class ReadUserDetailsUseCase(private val localUserManager: LocalUserManager) {
    operator fun invoke(): Flow<UserDetails> {
        return localUserManager.readUserDetails()
    }
}