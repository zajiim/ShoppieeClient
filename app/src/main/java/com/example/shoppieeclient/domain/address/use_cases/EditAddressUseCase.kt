package com.example.shoppieeclient.domain.address.use_cases

import com.example.shoppieeclient.domain.address.models.AddressModel
import com.example.shoppieeclient.domain.address.repository.AddressRepo
import com.example.shoppieeclient.utils.Resource
import kotlinx.coroutines.flow.Flow

class EditAddressUseCase(
    private val addressRepo: AddressRepo
) {
    operator fun invoke(
        id: String,
        streetAddress: String,
        city: String,
        state: String?,
        zipCode: String?
        ): Flow<Resource<List<AddressModel>>> {
        return addressRepo.editAddress(id, streetAddress, city, state, zipCode)
    }
}