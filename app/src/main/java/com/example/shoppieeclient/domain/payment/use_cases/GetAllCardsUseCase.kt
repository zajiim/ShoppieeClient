package com.example.shoppieeclient.domain.payment.use_cases

import com.example.shoppieeclient.domain.payment.models.PaymentCardModel
import com.example.shoppieeclient.domain.payment.repository.PaymentRepository
import kotlinx.coroutines.flow.Flow

class GetAllCardsUseCase(
    private val paymentRepository: PaymentRepository
) {
    operator fun invoke(): Flow<List<PaymentCardModel?>> {
        return paymentRepository.getAllCards()
    }
}