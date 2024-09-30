package com.example.shoppieeclient.presentation.auth.signup

sealed class SignUpEvents {
    data class UsernameChanged(val username: String) : SignUpEvents()
    data class EmailChanged(val email: String) : SignUpEvents()
    data class PasswordChanged(val password: String) : SignUpEvents()
    data class ConfirmPasswordChanged(val confirmPassword: String) : SignUpEvents()
    data class VisiblePasswordChanged(val isVisiblePassword: Boolean) : SignUpEvents()
    data class VisibleConfirmPasswordChanged(val isVisiblePassword: Boolean) : SignUpEvents()
    data object Submit : SignUpEvents()
}
