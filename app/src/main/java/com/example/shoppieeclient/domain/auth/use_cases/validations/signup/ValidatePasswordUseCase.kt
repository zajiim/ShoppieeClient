package com.example.shoppieeclient.domain.auth.use_cases.validations

import com.example.shoppieeclient.R
import com.example.shoppieeclient.domain.auth.models.validation.ValidationResult
import com.example.shoppieeclient.utils.UiText
import com.example.shoppieeclient.utils.isPasswordValid

class ValidatePasswordUseCase {
    operator fun invoke(password: String): ValidationResult {
        if (password.length < 8) {
            return ValidationResult(
                successful = false,
                errorMessage = UiText.StringResource(
                    R.string.validation_password_must_contain_8_letters
                )
            )
        }
        if (!isPasswordValid(password)) {
            return ValidationResult(
                successful = false,
                errorMessage = UiText.StringResource(
                    R.string.validation_password_must_contain_1_letter_and_1_number
                )
            )
        }

        return ValidationResult(
            successful = true,
            errorMessage = null
        )
    }
}