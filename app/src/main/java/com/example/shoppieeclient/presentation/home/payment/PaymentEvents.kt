package com.example.shoppieeclient.presentation.home.payment


sealed class PaymentEvents {
    data object AddCardClicked: PaymentEvents()
    data object DismissBottomSheet: PaymentEvents()
    data class CardHolderNameChanged(val name: String): PaymentEvents()
    data class CardNumberChanged(val number: String): PaymentEvents()
    data class CardExpiryChanged(val expiry: String): PaymentEvents()
    data class CardCvvChanged(val cvv: String): PaymentEvents()
}