package com.example.shoppieeclient.presentation.auth.signup

import com.example.shoppieeclient.utils.UiText

data class SignUpState(
    val userName: String = "",
    val userNameError: UiText? = null,
    val email: String = "",
    val emailError: UiText? = null,
    val password: String = "",
    val passwordError: UiText? = null,
    val confirmPassword: String = "",
    val confirmPasswordError: UiText? = null,
    val visiblePassword: Boolean = false,
    val visibleConfirmPassword: Boolean = false,
    val isLoading: Boolean = false,
    val alertDialog: String? = null,
    val alertButtonString: String? = null,
    val isSignUpSuccessful: Boolean = false,
    val isSocialSignInSuccessful: Boolean = false
)
