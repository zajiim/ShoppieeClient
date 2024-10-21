package com.example.shoppieeclient.presentation.home.home

data class HomeStates(
    val query: String = "",
    val selectedChip: String = "",
    val isMenuOpen: Boolean = false,
    val storeLocation: String = "New Delhi",
    val brandsList: List<Pair<String, Int>> = emptyList()
)