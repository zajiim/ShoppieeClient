package com.example.shoppieeclient.presentation.navigation

import kotlinx.serialization.Serializable

//@Serializable
//data object Onboarding
//@Serializable
//data object SignIn
//@Serializable
//data object SignUp
//@Serializable
//data object Home

@Serializable
sealed class Destination {
    @Serializable
    data object Onboarding : Destination()

    @Serializable
    data object SignIn : Destination()

    @Serializable
    data object SignUp : Destination()

    @Serializable
    data object Home : Destination()
}


