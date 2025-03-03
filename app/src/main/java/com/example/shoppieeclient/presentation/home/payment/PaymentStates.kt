package com.example.shoppieeclient.presentation.home.payment

import com.example.shoppieeclient.domain.payment.CardTypes
import com.example.shoppieeclient.domain.payment.PaymentCardModel

data class PaymentStates(
    val isAddCardClicked: Boolean = false,
    val paymentCards: List<PaymentCardModel>?=null,
    val isLoading: Boolean = false,
    val error: String? = null,
    val selectedPayment: PaymentCardModel = PaymentCardModel(
        id = "",
        cardHolderName = "",
        cardNumber = "",
        expirationDate = "",
        cvv = ""
    ),
    val backVisibleState: Boolean = false,
    val cardHolderName: String = "",
    val cardNumber: String = "",
    val maskedCardNumber: String = "*****************",
    val cardExpiryDate: String = "",
    val cardCvvNumber: String = "",
    val cardType: CardTypes = CardTypes.NONE
)