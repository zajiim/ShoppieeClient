package com.example.shoppieeclient.presentation.home.cart

data class CartStates(
    val isLoading: Boolean = false,
    val error: String? = null,
    val itemCount: Int = 0,
)
