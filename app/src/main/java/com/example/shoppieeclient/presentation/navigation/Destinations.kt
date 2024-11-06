package com.example.shoppieeclient.presentation.navigation

import kotlinx.serialization.Serializable


@Serializable
sealed class Destination() {
    // Auth
    @Serializable
    object SignIn : Destination()
    @Serializable
    object SignUp : Destination()
    @Serializable
    object Forgot : Destination()

    // Main
    @Serializable
    object Home : Destination()
    @Serializable
    object Notifications : Destination()
    @Serializable
    object Cart : Destination()
    @Serializable
    object Favorites : Destination()
    @Serializable
    object Profile : Destination()

    @Serializable
    data class Details(val id: String) : Destination()
}