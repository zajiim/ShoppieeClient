package com.example.shoppieeclient.presentation.home.payment

import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import com.example.shoppieeclient.domain.payment.CardTypes
import com.example.shoppieeclient.domain.payment.PaymentCardModel
import com.example.shoppieeclient.utils.startsWithAny

private const val TAG = "PaymentViewModel"


class PaymentViewModel: ViewModel() {
    var paymentState by mutableStateOf(PaymentStates())
        private set


    fun onEvent(event: PaymentEvents) {
        when(event) {
            PaymentEvents.AddCardClicked -> {
                paymentState = paymentState.copy(
                    isAddCardClicked = true,
                    selectedPayment = PaymentCardModel(
                        id = "",
                        cardHolderName = "",
                        cardNumber = "",
                        expirationDate = "",
                        cvv = ""
                    )
                )
            }

            PaymentEvents.DismissBottomSheet -> {
                paymentState = paymentState.copy(
                    isAddCardClicked = false
                )
            }

            is PaymentEvents.CardCvvChanged -> {
                val isBackVisibleState = event.cvv.length in 1..2
                paymentState = paymentState.copy(
                    selectedPayment = paymentState.selectedPayment.copy(
                        cvv = event.cvv.take(3)
                    ),
                    backVisibleState = isBackVisibleState
                )
//                paymentState = paymentState.copy(
//                    cardCvvNumber = event.cvv.take(3),
//                    backVisibleState = isBackVisibleState
//                )

            }
            is PaymentEvents.CardExpiryChanged -> {
                val expiryNumber = event.expiry.filter { it.isDigit() }.take(4)
                paymentState = paymentState.copy(
                    selectedPayment = paymentState.selectedPayment.copy(expirationDate = expiryNumber)
                )
                Log.e(TAG, "onEvent: expiry date == >> ${paymentState.cardExpiryDate}", )
                Log.e(TAG, "onEvent: expiry selected == >> ${paymentState.selectedPayment.expirationDate}", )


            }
            is PaymentEvents.CardHolderNameChanged -> {
                paymentState = paymentState.copy(
                    selectedPayment = paymentState.selectedPayment.copy(cardHolderName = event.name),
                    cardHolderName = event.name
                )

            }
            is PaymentEvents.CardNumberChanged -> {
                val cardNumber = event.number.filter { it.isDigit() }.take(16)
                val cardType = when {
                    cardNumber.startsWith("4") -> CardTypes.VISA
                    cardNumber.startsWithAny("508", "60", "65", "81", "82") -> CardTypes.RUPAY
                    cardNumber.startsWithAny("51", "52", "53", "54", "55") -> CardTypes.MASTERCARD
                    else -> CardTypes.NONE
                }

                val maskedNumber = if (cardNumber.isEmpty()) {
                    "****************"
                } else {
                    "****************".replaceRange(0, cardNumber.length, cardNumber)
                }

                paymentState = paymentState.copy(
                    selectedPayment = paymentState.selectedPayment.copy(
                        cardNumber = cardNumber
                    ),
                    maskedCardNumber = maskedNumber,
                    cardNumber = maskedNumber,
                    cardType = cardType
                )
                Log.e(TAG, "masked number ====> $maskedNumber or ${paymentState.maskedCardNumber}", )
            }

            PaymentEvents.OnCardClicked -> {
                Log.e(TAG, "card clicked: ${paymentState.backVisibleState}", )
                paymentState = paymentState.copy(backVisibleState = !paymentState.backVisibleState)
            }
        }
    }
}