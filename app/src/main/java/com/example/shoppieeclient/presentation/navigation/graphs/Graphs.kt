package com.example.shoppieeclient.presentation.navigation.graphs

import kotlinx.serialization.Serializable

@Serializable
sealed class Graphs() {
    @Serializable
    data object OnBoarding: Graphs()
    @Serializable
    data object Auth : Graphs()
    @Serializable
    data object Home : Graphs()
}