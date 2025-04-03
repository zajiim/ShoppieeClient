package com.example.shoppieeclient.domain.payment.use_cases

import com.example.shoppieeclient.domain.payment.repository.PaymentRepository

class DeleteCardByIdUseCase(
    private val paymentRepository: PaymentRepository
) {
    suspend operator fun invoke(id: Int) {
        paymentRepository.deletePaymentCard(id)
    }
}