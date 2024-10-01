package com.example.shoppieeclient.presentation.auth.signup

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.shoppieeclient.data.auth.remote.api.ShoppieeApi
import com.example.shoppieeclient.data.auth.remote.dto.signup.SignUpRequestDto
import com.example.shoppieeclient.domain.auth.use_cases.validations.signup.SignupValidationsUseCase
import kotlinx.coroutines.launch

private const val TAG = "SignUpViewModel"

class SignUpViewModel(
    private val signUpValidationsUseCase: SignupValidationsUseCase,
    private val shoppieeApi: ShoppieeApi
) : ViewModel() {

    var signUpFormState by mutableStateOf(SignUpState())

    fun onEvent(event: SignUpEvents) {
        when (event) {
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

            is SignUpEvents.VisibleConfirmPasswordChanged -> {
                signUpFormState =
                    signUpFormState.copy(visibleConfirmPassword = event.isVisiblePassword)
            }

            SignUpEvents.Submit -> {
                if (validateUserName() && validateEmail() && validatePassword() && validateConfirmPassword()) {
                    signUpUser()
                }
            }
        }
    }

    private fun signUpUser() = viewModelScope.launch{
        try {
            signUpFormState = signUpFormState.copy(isLoading = true)
            val signUpRequest = SignUpRequestDto(
                name = signUpFormState.userName,
                email = signUpFormState.email,
                password = signUpFormState.password,
                confirmPassword = signUpFormState.confirmPassword
            )
            val response = shoppieeApi.signUp(signUpRequest)
            Log.d(TAG, "Sign up successful: $response")

        } catch (e: Exception) {
            signUpFormState = signUpFormState.copy(signUpError = "Sign up failed: ${e.message}" )
            Log.e(TAG, "Error during sign up: $e")
        } finally {
            signUpFormState = signUpFormState.copy(isLoading = false)
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
        signUpFormState =
            signUpFormState.copy(confirmPasswordError = confirmPasswordResult.errorMessage)
        return confirmPasswordResult.successful
    }

}