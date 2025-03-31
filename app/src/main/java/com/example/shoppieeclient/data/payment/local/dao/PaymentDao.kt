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
    suspend fun insertCard(paymentEntity: PaymentEntity)

    @Query("DELETE FROM ${Constants.PAYMENT_DATA_TABLE} WHERE id = :id")
    suspend fun deleteCard(id: Int)

}