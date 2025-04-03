package com.example.shoppieeclient.data.payment.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.shoppieeclient.utils.Constants

@Entity(tableName = Constants.PAYMENT_DATA_TABLE)
data class PaymentEntity(
    @PrimaryKey
    val id: Int = 0,
    val cardNumber: String,
    val cardHolderName: String,
    val expirationDate: String,
    val cvv: String,
    val isSelected: Boolean = false
)
