package com.example.shoppieeclient.domain.auth.use_cases.validations.signin

import com.example.shoppieeclient.domain.auth.use_cases.validations.auth.ValidateEmailUseCase
import com.example.shoppieeclient.domain.auth.use_cases.validations.auth.ValidatePasswordUseCase

data class SignInValidationsUseCases(
    val validateEmail: ValidateEmailUseCase = ValidateEmailUseCase(),
    val validatePassword: ValidatePasswordUseCase = ValidatePasswordUseCase()
)
