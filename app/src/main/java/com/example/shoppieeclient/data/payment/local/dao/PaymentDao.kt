package com.example.shoppieeclient.data.payment.local.dao

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Upsert
import com.example.shoppieeclient.data.payment.local.entity.PaymentEntity
import com.example.shoppieeclient.utils.Constants
import kotlinx.coroutines.flow.Flow

@Dao
interface PaymentDao {
    @Query("SELECT * FROM ${Constants.PAYMENT_DATA_TABLE}")
    fun getAllCards(): Flow<List<PaymentEntity>>

    @Upsert
    suspend fun upsertPaymentCard(paymentEntity: PaymentEntity)

    @Query("SELECT * FROM ${Constants.PAYMENT_DATA_TABLE} WHERE id = :id")
    suspend fun getCardById(id: Int): PaymentEntity?

    @Query("DELETE FROM ${Constants.PAYMENT_DATA_TABLE} WHERE id = :id")
    suspend fun deleteCard(id: Int)

    @Query("UPDATE ${Constants.PAYMENT_DATA_TABLE} SET isSelected = 1 WHERE id = :paymentId")
    suspend fun setSelectedCard(paymentId: Int)

    @Query("UPDATE ${Constants.PAYMENT_DATA_TABLE} SET isSelected = 0")
    suspend fun resetSelectedCards()

    @Query("SELECT * FROM ${Constants.PAYMENT_DATA_TABLE} WHERE isSelected = 1")
    fun getSelectedCard(): Flow<PaymentEntity?>

}