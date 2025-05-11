package com.example.shoppieeclient.presentation.home.checkout

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import com.example.shoppieeclient.domain.checkout.repository.PaymentResultCallback
import com.example.shoppieeclient.domain.checkout.use_cases.StartRPPaymentUseCase

/*
class PaymentHandler(
    startRPPaymentUseCase: StartRPPaymentUseCase
): PaymentResultCallback {

    var paymentState by mutableStateOf(CheckOutStates())
        private set

    init {
        startRPPaymentUseCase.setPaymentResultCallback(this)
    }

    override fun onPaymentSuccess(paymentId: String) {
        paymentState = paymentState.copy(
            isLoading = false,
            paymentStatus = PaymentStatus.SUCCESS,
            paymentId = paymentId,
            error = null
        )
    }

    override fun onPaymentError(errorCode: Int, errorDescription: String) {
        paymentState = paymentState.copy(
            isLoading = false,
            paymentStatus = PaymentStatus.FAILED,
            error = errorDescription
        )
    }

    override fun onPaymentCancelled() {
        paymentState = paymentState.copy(
            isLoading = false,
            paymentStatus = PaymentStatus.CANCELLED,
            error = "Payment was cancelled"
        )
    }

    fun resetPaymentState() {
        paymentState = paymentState.copy(
            isLoading = false,
            paymentStatus = PaymentStatus.IDLE,
            error = null
        )
    }
}*/
