package com.example.shoppieeclient.domain.home.account.use_cases

import com.example.shoppieeclient.domain.home.account.models.ProfileDataModel
import com.example.shoppieeclient.domain.home.account.repository.ShoppieeUserProfileRepo
import com.example.shoppieeclient.utils.Resource
import kotlinx.coroutines.flow.Flow

class GetProfileDataUseCase(
    private val shoppieeUserProfileRepo: ShoppieeUserProfileRepo
) {
    operator fun invoke(): Flow<Resource<ProfileDataModel>> {
        return shoppieeUserProfileRepo.getProfileData()
    }
}