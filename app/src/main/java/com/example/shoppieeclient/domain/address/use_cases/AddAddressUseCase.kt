package com.example.shoppieeclient.domain.address.use_cases

import com.example.shoppieeclient.data.address.remote.dto.AddressRequest
import com.example.shoppieeclient.domain.address.models.AddressModel
import com.example.shoppieeclient.domain.address.repository.AddressRepo
import com.example.shoppieeclient.utils.Resource
import kotlinx.coroutines.flow.Flow

class AddAddressUseCase(
    private val addressRepo: AddressRepo
) {
    operator fun invoke(
        streetAddress: String,
        city: String,
        state: String?,
        zipCode: String?
    ): Flow<Resource<List<AddressModel>>> {
        return addressRepo.addAddress(
            streetAddress = streetAddress,
            city = city,
            state = state,
            zipCode = zipCode
        )
    }
}