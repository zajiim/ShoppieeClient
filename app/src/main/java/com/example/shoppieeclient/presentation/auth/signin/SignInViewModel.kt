package com.example.shoppieeclient.presentation.auth.signin

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.shoppieeclient.domain.auth.use_cases.auth.siginin.SignInUseCase
import com.example.shoppieeclient.domain.auth.use_cases.validations.signin.SignInValidationsUseCases
import com.example.shoppieeclient.domain.auth.use_cases.validations.signup.SignupValidationsUseCase
import com.example.shoppieeclient.utils.Resource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

private const val TAG = "SignInViewModel"
class SignInViewModel(
    private val signInValidationsUseCase: SignInValidationsUseCases,
    private val signInUseCase: SignInUseCase
): ViewModel() {

    var signInFormState by mutableStateOf(SignInState())
        private set

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
                    signInUser()
                }
            }

            SignInEvents.DismissDialog -> {
                signInFormState = signInFormState.copy(alertDialog = null)
            }
        }
    }

    private fun signInUser() = viewModelScope.launch(Dispatchers.IO) {
        signInUseCase(
            email = signInFormState.email,
            password = signInFormState.password
        ).collect { result ->
            withContext(Dispatchers.Main) {
                try {
                    signInFormState = signInFormState.copy(isLoading = true)
                    when(result) {
                        is Resource.Loading -> {
                            signInFormState = signInFormState.copy(isLoading = true)
                        }
                        is Resource.Success -> {
                            signInFormState = signInFormState.copy(
                                isLoading = false,
                                isSignInSuccessful = true,
                                alertDialog = result.message,
                                alertButtonString = "Go to Home"
                            )
                        }
                        is Resource.Error -> {
                            signInFormState = signInFormState.copy(
                                isLoading = false,
                                isSignInSuccessful = false,
                                alertDialog = result.message,
                                alertButtonString = "Go back"
                            )
                        }
                    }
                } catch (e: Exception) {
                    signInFormState = signInFormState.copy(
                        isLoading = false,
                        isSignInSuccessful = false,
                        alertDialog = result.message,
                        alertButtonString = "Go back"
                    )
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