package com.example.shoppieeclient.presentation.home.cart

sealed class CartEvents {
    data class RemoveCartItem(val id: String): CartEvents()
    data class IncrementItem(val id: String, val size: String): CartEvents()
    data class DecrementItem(val id: String): CartEvents()
    data object Checkout: CartEvents()
}
