package com.example.shoppieeclient.presentation.auth.signin

import com.example.shoppieeclient.utils.UiText

data class SignInState(
    val email: String = "",
    val emailError: UiText? = null,
    val password: String = "",
    val passwordError: UiText? = null,
    val visiblePassword: Boolean = false
)
