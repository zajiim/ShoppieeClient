package com.example.shoppieeclient.presentation.auth.signup

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.shoppieeclient.domain.auth.use_cases.auth.signup.SignUpUseCase
import com.example.shoppieeclient.domain.auth.use_cases.validations.signup.SignupValidationsUseCase
import com.example.shoppieeclient.utils.Resource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

private const val TAG = "SignUpViewModel"

class SignUpViewModel(
    private val signUpValidationsUseCase: SignupValidationsUseCase,
    private val signUpUseCase: SignUpUseCase
) : ViewModel() {

    var signUpFormState by mutableStateOf(SignUpState())
        private set

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
            SignUpEvents.DismissDialog -> {
                signUpFormState = signUpFormState.copy(alertDialog = null)
            }
        }
    }

    private fun signUpUser() = viewModelScope.launch(Dispatchers.IO) {
        signUpUseCase(
            name = signUpFormState.userName,
            email = signUpFormState.email,
            password = signUpFormState.password,
            confirmPassword = signUpFormState.confirmPassword
        ).collect { result ->
            withContext(Dispatchers.Main) {
                try {
                    signUpFormState = signUpFormState.copy(isLoading = true)
                    when (result) {
                        is Resource.Success -> {
                            Log.d(TAG, "Success: ${result.message}")
                            signUpFormState = signUpFormState.copy(
                                isLoading = false,
                                isSignUpSuccessful = true,
                                alertDialog = result.message,
                                alertButtonString = "Go to Login"
//                                signUpError = null
                            )
                        }

                        is Resource.Error -> {
                            Log.d(TAG, "Error: ${result.message}")
                            signUpFormState = signUpFormState.copy(
                                isLoading = false,
                                isSignUpSuccessful = false,
//                                signUpError = result.message ?: "Unknown error"
                                alertDialog = result.message,
                                alertButtonString = "Go back"
                            )
                        }

                        is Resource.Loading -> {
                            Log.d(TAG, "Loading: ${result.message}")
                            signUpFormState = signUpFormState.copy(
                                isLoading = true
                            )
                        }
                    }
                } catch (e: Exception) {
                    Log.d(TAG, "Exception: ${result.message}, excep ==> ${e.message}")
                    signUpFormState = signUpFormState.copy(
                        isLoading = false,
                        isSignUpSuccessful = false,
//                        signUpError = "Sign up failed: ${e.message}"
                        alertDialog = result.message,
                        alertButtonString = "Go back"
                    )
                }
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
            signUpFormState.password, signUpFormState.confirmPassword
        )
        signUpFormState =
            signUpFormState.copy(confirmPasswordError = confirmPasswordResult.errorMessage)
        return confirmPasswordResult.successful
    }

}