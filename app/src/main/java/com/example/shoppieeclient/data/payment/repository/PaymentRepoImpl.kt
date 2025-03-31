package com.example.shoppieeclient.data.payment.repository

import com.example.shoppieeclient.data.payment.local.dao.PaymentDao
import com.example.shoppieeclient.data.payment.mapper.toPaymentCardModel
import com.example.shoppieeclient.data.payment.mapper.toPaymentEntity
import com.example.shoppieeclient.domain.payment.models.PaymentCardModel
import com.example.shoppieeclient.domain.payment.repository.PaymentRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class PaymentRepoImpl(private val paymentDao: PaymentDao): PaymentRepository {
    override fun getAllCards(): Flow<List<PaymentCardModel?>> {
        return paymentDao.getAllCards().map { payment ->
            payment.map { it.toPaymentCardModel() }
        }
    }

    override suspend fun insertPaymentCard(paymentCardModel: PaymentCardModel) {
        paymentDao.insertCard(paymentEntity = paymentCardModel.toPaymentEntity())
    }

    override suspend fun deletePaymentCard(id: Int) {
        paymentDao.deleteCard(id = id)
    }
}