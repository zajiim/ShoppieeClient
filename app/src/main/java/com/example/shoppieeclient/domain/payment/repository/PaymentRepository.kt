package com.example.shoppieeclient.domain.payment.repository

import com.example.shoppieeclient.domain.payment.models.PaymentCardModel
import kotlinx.coroutines.flow.Flow

interface PaymentRepository {
    fun getAllCards(): Flow<List<PaymentCardModel?>>

    suspend fun upsertPaymentCard(paymentCardModel: PaymentCardModel)

    suspend fun getCardById(id: Int): PaymentCardModel?

    suspend fun deletePaymentCard(id: Int)

    suspend fun setSelectedCard(paymentCardModel: PaymentCardModel)

    suspend fun getSelectedCard(): Flow<PaymentCardModel?>
}