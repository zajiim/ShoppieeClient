package com.example.shoppieeclient.presentation.home.checkout

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.shoppieeclient.domain.payment.use_cases.GetSelectedCardUseCase
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

private const val TAG = "CheckOutViewModel"
class CheckOutViewModel(
    private val getSelectedCardUseCase: GetSelectedCardUseCase
): ViewModel() {
    var checkOutState by mutableStateOf(CheckOutStates())
        private set

    init {
        getSelectedCard()
    }

    private fun getSelectedCard() = viewModelScope.launch {
        getSelectedCardUseCase().collectLatest { selectedCard ->
            checkOutState = checkOutState.copy(
                selectedCard = selectedCard
            )
        }
        Log.e(TAG, "getSelectedCard: ${checkOutState.selectedCard}", )
    }


}