package com.example.shoppieeclient.domain.auth.datamanager

import com.example.shoppieeclient.domain.common.model.UserDetails
import kotlinx.coroutines.flow.Flow

interface LocalUserManager {
    suspend fun saveOnBoardingValue(value: Boolean)
    fun readOnBoardingValue(): Flow<Boolean>
    suspend fun saveAppToken(token: String)
    fun readAppToken(): Flow<String?>
    suspend fun saveUserDetails(name: String, profileImage: String)
    fun readUserDetails(): Flow<UserDetails>
//    fun readUserImage(): Flow<String?>
//    fun readUserName(): Flow<String?>
}