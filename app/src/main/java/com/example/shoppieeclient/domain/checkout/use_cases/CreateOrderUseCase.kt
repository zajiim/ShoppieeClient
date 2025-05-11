package com.example.shoppieeclient.domain.checkout.use_cases

import com.example.shoppieeclient.domain.checkout.repository.RazorPayPaymentRepository

class CreateOrderUseCase(
    private val razorPayPaymentRepository: RazorPayPaymentRepository
) {
    operator fun invoke(addressId: String, amount: Double, currency: String) =
        razorPayPaymentRepository.createOrder(
            amount = amount,
            currency = currency,
            addressId = addressId
        )
}