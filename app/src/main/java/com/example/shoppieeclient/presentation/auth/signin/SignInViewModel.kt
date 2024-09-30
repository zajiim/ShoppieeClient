package com.example.shoppieeclient.presentation.auth.signin

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.example.shoppieeclient.domain.auth.use_cases.validations.signin.SignInValidationsUseCases
import com.example.shoppieeclient.domain.auth.use_cases.validations.signup.SignupValidationsUseCase

private const val TAG = "SignInViewModel"
class SignInViewModel(
    private val signInValidationsUseCase: SignInValidationsUseCases
): ViewModel() {

    var signInFormState by mutableStateOf(SignInState())

    fun onEvent(event: SignInEvents) {
        when(event) {
            is SignInEvents.EmailChanged -> {
                signInFormState = signInFormState.copy(email = event.email)
                validateEmail()
            }
            is SignInEvents.PasswordChanged -> {
                signInFormState = signInFormState.copy(password = event.password)
                validatePassword()
            }
            is SignInEvents.VisiblePasswordChanged -> {
                signInFormState = signInFormState.copy(visiblePassword = event.isVisiblePassword)
            }
            SignInEvents.Submit -> {
                if (validateEmail() && validatePassword()) {
                    Log.e(TAG, "onEvent: ======Signup Api call")
                }
            }
        }
    }

    private fun validateEmail(): Boolean {
        val emailResult = signInValidationsUseCase.validateEmail(signInFormState.email)
        signInFormState = signInFormState.copy(emailError = emailResult.errorMessage)
        return emailResult.successful
    }

    private fun validatePassword(): Boolean {
        val passwordResult = signInValidationsUseCase.validatePassword(signInFormState.password)
        signInFormState = signInFormState.copy(passwordError = passwordResult.errorMessage)
        return passwordResult.successful
    }

}