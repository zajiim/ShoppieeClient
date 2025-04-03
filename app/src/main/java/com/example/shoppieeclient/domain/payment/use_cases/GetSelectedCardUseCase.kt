package com.example.shoppieeclient.domain.payment.use_cases

import com.example.shoppieeclient.domain.payment.models.PaymentCardModel
import com.example.shoppieeclient.domain.payment.repository.PaymentRepository
import kotlinx.coroutines.flow.Flow

class GetSelectedCardUseCase(
    private val paymentRepository: PaymentRepository
) {
    suspend operator fun invoke(): Flow<PaymentCardModel?> {
        return paymentRepository.getSelectedCard()
    }
}