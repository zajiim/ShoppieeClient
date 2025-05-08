package com.example.shoppieeclient.presentation.home.payment

import com.example.shoppieeclient.domain.payment.models.PaymentCardModel


sealed class PaymentEvents {
    //Home Events
    data class ShowDeleteDialog(val paymentCardModel: PaymentCardModel): PaymentEvents()
    data object DismissDeleteDialog: PaymentEvents()
    data class DeleteCard(val paymentId: Int): PaymentEvents()

    //Details Event
    data class CardNameChanged(val name: String): PaymentEvents()
    data class CardNumberChanged(val cardNumber: String): PaymentEvents()
    data class CardExpiryChanged(val cardExpiry: String): PaymentEvents()
    data class CardCvvChanged(val cardCvv: String): PaymentEvents()
    data object OnCardClicked: PaymentEvents()
    data class LoadPaymentDetails(val paymentId: Int): PaymentEvents()


    data object ShowAddPaymentSheet: PaymentEvents()
    data object DismissAddPaymentSheet: PaymentEvents()
    data object SaveCard: PaymentEvents()

    data class ShowSelectionDialog(val paymentCardModel: PaymentCardModel): PaymentEvents()
    data object DismissSelectionDialog: PaymentEvents()
    data object ConfirmCardSelection: PaymentEvents()

}