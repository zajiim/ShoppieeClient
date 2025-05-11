package com.example.shoppieeclient.presentation.home.checkout

import android.app.Activity

sealed class CheckoutEvents {

    data class CreateOrder(
        val amount: Double, val currency: String,
        val addressId: String,
        val activity: Activity?
    ) : CheckoutEvents()

    data class PaymentSuccess(
        val paymentId: String,
        val signature: String,
    ) : CheckoutEvents()

    data class PaymentError(
        val message: String
    ) : CheckoutEvents()

//    data object VerifyPayment: CheckoutEvents()

}