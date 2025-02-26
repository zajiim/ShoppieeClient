package com.example.shoppieeclient.data.address.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.shoppieeclient.utils.Constants

@Entity(tableName = Constants.ADDRESS_DATA_TABLE)
data class SelectedAddressEntity(
    @PrimaryKey
    val addressId: String,
    val streetAddress: String,
    val city: String,
    val state: String,
    val zipCode: String,
)
