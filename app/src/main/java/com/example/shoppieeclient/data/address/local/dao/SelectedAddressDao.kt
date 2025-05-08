package com.example.shoppieeclient.data.address.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.shoppieeclient.data.address.local.entity.SelectedAddressEntity
import com.example.shoppieeclient.utils.Constants
import kotlinx.coroutines.flow.Flow

@Dao
interface SelectedAddressDao {
    @Query("SELECT * FROM ${Constants.ADDRESS_DATA_TABLE} LIMIT 1")
    fun getSelectedAddress(): Flow<SelectedAddressEntity?>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertSelectedAddress(address: SelectedAddressEntity)

    @Query("DELETE FROM ${Constants.ADDRESS_DATA_TABLE}")
    suspend fun deleteSelectedAddress()

}