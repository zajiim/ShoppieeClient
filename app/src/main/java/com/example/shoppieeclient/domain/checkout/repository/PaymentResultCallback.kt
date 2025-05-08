package com.example.shoppieeclient.domain.checkout.repository

interface PaymentResultCallback {
    fun onPaymentSuccess(paymentId: String)
    fun onPaymentError(errorCode: Int, errorDescription: String)
    fun onPaymentCancelled()
}