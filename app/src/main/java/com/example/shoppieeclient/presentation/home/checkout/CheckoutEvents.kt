package com.example.shoppieeclient.presentation.home.checkout

import android.app.Activity

sealed class CheckoutEvents {
    data class InitiatePayment(val amount: Double, val activity: Activity, val description: String): CheckoutEvents()
    data class PaymentSuccess(val paymentId: String): CheckoutEvents()
    data class PaymentError(val errorCode: Int, val errorDescription: String): CheckoutEvents()
    object PaymentCancelled: CheckoutEvents()
}