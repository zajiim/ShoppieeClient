package com.example.shoppieeclient.domain.auth.models.validation

import com.example.shoppieeclient.utils.UiText

data class ValidationResult(
    val successful: Boolean,
    val errorMessage: UiText?= null
)
