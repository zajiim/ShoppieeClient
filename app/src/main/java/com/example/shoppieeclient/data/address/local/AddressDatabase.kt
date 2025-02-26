package com.example.shoppieeclient.data.address.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.shoppieeclient.data.address.local.dao.SelectedAddressDao
import com.example.shoppieeclient.data.address.local.entity.SelectedAddressEntity
import com.example.shoppieeclient.data.home.account.local.entity.ProfileEntity

@Database(
    entities = [SelectedAddressEntity::class],
    version = 1,
    exportSchema = false
)

abstract class AddressDatabase: RoomDatabase() {
    abstract fun selectedAddressDao(): SelectedAddressDao
}