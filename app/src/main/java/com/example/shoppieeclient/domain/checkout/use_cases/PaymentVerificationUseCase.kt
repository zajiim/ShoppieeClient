package com.example.shoppieeclient.domain.checkout.use_cases

import com.example.shoppieeclient.domain.checkout.model.PaymentVerificationResponseModel
import com.example.shoppieeclient.domain.checkout.repository.RazorPayPaymentRepository
import com.example.shoppieeclient.utils.Resource
import kotlinx.coroutines.flow.Flow

class PaymentVerificationUseCase(
    private val razorPayPaymentRepository: RazorPayPaymentRepository
) {
    operator fun invoke(
        orderId: String,
        paymentId: String,
        signature: String,
        razorpayId: String
    ): Flow<Resource<PaymentVerificationResponseModel>> = razorPayPaymentRepository.verifyPayment(
        orderId = orderId,
        paymentId = paymentId,
        signature = signature,
        razorPayOrderId = razorpayId
    )
}