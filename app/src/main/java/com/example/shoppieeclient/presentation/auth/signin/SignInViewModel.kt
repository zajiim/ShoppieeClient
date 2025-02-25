package com.example.shoppieeclient.presentation.auth.signin

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
import com.example.shoppieeclient.domain.auth.use_cases.auth.siginin.SignInUseCase
import com.example.shoppieeclient.domain.auth.use_cases.auth.siginin.SignInWithFacebookUseCase
import com.example.shoppieeclient.domain.auth.use_cases.auth.siginin.SignInWithGoogleUseCase
import com.example.shoppieeclient.domain.auth.use_cases.validations.signin.SignInValidationsUseCases
import com.example.shoppieeclient.utils.Resource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.net.NoRouteToHostException

private const val TAG = "SignInViewModel"

class SignInViewModel(
    private val signInValidationsUseCase: SignInValidationsUseCases,
    private val signInUseCase: SignInUseCase,
    private val saveTokenUseCase: SaveTokenUseCase,
    private val saveUserDetailsUseCase: SaveUserDetailsUseCase,
    private val signInWithGoogleUseCase: SignInWithGoogleUseCase,
    private val signInWithFacebookUseCase: SignInWithFacebookUseCase,
    private val oAuthSignInUseCase: OAuthSignInUseCase
) : ViewModel() {

    var signInFormState by mutableStateOf(SignInState())
        private set

    fun onEvent(event: SignInEvents) {
        when (event) {
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

            is SignInEvents.SignInWithGoogle -> {
                signInWithGoogle(event.activityContext)
            }

            is SignInEvents.SignInWithFacebook -> {
                signInWithFacebook(event.activityContext)
            }
        }
    }

    private fun signInWithFacebook(activityContext: Context) = viewModelScope.launch {
        signInFormState = signInFormState.copy(isLoading = true)
        val result = signInWithFacebookUseCase(activityContext)
        handleSignInResult(result)
    }

    private fun signInWithGoogle(activityContext: Context) = viewModelScope.launch {
        signInFormState = signInFormState.copy(isLoading = true)
        val result = signInWithGoogleUseCase(activityContext)
        handleSignInResult(result)
    }

    private fun signInUser() = viewModelScope.launch(Dispatchers.IO) {
        signInUseCase(
            email = signInFormState.email, password = signInFormState.password
        ).collect { result ->
            withContext(Dispatchers.Main) {
                try {
                    signInFormState = signInFormState.copy(isLoading = true)
                    when (result) {
                        is Resource.Loading -> {
                            signInFormState = signInFormState.copy(isLoading = true)
                        }

                        is Resource.Success -> {
                            Log.e(TAG, "signInUser token: ${result.data?.token}")
                            result.data?.let { data ->
                                saveTokenUseCase(data.token)
                                saveUserDetailsUseCase(data.name, data.profileImage)
                            }
                            signInFormState = signInFormState.copy(
                                isLoading = false,
                                isSignInSuccessful = true,
                                alertDialog = result.message,
                                alertButtonString = "Go to Home"
                            )
                        }

                        is Resource.Error -> {
                            Log.e(TAG, "Resource exception $result")
                            signInFormState = signInFormState.copy(
                                isLoading = false,
                                isSignInSuccessful = false,
                                alertDialog = result.message,
                                alertButtonString = "Go back"
                            )
                        }
                    }
                } catch (e: Exception) {
                    Log.e(TAG, "common exception: $e")
                    signInFormState = signInFormState.copy(
                        isLoading = false,
                        isSignInSuccessful = false,
                        alertDialog = result.message,
                        alertButtonString = "Go back"
                    )
                } catch (e: NoRouteToHostException) {
                    Log.e(TAG, "errorr: $e")
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

                                            signInFormState = signInFormState.copy(
                                                isLoading = false,
                                                isSignInSuccessful = true,
                                                alertDialog = oauthResult.message,
                                                alertButtonString = "Go to Home"
                                            )
                                        }
                                    }

                                    is Resource.Error -> {
                                        signInFormState = signInFormState.copy(
                                            isLoading = false,
                                            isSignInSuccessful = false,
                                            alertDialog = oauthResult.message,
                                            alertButtonString = "Go back"
                                        )
                                    }

                                    is Resource.Loading -> {
                                        signInFormState = signInFormState.copy(isLoading = true)
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

                                            signInFormState = signInFormState.copy(
                                                isLoading = false,
                                                isSignInSuccessful = true,
                                                alertDialog = oauthResult.message,
                                                alertButtonString = "Go to Home"
                                            )
                                        }
                                    }

                                    is Resource.Error -> {
                                        signInFormState = signInFormState.copy(
                                            isLoading = false,
                                            isSignInSuccessful = false,
                                            alertDialog = oauthResult.message,
                                            alertButtonString = "Go back"
                                        )
                                    }

                                    is Resource.Loading -> {
                                        signInFormState = signInFormState.copy(isLoading = true)
                                    }
                                }
                            }
                        }
                    }
                }

                is Resource.Error -> {
                    signInFormState = signInFormState.copy(
                        isLoading = false,
                        isSignInSuccessful = false,
                        alertDialog = result.message,
                        alertButtonString = "Go back"
                    )
                }

                is Resource.Loading -> {
                    signInFormState = signInFormState.copy(isLoading = true)
                }
            }
        }
    }

}