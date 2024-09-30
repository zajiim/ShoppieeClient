package com.example.shoppieeclient.domain.auth.use_cases.validations

import com.example.shoppieeclient.domain.auth.use_cases.validations.signup.ValidateConfirmPasswordUseCase
import com.example.shoppieeclient.domain.auth.use_cases.validations.signup.ValidateEmailUseCase
import com.example.shoppieeclient.domain.auth.use_cases.validations.signup.ValidatePasswordUseCase
import com.example.shoppieeclient.domain.auth.use_cases.validations.signup.ValidateUserNameUseCase

data class SignupValidationsUseCase(
    val validateUserName: ValidateUserNameUseCase = ValidateUserNameUseCase(),
    val validateEmail: ValidateEmailUseCase = ValidateEmailUseCase(),
    val validatePassword: ValidatePasswordUseCase = ValidatePasswordUseCase(),
    val validateConfirmPassword: ValidateConfirmPasswordUseCase = ValidateConfirmPasswordUseCase()
)
