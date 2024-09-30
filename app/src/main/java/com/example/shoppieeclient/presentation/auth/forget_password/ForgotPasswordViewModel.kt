package com.example.shoppieeclient.presentation.auth.forget_password

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.example.shoppieeclient.domain.auth.use_cases.validations.forgot_password.ForgotPasswordValidationUseCase

private const val TAG = "ForgotPasswordViewModel"
class ForgotPasswordViewModel(
    private val forgotPasswordValidationUseCase: ForgotPasswordValidationUseCase
): ViewModel() {

    var forgotPasswordFormState by mutableStateOf(ForgotPasswordStates())

    fun onEvent(event: ForgotPasswordEvents) {
        when(event) {
            is ForgotPasswordEvents.EmailChanged -> {
                forgotPasswordFormState = forgotPasswordFormState.copy(email = event.email)
                validateEmail()
            }
            ForgotPasswordEvents.Submit -> {
                if (validateEmail()) {
                    Log.e(TAG, "onEvent: invoked", )
                }
            }
        }
    }

    private fun validateEmail(): Boolean {
        val emailResult = forgotPasswordValidationUseCase.validateEmail(email = forgotPasswordFormState.email)
        forgotPasswordFormState = forgotPasswordFormState.copy(emailError = emailResult.errorMessage)
        return emailResult.successful
    }
}