package com.example.shoppieeclient.domain.auth.use_cases.validations.forgot_password

import com.example.shoppieeclient.domain.auth.use_cases.validations.auth.ValidateEmailUseCase

data class ForgotPasswordValidationUseCase(
    val validateEmail: ValidateEmailUseCase
)
