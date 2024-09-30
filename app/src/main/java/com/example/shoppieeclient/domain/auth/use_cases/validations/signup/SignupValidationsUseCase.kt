package com.example.shoppieeclient.domain.auth.use_cases.validations.signup

import com.example.shoppieeclient.domain.auth.use_cases.validations.auth.ValidateConfirmPasswordUseCase
import com.example.shoppieeclient.domain.auth.use_cases.validations.auth.ValidateEmailUseCase
import com.example.shoppieeclient.domain.auth.use_cases.validations.auth.ValidatePasswordUseCase
import com.example.shoppieeclient.domain.auth.use_cases.validations.auth.ValidateUserNameUseCase

data class SignupValidationsUseCase(
    val validateUserName: ValidateUserNameUseCase = ValidateUserNameUseCase(),
    val validateEmail: ValidateEmailUseCase = ValidateEmailUseCase(),
    val validatePassword: ValidatePasswordUseCase = ValidatePasswordUseCase(),
    val validateConfirmPassword: ValidateConfirmPasswordUseCase = ValidateConfirmPasswordUseCase()
)
