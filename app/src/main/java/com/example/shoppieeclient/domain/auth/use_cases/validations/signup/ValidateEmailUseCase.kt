package com.example.shoppieeclient.domain.auth.use_cases.validations

import com.example.shoppieeclient.R
import com.example.shoppieeclient.domain.auth.models.validation.ValidationResult
import com.example.shoppieeclient.utils.UiText
import com.example.shoppieeclient.utils.isEmailValid

class ValidateEmailUseCase {

    operator fun invoke(email: String): ValidationResult {
        if (email.isBlank()) {
            return ValidationResult(
                successful = false,
                errorMessage = UiText.StringResource(
                    R.string.validation_email_cannot_be_empty
                )
            )
        }
        if (!isEmailValid(email)) {
            return ValidationResult(
                successful = false,
                errorMessage = UiText.StringResource(
                    R.string.validation_please_provide_a_valid_email
                )
            )
        }

        return ValidationResult(
            successful = true,
            errorMessage = null
        )
    }
}