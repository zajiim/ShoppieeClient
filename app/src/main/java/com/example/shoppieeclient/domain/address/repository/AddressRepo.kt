package com.example.shoppieeclient.domain.address.repository

import com.example.shoppieeclient.domain.address.models.AddressModel
import com.example.shoppieeclient.utils.Resource
import kotlinx.coroutines.flow.Flow

interface AddressRepo {
    fun getAddresses(): Flow<Resource<List<AddressModel>>>

    fun deleteAddress(id: String): Flow<Resource<List<AddressModel>>>

    fun addAddress(
        streetAddress: String,
        city: String,
        state: String?,
        zipCode: String?
    ): Flow<Resource<List<AddressModel>>>
}