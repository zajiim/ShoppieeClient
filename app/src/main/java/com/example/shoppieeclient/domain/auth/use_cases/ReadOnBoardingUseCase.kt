package com.example.shoppieeclient.domain.auth.use_cases

import com.example.shoppieeclient.domain.auth.datamanager.LocalUserManager
import kotlinx.coroutines.flow.Flow

class ReadOnBoardingUseCase(
    private val localUserManager: LocalUserManager
) {
    operator fun invoke(): Flow<Boolean> {
        return localUserManager.readOnBoardingValue()
    }
}