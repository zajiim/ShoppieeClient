package com.example.shoppieeclient.domain.auth.datamanager

import kotlinx.coroutines.flow.Flow

interface LocalUserManager {
    suspend fun saveOnBoardingValue(value: Boolean)
    fun readOnBoardingValue(): Flow<Boolean>
}