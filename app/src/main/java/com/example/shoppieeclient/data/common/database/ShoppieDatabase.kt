package com.example.shoppieeclient.data.common.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.shoppieeclient.data.address.local.dao.SelectedAddressDao
import com.example.shoppieeclient.data.address.local.entity.SelectedAddressEntity
import com.example.shoppieeclient.data.home.account.local.dao.ProfileDao
import com.example.shoppieeclient.data.home.account.local.entity.ProfileEntity

@Database(
    entities = [
        ProfileEntity::class,
        SelectedAddressEntity::class
    ],
    version = 1,
    exportSchema = false
)

abstract class ShoppieDatabase: RoomDatabase() {
    abstract fun profileDao(): ProfileDao
    abstract fun selectedAddressDao(): SelectedAddressDao

}