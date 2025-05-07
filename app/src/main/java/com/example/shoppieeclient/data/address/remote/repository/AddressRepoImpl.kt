package com.example.shoppieeclient.data.address.remote.repository

import android.util.Log
import com.example.shoppieeclient.data.address.remote.api.AddressApiService
import com.example.shoppieeclient.data.address.remote.dto.AddressRequest
import com.example.shoppieeclient.data.address.remote.mapper.toAddressModel
import com.example.shoppieeclient.domain.address.models.AddressModel
import com.example.shoppieeclient.domain.address.repository.AddressRepo
import com.example.shoppieeclient.utils.Resource
import io.ktor.client.plugins.ClientRequestException
import io.ktor.client.plugins.ServerResponseException
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.serialization.SerializationException
import java.io.IOException

private const val TAG = "AddressRepoImpl"

class AddressRepoImpl(
    private val addressApiService: AddressApiService
) : AddressRepo {
    override fun getAddresses(): Flow<Resource<List<AddressModel>>> = flow {
        try {
            emit(Resource.Loading())
            val addressResponse = addressApiService.getAddresses()
            val addresses = addressResponse.result.addresses
            if (addressResponse.status == 200) {
                val address = addresses?.map { it.toAddressModel() }
                emit(Resource.Success(address))
            } else {
                emit(Resource.Error(addressResponse.message))
            }
        } catch (e: ClientRequestException) {
            emit(Resource.Error(e.message))
        } catch (e: ServerResponseException) {
            emit(Resource.Error(e.message))
        } catch (e: SerializationException) {
            emit(Resource.Error(e.message ?: "Unknown error occurred"))
        } catch (e: IOException) {
            emit(Resource.Error(e.message ?: "Unknown error occurred"))
        } catch (e: Exception) {
            emit(Resource.Error(e.message ?: "Unknown error occurred"))
        }

    }.catch { e ->
        emit(Resource.Error(e.message ?: "Unexpected error occurred"))
    }.flowOn(Dispatchers.IO)

    override fun deleteAddress(id: String): Flow<Resource<List<AddressModel>>> = flow {
        try {
            emit(Resource.Loading())
            val addressResponse = addressApiService.deleteAddress(id)
            val addresses = addressResponse.result.addresses
            if (addressResponse.status == 200) {
                val address = addresses.map { it.toAddressModel() }
                emit(Resource.Success(address))
            } else {
                emit(Resource.Error(addressResponse.message))
            }

        } catch (e: ClientRequestException) {
            emit(Resource.Error(e.message))
        } catch (e: ServerResponseException) {
            emit(Resource.Error(e.message))
        } catch (e: SerializationException) {
            emit(Resource.Error(e.message ?: "Unknown error occurred"))
        } catch (e: IOException) {
            emit(Resource.Error(e.message ?: "Unknown error occurred"))
        } catch (e: Exception) {
            emit(Resource.Error(e.message ?: "Unknown error occurred"))
        }
    }.catch { e ->
        emit(Resource.Error(e.message ?: "Unexpected error occurred"))
    }.flowOn(Dispatchers.IO)

    override fun addAddress(
        streetAddress: String, city: String, state: String?, zipCode: String?
    ): Flow<Resource<List<AddressModel>>> = flow {
        try {
            emit(Resource.Loading())
            val addressRequest = AddressRequest(
                streetAddress = streetAddress, city = city, state = state, zipCode = zipCode
            )
            val addressResponse = addressApiService.addAddress(
                addressRequest = addressRequest
            )
            val addresses = addressResponse.result.addresses.map { it.toAddressModel() }
            emit(Resource.Success(addresses))

        } catch (e: ClientRequestException) {
            emit(Resource.Error(e.message))
        } catch (e: ServerResponseException) {
            emit(Resource.Error(e.message))
        } catch (e: SerializationException) {
            Log.e(TAG, "exception: ${e.message}", )
            emit(Resource.Error(e.message ?: "Unknown error occurred"))
        } catch (e: IOException) {
            emit(Resource.Error(e.message ?: "Unknown error occurred"))
        } catch (e: Exception) {
            emit(Resource.Error(e.message ?: "Unknown error occurred"))
        }
    }.catch { e ->
        emit(Resource.Error(e.message ?: "Unexpected error occurred"))
    }.flowOn(Dispatchers.IO)

    override fun editAddress(
        id: String,
        streetAddress: String, city: String, state: String?, zipCode: String?
    ): Flow<Resource<List<AddressModel>>> = flow {
        try {
            emit(Resource.Loading())
            val addressRequest = AddressRequest(
                streetAddress = streetAddress, city = city, state = state, zipCode = zipCode
            )
            val addressResponse = addressApiService.editAddress(id = id, addressRequest = addressRequest)
            val addresses = addressResponse.result.addresses.map { it.toAddressModel() }
            emit(Resource.Success(addresses))

        } catch (e: ClientRequestException) {
            emit(Resource.Error(e.message))
        } catch (e: ServerResponseException) {
            emit(Resource.Error(e.message))
        } catch (e: SerializationException) {
            emit(Resource.Error(e.message ?: "Unknown error occurred"))
        } catch (e: IOException) {
            emit(Resource.Error(e.message ?: "Unknown error occurred"))
        } catch (e: Exception) {
            emit(Resource.Error(e.message ?: "Unknown error occurred"))
        }
    }.catch { e ->
        emit(Resource.Error(e.message ?: "Unexpected error occurred"))
    }.flowOn(Dispatchers.IO)

    override fun selectAddress(id: String): Flow<Resource<List<AddressModel>>> = flow {
        try {
            emit(Resource.Loading())
            val addressResponse = addressApiService.selectAddress(id)
            val addresses = addressResponse.result.addresses.map { it.toAddressModel() }
            emit(Resource.Success(addresses))
        } catch (e: ClientRequestException) {
            emit(Resource.Error(e.message))
        } catch (e: ServerResponseException) {
            emit(Resource.Error(e.message))
        } catch (e: SerializationException) {
            emit(Resource.Error(e.message ?: "Unknown error occurred"))
        } catch (e: IOException) {
            emit(Resource.Error(e.message ?: "Unknown error occurred"))
        } catch (e: Exception) {
            emit(Resource.Error(e.message ?: "Unknown error occurred"))
        }
    }.catch { e ->
        emit(Resource.Error(e.message ?: "Unexpected error occurred"))
    }.flowOn(Dispatchers.IO)

    override fun getSelectedAddress(): Flow<Resource<List<AddressModel>>> = flow {
        try {
            emit(Resource.Loading())
            val addressResponse = addressApiService.getSelectedAddress()
            val addressList = addressResponse.result.addresses
            if (addressResponse.status == 200) {
                val address = addressList.map { it.toAddressModel() }
                emit(Resource.Success(address))
            } else {
                emit(Resource.Error(addressResponse.message))
            }
        } catch (e: ClientRequestException) {
            emit(Resource.Error(e.message))
        } catch (e: ServerResponseException) {
            emit(Resource.Error(e.message))
        } catch (e: SerializationException) {
            emit(Resource.Error(e.message ?: "Unknown error occurred"))
        } catch (e: IOException) {
            emit(Resource.Error(e.message ?: "Unknown error occurred"))
        } catch (e: Exception) {
            emit(Resource.Error(e.message ?: "Unknown error occurred"))
        }
    }.catch { e->
        emit(Resource.Error(e.message ?: "Unknown error occurred"))
    }.flowOn(Dispatchers.IO)

}