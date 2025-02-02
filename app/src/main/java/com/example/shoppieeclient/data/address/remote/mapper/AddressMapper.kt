package com.example.shoppieeclient.data.address.remote.mapper

import com.example.shoppieeclient.data.address.remote.dto.AddressDto
import com.example.shoppieeclient.domain.address.models.AddressModel

fun AddressDto.toAddressModel(): AddressModel {
    return AddressModel(
        id = this.id,
        userId = this.userId,
        streetAddress = this.streetAddress,
        city = this.city,
        state = this.state,
        zipCode = this.zipCode
    )
}