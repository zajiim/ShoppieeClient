package com.example.shoppieeclient.data.home.account.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.shoppieeclient.data.home.account.local.dao.ProfileDao
import com.example.shoppieeclient.data.home.account.local.entity.ProfileEntity

@Database(
    entities = [ProfileEntity::class],
    version = 1,
    exportSchema = false
)
abstract class ProfileDatabase: RoomDatabase() {
    abstract fun profileDao(): ProfileDao
}