package com.example.shoppieeclient.data.auth.remote.mapper.auth

import com.example.shoppieeclient.data.auth.remote.dto.auth.ValidUserResponseDto
import com.example.shoppieeclient.domain.auth.models.auth.ValidUserModel

fun ValidUserResponseDto.toValidUserModel(): ValidUserModel {
    return ValidUserModel(
        status = status,
        message = message,
        result = result
    )
}