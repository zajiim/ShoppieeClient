package com.example.shoppieeclient.domain.home.account.use_cases

import com.example.shoppieeclient.domain.home.account.models.UpdateProfileModel
import com.example.shoppieeclient.domain.home.account.repository.ShoppieeUserProfileRepo
import com.example.shoppieeclient.utils.Resource
import kotlinx.coroutines.flow.Flow

class UpdateProfileDataUseCase(
    private val shoppieeUserProfileRepo: ShoppieeUserProfileRepo
) {
    operator fun invoke(name: String, profileImage: String): Flow<Resource<UpdateProfileModel>> {
        return shoppieeUserProfileRepo.updateProfile(name, profileImage)
    }
}