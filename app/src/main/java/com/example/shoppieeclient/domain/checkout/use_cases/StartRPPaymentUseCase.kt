package com.example.shoppieeclient.domain.checkout.use_cases

import android.app.Activity
import com.example.shoppieeclient.domain.checkout.repository.PaymentResultCallback
import com.example.shoppieeclient.domain.checkout.repository.RazorPayPaymentRepository

class StartRPPaymentUseCase(
    private val paymentRepository: RazorPayPaymentRepository
) {
    operator fun invoke(
        amount: Double,
        activity: Activity,
        currency: String,
        razorPayOrderId: String,
        keyId: String,
        description: String = "Payment for Order"
    ) {
        paymentRepository.startPayment(
            amount = amount,
            activity = activity,
            currency = currency,
            razorPayOrderId = razorPayOrderId,
            keyId = keyId,
            description = description
        )
    }
}