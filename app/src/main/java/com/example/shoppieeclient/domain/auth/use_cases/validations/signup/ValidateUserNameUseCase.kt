package com.example.shoppieeclient.domain.auth.use_cases.validations.signup

import com.example.shoppieeclient.R
import com.example.shoppieeclient.domain.auth.models.validation.ValidationResult
import com.example.shoppieeclient.utils.UiText

class ValidateUserNameUseCase {
    operator fun invoke(userName: String): ValidationResult {
        if (userName.length < 4) {
            return ValidationResult(
                successful = false,
                errorMessage = UiText.StringResource(
                    R.string.validation_please_provide_a_valid_username
                )
            )
        }
        return ValidationResult(
            successful = true,
            errorMessage = null
        )
    }
}