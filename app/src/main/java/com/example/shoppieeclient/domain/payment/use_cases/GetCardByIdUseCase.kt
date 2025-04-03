package com.example.shoppieeclient.domain.payment.use_cases

import com.example.shoppieeclient.domain.payment.models.PaymentCardModel
import com.example.shoppieeclient.domain.payment.repository.PaymentRepository

class GetCardByIdUseCase(private val paymentRepository: PaymentRepository) {
    suspend operator fun invoke(id: Int): PaymentCardModel? {
        return paymentRepository.getCardById(id)
    }
}