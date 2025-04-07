package com.example.shoppieeclient.domain.checkout.repository

import android.app.Activity

interface RazorPayPaymentRepository {
    fun startPayment(amount: Double, activity: Activity, description: String)
    fun setPaymentResultCallback(callback: PaymentResultCallback)
}