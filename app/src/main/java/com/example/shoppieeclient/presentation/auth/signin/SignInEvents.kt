package com.example.shoppieeclient.presentation.auth.signin

import android.content.Context

sealed class SignInEvents {
    data class EmailChanged(val email: String) : SignInEvents()
    data class PasswordChanged(val password: String) : SignInEvents()
    data class VisiblePasswordChanged(val isVisiblePassword: Boolean) : SignInEvents()
    data object Submit: SignInEvents()
    data class SignInWithGoogle(val activityContext: Context): SignInEvents()
    data class SignInWithFacebook(val activityContext: Context): SignInEvents()
    data object DismissDialog: SignInEvents()
}
