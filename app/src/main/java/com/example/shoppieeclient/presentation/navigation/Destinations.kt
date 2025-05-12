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

    //Profile
    @Serializable
    object Account: Destination()


    @Serializable
    object Payment: Destination()

    @Serializable
    object Delete: Destination()

    @Serializable
    object CheckOut: Destination()

    @Serializable
    object Address: Destination()

    @Serializable
    object Order: Destination()

}