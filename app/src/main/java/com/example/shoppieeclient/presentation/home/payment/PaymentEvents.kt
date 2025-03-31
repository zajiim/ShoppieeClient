package com.example.shoppieeclient.presentation.home.payment

import com.example.shoppieeclient.domain.payment.models.PaymentCardModel


sealed class PaymentEvents {
    data object AddCardClicked: PaymentEvents()
    data object DismissBottomSheet: PaymentEvents()
    data object OnCardClicked: PaymentEvents()
    data class CardHolderNameChanged(val name: String): PaymentEvents()
    data class CardNumberChanged(val number: String): PaymentEvents()
    data class CardExpiryChanged(val expiry: String): PaymentEvents()
    data class CardCvvChanged(val cvv: String): PaymentEvents()
    data class AddPaymentCard(val paymentDetails: PaymentCardModel): PaymentEvents()
}