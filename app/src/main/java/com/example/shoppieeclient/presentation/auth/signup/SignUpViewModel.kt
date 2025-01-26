package com.example.shoppieeclient.presentation.auth.signup

import android.content.Context
import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.shoppieeclient.domain.auth.models.auth.FacebookAccount
import com.example.shoppieeclient.domain.auth.models.auth.GoogleAccount
import com.example.shoppieeclient.domain.auth.use_cases.auth.siginin.OAuthSignInUseCase
import com.example.shoppieeclient.domain.auth.use_cases.auth.siginin.SaveTokenUseCase
import com.example.shoppieeclient.domain.auth.use_cases.auth.siginin.SaveUserDetailsUseCase
import com.example.shoppieeclient.domain.auth.use_cases.auth.siginin.SignInWithFacebookUseCase
import com.example.shoppieeclient.domain.auth.use_cases.auth.siginin.SignInWithGoogleUseCase
import com.example.shoppieeclient.domain.auth.use_cases.auth.signup.SignUpUseCase
import com.example.shoppieeclient.domain.auth.use_cases.validations.signup.SignupValidationsUseCase
import com.example.shoppieeclient.utils.Resource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

private const val TAG = "SignUpViewModel"

class SignUpViewModel(
    private val signUpValidationsUseCase: SignupValidationsUseCase,
    private val signUpUseCase: SignUpUseCase,
    private val signInWithGoogleUseCase: SignInWithGoogleUseCase,
    private val signInWithFacebookUseCase: SignInWithFacebookUseCase,
    private val oAuthSignInUseCase: OAuthSignInUseCase,
    private val saveTokenUseCase: SaveTokenUseCase,
    private val saveUserDetailsUseCase: SaveUserDetailsUseCase,
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

            is SignUpEvents.SignInWithFacebook -> {
                signInWithFacebook(event.activityContext)
            }
            is SignUpEvents.SignInWithGoogle -> {
                signInWithGoogle(event.activityContext)
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
                            )
                        }

                        is Resource.Error -> {
                            Log.d(TAG, "Error: ${result.message}")
                            signUpFormState = signUpFormState.copy(
                                isLoading = false,
                                isSignUpSuccessful = false,
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

    private fun signInWithFacebook(activityContext: Context) = viewModelScope.launch {
        signUpFormState = signUpFormState.copy(isLoading = true)
        val result = signInWithFacebookUseCase(activityContext)
        handleSignInResult(result)
    }

    private fun signInWithGoogle(activityContext: Context) = viewModelScope.launch {
        signUpFormState = signUpFormState.copy(isLoading = true)
        val result = signInWithGoogleUseCase(activityContext)
        handleSignInResult(result)
    }

    private suspend fun <T> handleSignInResult(result: Resource<T>) {
        withContext(Dispatchers.Main) {
            when (result) {
                is Resource.Success -> {
                    result.data?.let { data ->
                        if (data is GoogleAccount) {
                            oAuthSignInUseCase(
                                provider = "google", token = data.token
                            ).collect { oauthResult ->
                                when (oauthResult) {
                                    is Resource.Success -> {
                                        oauthResult.data?.let { userData ->
                                            saveTokenUseCase(userData.token)
                                            saveUserDetailsUseCase(
                                                userData.name, userData.profileImage
                                            )

                                            signUpFormState = signUpFormState.copy(
                                                isLoading = false,
                                                isSocialSignInSuccessful = true,
                                                alertDialog = oauthResult.message,
                                                alertButtonString = "Go to Home"
                                            )
                                        }
                                    }

                                    is Resource.Error -> {
                                        signUpFormState = signUpFormState.copy(
                                            isLoading = false,
                                            isSocialSignInSuccessful = false,
                                            alertDialog = oauthResult.message,
                                            alertButtonString = "Go back"
                                        )
                                    }

                                    is Resource.Loading -> {
                                        signUpFormState = signUpFormState.copy(isLoading = true)
                                    }
                                }
                            }
                        }


                        if (data is FacebookAccount) {
                            oAuthSignInUseCase(
                                provider = "facebook", token = data.token
                            ).collect { oauthResult ->
                                when (oauthResult) {
                                    is Resource.Success -> {
                                        oauthResult.data?.let { userData ->
                                            saveTokenUseCase(userData.token)
                                            saveUserDetailsUseCase(
                                                userData.name, userData.profileImage
                                            )

                                            signUpFormState = signUpFormState.copy(
                                                isLoading = false,
                                                isSocialSignInSuccessful = true,
                                                alertDialog = oauthResult.message,
                                                alertButtonString = "Go to Home"
                                            )
                                        }
                                    }

                                    is Resource.Error -> {
                                        signUpFormState = signUpFormState.copy(
                                            isLoading = false,
                                            isSocialSignInSuccessful = false,
                                            alertDialog = oauthResult.message,
                                            alertButtonString = "Go back"
                                        )
                                    }

                                    is Resource.Loading -> {
                                        signUpFormState = signUpFormState.copy(isLoading = true)
                                    }
                                }
                            }
                        }
                    }
                }

                is Resource.Error -> {
                    signUpFormState = signUpFormState.copy(
                        isLoading = false,
                        isSocialSignInSuccessful = false,
                        alertDialog = result.message,
                        alertButtonString = "Go back"
                    )
                }

                is Resource.Loading -> {
                    signUpFormState = signUpFormState.copy(isLoading = true)
                }
            }
        }
    }

}