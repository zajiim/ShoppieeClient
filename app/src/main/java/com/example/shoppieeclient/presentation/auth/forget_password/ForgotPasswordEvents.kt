package com.example.shoppieeclient.presentation.auth.forget_password

sealed class ForgotPasswordEvents {
    data class EmailChanged(val email: String) : ForgotPasswordEvents()
    data object Submit : ForgotPasswordEvents()
}
