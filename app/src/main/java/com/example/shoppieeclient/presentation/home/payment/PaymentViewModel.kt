package com.example.shoppieeclient.presentation.home.payment

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.shoppieeclient.domain.payment.models.CardTypes
import com.example.shoppieeclient.domain.payment.models.PaymentCardModel
import com.example.shoppieeclient.domain.payment.use_cases.DeleteCardByIdUseCase
import com.example.shoppieeclient.domain.payment.use_cases.GetAllCardsUseCase
import com.example.shoppieeclient.domain.payment.use_cases.GetCardByIdUseCase
import com.example.shoppieeclient.domain.payment.use_cases.SetSelectedCardUseCase
import com.example.shoppieeclient.domain.payment.use_cases.UpsertCardUseCase
import com.example.shoppieeclient.utils.startsWithAny
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

private const val TAG = "PaymentViewModel"


class PaymentViewModel(
    private val getAllCardsUseCase: GetAllCardsUseCase,
    private val deleteCardByIdUseCase: DeleteCardByIdUseCase,
    private val upsertCardUseCase: UpsertCardUseCase,
    private val getCardDetailsByIdUseCase: GetCardByIdUseCase,
    private val setSelectedCardUseCase: SetSelectedCardUseCase
) : ViewModel() {
    var paymentState by mutableStateOf(PaymentStates())
        private set

    init {
        getAllCards()
    }

    private fun getAllCards() = viewModelScope.launch {
        getAllCardsUseCase().collectLatest { cards ->
            paymentState = paymentState.copy(
                cards = cards.filterNotNull(),
                isLoading = false
            )
        }
    }


    fun onEvent(events: PaymentEvents) {
        when (events) {
            is PaymentEvents.CardNumberChanged -> {
                val cardNumber = events.cardNumber.filter { it.isDigit() }.take(16)
                val cardType = when {
                    cardNumber.startsWithAny("5", "2") -> CardTypes.MASTERCARD
                    cardNumber.startsWithAny("4") -> CardTypes.VISA
                    cardNumber.startsWithAny("6", "8") -> CardTypes.RUPAY
                    else -> CardTypes.NONE
                }
                val maskedNumber = if (cardNumber.isEmpty()) {
                    "****************"
                } else {
                    "****************".replaceRange(0, cardNumber.length, cardNumber)
                }

                paymentState = paymentState.copy(
                    cardNumberText = cardNumber,
                    cardType = cardType,
                    maskedCardNumber = maskedNumber
                )
            }

            is PaymentEvents.CardNameChanged -> {
                paymentState = paymentState.copy(
                    nameText = events.name
                )
            }

            is PaymentEvents.CardCvvChanged -> {
                val backVisibleState = events.cardCvv.length in 1..2
                paymentState = paymentState.copy(
                    cvvText = events.cardCvv,
                    isBackVisibleState = backVisibleState
                )
            }

            is PaymentEvents.CardExpiryChanged -> {
                val expiry = events.cardExpiry.filter { it.isDigit() }.take(4)
                paymentState = paymentState.copy(
                    expiryText = expiry
                )

            }

            PaymentEvents.ConfirmCardSelection -> {
                viewModelScope.launch {
                    paymentState.cardToSelect?.let { card ->
                        setSelectedCardUseCase(card)
                        paymentState = paymentState.copy(
                            showSelectionDialog = false,
                            cardToSelect = null,
                            selectedCard = card
                        )
                        getAllCards()
                    }
                }
            }

            is PaymentEvents.DeleteCard -> {
                viewModelScope.launch {
                    events.paymentId.let { cardId ->
                        try {
                            deleteCardByIdUseCase(cardId)
                            getAllCards()
                            paymentState = paymentState.copy(
                                showDeleteDialog = false,
                                cards = paymentState.cards.filter { it.id != cardId }
                            )
                        } catch (e: Exception) {
                            Log.e(TAG, "onEvent: error =>  ${e.message}")
                            paymentState = paymentState.copy(
                                isError = true,
                                showDeleteDialog = false
                            )
                        }
                    }
                }
            }

            PaymentEvents.DismissAddPaymentSheet -> {
                paymentState = paymentState.copy(
                    showBottomSheet = false
                )
            }

            PaymentEvents.DismissDeleteDialog -> {
                paymentState = paymentState.copy(showDeleteDialog = false, cardToDeleteId = null)
            }

            PaymentEvents.DismissSelectionDialog -> {
                paymentState = paymentState.copy(
                    showSelectionDialog = false,
                    cardToSelect = null
                )
            }

            is PaymentEvents.LoadPaymentDetails -> {
                viewModelScope.launch {
                    getCardDetailsByIdUseCase(events.paymentId)?.let { paymentModel ->
                        val cardNumber = paymentModel.cardNumber.filter { it.isDigit() }.take(16)
                        val cardType = when {
                            cardNumber.startsWithAny("5", "2") -> CardTypes.MASTERCARD
                            cardNumber.startsWithAny("4") -> CardTypes.VISA
                            cardNumber.startsWithAny("6", "8") -> CardTypes.RUPAY
                            else -> CardTypes.NONE
                        }
                        val maskedNumber = if (cardNumber.isEmpty()) {
                            "****************"
                        } else {
                            "****************".replaceRange(0, cardNumber.length, cardNumber)
                        }
                        paymentState = paymentState.copy(
                            nameText = paymentModel.cardHolderName,
                            cardNumberText = cardNumber,
                            expiryText = paymentModel.expirationDate,
                            cvvText = paymentModel.cvv,
                            isEditing = true,
                            currentPaymentId = paymentModel.id,
                            cardType = cardType,
                            maskedCardNumber = maskedNumber,
                            showDeleteDialog = false,
                            showBottomSheet = true,
                            selectedCard = paymentModel
                        )
                    }
                }
            }

            PaymentEvents.OnCardClicked -> {
                paymentState = paymentState.copy(
                    isBackVisibleState = !paymentState.isBackVisibleState
                )
            }

            PaymentEvents.SaveCard -> {
                val paymentDetails = PaymentCardModel(
                    cardNumber = paymentState.cardNumberText,
                    cardHolderName = paymentState.nameText,
                    expirationDate = paymentState.expiryText,
                    cvv = paymentState.cvvText,
                    isSelected = paymentState.selectedCard?.isSelected ?: false
                )

                viewModelScope.launch {
                    if (paymentState.isEditing) {
                        upsertCardUseCase(
                            paymentCardModel = paymentDetails.copy(id = paymentState.currentPaymentId)
                        )
                    } else {
                        upsertCardUseCase(paymentCardModel = paymentDetails)
                    }
                    paymentState = paymentState.copy(
                        showBottomSheet = false
                    )
                }
            }

            PaymentEvents.ShowAddPaymentSheet -> {
                paymentState = paymentState.copy(
                    showBottomSheet = true,
                    isEditing = false,
                    nameText = "",
                    cardNumberText = "",
                    expiryText = "",
                    cvvText = "",
                    currentPaymentId = 0,
                    cardType = CardTypes.NONE,
                    maskedCardNumber = "****************"
                )
            }

            is PaymentEvents.ShowDeleteDialog -> {
                paymentState = paymentState.copy(
                    showDeleteDialog = true,
                    cardToDeleteId = events.paymentCardModel.id
                )
            }

            is PaymentEvents.ShowSelectionDialog -> {
                paymentState = paymentState.copy(
                    showSelectionDialog = true,
                    cardToSelect = events.paymentCardModel
                )
            }
        }
    }

}