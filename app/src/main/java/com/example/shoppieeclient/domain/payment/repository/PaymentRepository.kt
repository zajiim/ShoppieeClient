package com.example.shoppieeclient.domain.payment.repository

import com.example.shoppieeclient.domain.payment.models.PaymentCardModel
import kotlinx.coroutines.flow.Flow

interface PaymentRepository {
    fun getAllCards(): Flow<List<PaymentCardModel?>>

    suspend fun insertPaymentCard(paymentCardModel: PaymentCardModel)

    suspend fun deletePaymentCard(id: Int)
}