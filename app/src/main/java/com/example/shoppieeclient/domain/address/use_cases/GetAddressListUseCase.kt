package com.example.shoppieeclient.domain.address.use_cases

import com.example.shoppieeclient.domain.address.models.AddressModel
import com.example.shoppieeclient.domain.address.repository.AddressRepo
import com.example.shoppieeclient.utils.Resource
import kotlinx.coroutines.flow.Flow

class GetAddressListUseCase(
    private val addressRepo: AddressRepo
) {
    operator fun invoke(): Flow<Resource<List<AddressModel>>> {
        return addressRepo.getAddresses()
    }
}