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
import com.example.shoppieeclient.domain.payment.use_cases.UpsertCardUseCase
import com.example.shoppieeclient.utils.startsWithAny
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

private const val TAG = "PaymentViewModel"


class PaymentViewModel(
    private val getAllCardsUseCase: GetAllCardsUseCase,
    private val deleteCardByIdUseCase: DeleteCardByIdUseCase,
    private val upsertCardUseCase: UpsertCardUseCase,
    private val getCardDetailsByIdUseCase: GetCardByIdUseCase
) : ViewModel() {
    var paymentState by mutableStateOf(PaymentStates())
        private set

    init {
        getAllCards()
    }

    private fun getAllCards() = viewModelScope.launch {
        getAllCardsUseCase().collectLatest{ cards ->
            paymentState = paymentState.copy(
                cards = cards.filterNotNull(),
                isLoading = false
            )
        }
    }


    fun onEvent(events: PaymentEvents) {
        when(events) {
            is PaymentEvents.CardCvvChanged -> TODO()
            is PaymentEvents.CardExpiryChanged -> TODO()
            is PaymentEvents.CardNameChanged -> TODO()
            is PaymentEvents.CardNumberChanged -> TODO()
            PaymentEvents.ConfirmCardSelection -> TODO()
            is PaymentEvents.DeleteCard -> TODO()
            PaymentEvents.DismissAddPaymentSheet -> TODO()
            PaymentEvents.DismissDeleteDialog -> TODO()
            PaymentEvents.DismissSelectionDialog -> TODO()
            is PaymentEvents.LoadPaymentDetails -> TODO()
            PaymentEvents.OnCardClicked -> TODO()
            PaymentEvents.SaveCard -> TODO()
            PaymentEvents.ShowAddPaymentSheet -> TODO()
            is PaymentEvents.ShowDeleteDialog -> TODO()
            is PaymentEvents.ShowSelectionDialog -> TODO()
        }
    }

}