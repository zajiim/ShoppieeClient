package com.example.shoppieeclient.presentation.auth.forget_password

import com.example.shoppieeclient.utils.UiText

data class ForgotPasswordStates(
    val email: String = "",
    val emailError: UiText? = null
)