package com.example.shoppieeclient.data.common.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.shoppieeclient.data.address.local.dao.SelectedAddressDao
import com.example.shoppieeclient.data.address.local.entity.SelectedAddressEntity
import com.example.shoppieeclient.data.home.account.local.dao.ProfileDao
import com.example.shoppieeclient.data.home.account.local.entity.ProfileEntity
import com.example.shoppieeclient.data.payment.local.dao.PaymentDao
import com.example.shoppieeclient.data.payment.local.entity.PaymentEntity

@Database(
    entities = [
        ProfileEntity::class,
        SelectedAddressEntity::class,
        PaymentEntity::class
    ],
    version = 4,
    exportSchema = false
)

abstract class ShoppieDatabase: RoomDatabase() {
    abstract fun profileDao(): ProfileDao
//    abstract fun selectedAddressDao(): SelectedAddressDao
    abstract fun paymentDao(): PaymentDao
}