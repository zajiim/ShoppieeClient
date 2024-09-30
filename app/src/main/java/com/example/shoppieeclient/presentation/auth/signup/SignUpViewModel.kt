package com.example.shoppieeclient.presentation.auth.signup

import android.util.Log
import android.widget.Toast
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.ViewModel
import com.example.shoppieeclient.domain.auth.use_cases.validations.SignupValidationsUseCase

private const val TAG = "SignUpViewModel"
class SignUpViewModel(
    private val signUpValidationsUseCase: SignupValidationsUseCase
): ViewModel() {

    var signUpFormState by mutableStateOf(SignUpState())

    fun onEvent(event: SignUpEvents) {
        when(event) {
            is SignUpEvents.UsernameChanged -> {
                signUpFormState = signUpFormState.copy(userName = event.username)
                validateUserName()
            }
            is SignUpEvents.EmailChanged -> {
                signUpFormState = signUpFormState.copy(email = event.email)
                validateEmail()
            }
            is SignUpEvents.PasswordChanged -> {
                signUpFormState = signUpFormState.copy(password = event.password)
                validatePassword()
            }
            is SignUpEvents.ConfirmPasswordChanged -> {
                signUpFormState = signUpFormState.copy(confirmPassword = event.confirmPassword)
                validateConfirmPassword()
            }
            is SignUpEvents.VisiblePasswordChanged -> {
                signUpFormState = signUpFormState.copy(visiblePassword = event.isVisiblePassword)
            }
            SignUpEvents.Submit -> {
                if (validateUserName() && validateEmail() && validatePassword() && validateConfirmPassword()) {
                    Log.e(TAG, "onEvent: ======Signup Api call", )                }
            }
        }
    }

    private fun validateUserName(): Boolean {
        val userNameResult = signUpValidationsUseCase.validateUserName(signUpFormState.userName)
        signUpFormState = signUpFormState.copy(userNameError = userNameResult.errorMessage)
        return userNameResult.successful
    }

    private fun validateEmail(): Boolean {
        val emailResult = signUpValidationsUseCase.validateEmail(signUpFormState.email)
        signUpFormState = signUpFormState.copy(emailError = emailResult.errorMessage)
        return emailResult.successful
    }

    private fun validatePassword(): Boolean {
        val passwordResult = signUpValidationsUseCase.validatePassword(signUpFormState.password)
        signUpFormState = signUpFormState.copy(passwordError = passwordResult.errorMessage)
        return passwordResult.successful
    }

    private fun validateConfirmPassword(): Boolean {
        val confirmPasswordResult = signUpValidationsUseCase.validateConfirmPassword(
            signUpFormState.password,
            signUpFormState.confirmPassword
        )
        return confirmPasswordResult.successful
    }

}