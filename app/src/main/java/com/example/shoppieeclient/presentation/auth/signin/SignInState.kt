package com.example.shoppieeclient.presentation.auth.signin

import com.example.shoppieeclient.utils.UiText

data class SignInState(
    val email: String = "",
    val emailError: UiText? = null,
    val password: String = "",
    val passwordError: UiText? = null,
    val visiblePassword: Boolean = false,
    val isLoading: Boolean = false,
    val alertDialog: String? = null,
    val alertButtonString: String? = null,
    val isSignInSuccessful: Boolean = false
)
