package com.example.shoppieeclient.domain.auth.use_cases.validations.auth

import com.example.shoppieeclient.R
import com.example.shoppieeclient.domain.auth.models.validation.ValidationResult
import com.example.shoppieeclient.utils.UiText

class ValidateConfirmPasswordUseCase {
    operator fun invoke(password: String, confirmPassword: String): ValidationResult {
        if (confirmPassword.isEmpty()) {
            return ValidationResult(
                successful = false,
                errorMessage = UiText.StringResource(
                    R.string.validation_confirm_password_please_confirm_your_password
                )
            )
        }
        if (confirmPassword != password) {
            return ValidationResult(
                successful = false,
                errorMessage = UiText.StringResource(
                    R.string.validation_confirm_password_does_not_match
                )
            )
        }

        return ValidationResult(
            successful = true,
            errorMessage = null
        )
    }
}