package com.example.shoppieeclient.domain.auth.use_cases

import com.example.shoppieeclient.domain.auth.datamanager.LocalUserManager

class SaveOnBoardingUseCase(
    private val localUserManager: LocalUserManager
) {
    suspend operator fun invoke(onBoardingValue: Boolean) {
        localUserManager.saveOnBoardingValue(onBoardingValue)
    }
}