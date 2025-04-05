package com.example.shoppieeclient.presentation.home.payment

import com.example.shoppieeclient.domain.payment.models.CardTypes
import com.example.shoppieeclient.domain.payment.models.PaymentCardModel

data class PaymentStates(
    //Home-related states
    val isError: Boolean = false,
    val cards: List<PaymentCardModel> = emptyList(),
    val isLoading: Boolean = false,
    val showDeleteDialog: Boolean = false,
    val cardToDeleteId: Int? = null,

    //Detail-related states
    val nameText: String = "",
    val cardNumberText: String = "",
    val expiryText: String = "",
    val cvvText: String = "",
    val isBackVisibleState: Boolean = false,
    val cardType: CardTypes = CardTypes.NONE,
    val maskedCardNumber: String = "***************",
    val isEditing: Boolean = false,
    val currentPaymentId: Int = 0,

    //BottomSheet State
    val showBottomSheet: Boolean = false,

    //Card Selection State
    val showSelectionDialog: Boolean = false,
    val cardToSelect: PaymentCardModel? = null,
    val selectedCard: PaymentCardModel? = null,
)