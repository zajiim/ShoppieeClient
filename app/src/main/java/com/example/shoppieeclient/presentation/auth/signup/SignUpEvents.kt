package com.example.shoppieeclient.presentation.auth.signup

import android.content.Context
import com.example.shoppieeclient.presentation.auth.signin.SignInEvents

sealed class SignUpEvents {
    data class UsernameChanged(val username: String) : SignUpEvents()
    data class EmailChanged(val email: String) : SignUpEvents()
    data class PasswordChanged(val password: String) : SignUpEvents()
    data class ConfirmPasswordChanged(val confirmPassword: String) : SignUpEvents()
    data class VisiblePasswordChanged(val isVisiblePassword: Boolean) : SignUpEvents()
    data class VisibleConfirmPasswordChanged(val isVisiblePassword: Boolean) : SignUpEvents()
    data object Submit : SignUpEvents()
    data object DismissDialog : SignUpEvents()
    data class SignInWithGoogle(val activityContext: Context): SignUpEvents()
    data class SignInWithFacebook(val activityContext: Context): SignUpEvents()
}
