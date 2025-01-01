package com.example.shoppieeclient.data.home.account.remote.mapper

import com.example.shoppieeclient.data.home.account.remote.dto.ProfileDataDto
import com.example.shoppieeclient.data.home.account.remote.dto.UpdateProfileBody
import com.example.shoppieeclient.data.home.account.remote.dto.UpdateProfileResultDto
import com.example.shoppieeclient.domain.home.account.models.ProfileDataModel
import com.example.shoppieeclient.domain.home.account.models.UpdateProfileModel

fun UpdateProfileResultDto.toUpdateProfileModel(): UpdateProfileModel {
    return UpdateProfileModel(
        name = this.name ?: "",
        profileImage = this.profileImage ?: ""
    )
}

fun ProfileDataDto.toProfileDataModel(): ProfileDataModel {
    return ProfileDataModel(
        username = this.username,
        email = this.email,
        profileImage = this.profileImage
    )
}