package com.example.shoppieeclient.presentation.auth.signin

sealed class SignInEvents {
    data class EmailChanged(val email: String) : SignInEvents()
    data class PasswordChanged(val password: String) : SignInEvents()
    data class VisiblePasswordChanged(val isVisiblePassword: Boolean) : SignInEvents()
    data object Submit: SignInEvents()
    data object DismissDialog: SignInEvents()
}
