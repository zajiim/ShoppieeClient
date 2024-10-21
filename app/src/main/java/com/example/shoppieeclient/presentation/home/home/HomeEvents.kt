package com.example.shoppieeclient.presentation.home.home

sealed class HomeEvents {
    data class OnQueryChange(val query: String): HomeEvents()
    data class OnChipSelected(val chip: String): HomeEvents()
}