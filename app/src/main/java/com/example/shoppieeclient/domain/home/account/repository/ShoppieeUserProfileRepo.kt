package com.example.shoppieeclient.domain.home.account.repository

import com.example.shoppieeclient.domain.home.account.models.ProfileDataModel
import com.example.shoppieeclient.domain.home.account.models.UpdateProfileModel
import com.example.shoppieeclient.utils.Resource
import kotlinx.coroutines.flow.Flow

interface ShoppieeUserProfileRepo {
    fun updateProfile(name: String, profileImage: String): Flow<Resource<UpdateProfileModel>>

    fun getProfileData(): Flow<Resource<ProfileDataModel>>
}