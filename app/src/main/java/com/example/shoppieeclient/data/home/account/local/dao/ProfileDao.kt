package com.example.shoppieeclient.data.home.account.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Upsert
import com.example.shoppieeclient.data.home.account.local.entity.ProfileEntity
import com.example.shoppieeclient.utils.Constants

@Dao
interface ProfileDao {
    @Query("SELECT * FROM ${Constants.PROFILE_DATA_TABLE} LIMIT 1")
    suspend fun getProfile(): ProfileEntity?

//    @Insert(onConflict = OnConflictStrategy.REPLACE)
    @Upsert
    suspend fun insertProfile(profile: ProfileEntity)


}