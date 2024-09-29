package com.example.shoppieeclient.presentation.navigation

import kotlinx.serialization.Serializable

@Serializable
sealed class Destination {
    @Serializable
    data object Onboarding : Destination()

    @Serializable
    data object SignIn : Destination()

    @Serializable
    data object SignUp : Destination()

    @Serializable
    data object Forgot : Destination()

    @Serializable
    data object Home : Destination()
}


