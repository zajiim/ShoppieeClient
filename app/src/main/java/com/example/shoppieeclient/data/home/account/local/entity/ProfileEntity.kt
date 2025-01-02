package com.example.shoppieeclient.data.home.account.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.shoppieeclient.utils.Constants

@Entity(tableName = Constants.PROFILE_DATA_TABLE)
data class ProfileEntity(
    @PrimaryKey val email: String,
    val name: String,
    val profileImage: String
)
