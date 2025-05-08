package com.example.shoppieeclient.domain.checkout.use_cases

import android.app.Activity
import com.example.shoppieeclient.domain.checkout.repository.PaymentResultCallback
import com.example.shoppieeclient.domain.checkout.repository.RazorPayPaymentRepository

class StartRPPaymentUseCase(
    private val paymentRepository: RazorPayPaymentRepository
) {
    operator fun invoke(amount: Double, activity: Activity, description: String) {
        paymentRepository.startPayment(amount, activity, description)
    }

    fun setPaymentResultCallback(callback: PaymentResultCallback) {
        paymentRepository.setPaymentResultCallback(callback)
    }
}