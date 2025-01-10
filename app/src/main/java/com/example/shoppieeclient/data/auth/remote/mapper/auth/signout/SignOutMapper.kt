package com.example.shoppieeclient.data.auth.remote.mapper.auth.signout

import com.example.shoppieeclient.data.auth.remote.dto.auth.signout.SignOutResponseDto
import com.example.shoppieeclient.domain.auth.models.auth.signout.SignOutModel

fun SignOutResponseDto.toSignOutModel(): SignOutModel {
    return SignOutModel(
        status = this.status,
        message = this.message
    )
}