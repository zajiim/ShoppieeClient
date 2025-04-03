package com.example.shoppieeclient.domain.payment.use_cases

import com.example.shoppieeclient.domain.payment.models.PaymentCardModel
import com.example.shoppieeclient.domain.payment.repository.PaymentRepository

class UpsertCardUseCase(
    private val paymentRepository: PaymentRepository
) {
    suspend operator fun invoke(paymentCardModel: PaymentCardModel) {
        paymentRepository.upsertPaymentCard(paymentCardModel)
    }
}