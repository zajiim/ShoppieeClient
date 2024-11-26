package com.example.shoppieeclient.data.cart.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.example.shoppieeclient.data.cart.paging.CartsPagingSource
import com.example.shoppieeclient.data.cart.remote.api.ShoppieCartApiService
import com.example.shoppieeclient.data.cart.remote.dto.DecrementDeleteCartItemRequestDto
import com.example.shoppieeclient.data.cart.remote.dto.IncrementCartItemRequestDto
import com.example.shoppieeclient.data.cart.remote.mapper.toCartTotalModel
import com.example.shoppieeclient.data.cart.remote.mapper.toIncrementDecrementDeleteCartResultModel
import com.example.shoppieeclient.domain.cart.models.CartProductModel
import com.example.shoppieeclient.domain.cart.models.CartTotalModel
import com.example.shoppieeclient.domain.cart.models.IncrementDecrementDeleteResultModel
import com.example.shoppieeclient.domain.cart.repository.ShoppieCartRepo
import com.example.shoppieeclient.utils.Constants
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

class ShoppieCartRepoImpl(
    private val shoppieCartApi: ShoppieCartApiService
): ShoppieCartRepo {
    override fun getCartData(
        page: Int,
        limit: Int
    ): Flow<PagingData<CartProductModel>> {
        return Pager(
            config = PagingConfig(
                pageSize = Constants.PER_PAGE_ITEMS
            ),
            pagingSourceFactory = {
                CartsPagingSource(shoppieCartApi)
            }
        ).flow
    }

    override fun incrementCart(
        productId: String,
        size: String
    ): Flow<Resource<IncrementDecrementDeleteResultModel>> = flow {
        try {
            emit(Resource.Loading())
            val incrementCartRequest = IncrementCartItemRequestDto(
                id = productId,
                size = size
            )
            val incrementItemResponse = shoppieCartApi.incrementCart(incrementCartRequest)
            val result = incrementItemResponse.result
            if (incrementItemResponse.status == 200) {
                val incrementResult = result.toIncrementDecrementDeleteCartResultModel()
                emit(Resource.Success(incrementResult))
            } else {
                emit(Resource.Error(incrementItemResponse.message))
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

    override fun decrementCart(productId: String): Flow<Resource<IncrementDecrementDeleteResultModel>> = flow {
        try {
            emit(Resource.Loading())
            val decrementCartRequest = DecrementDeleteCartItemRequestDto(
                id = productId
            )
            val decrementItemResponse = shoppieCartApi.decrementCart(decrementCartRequest)
            val result = decrementItemResponse.result

            if (decrementItemResponse.status == 200) {
                val decrementResult = result.toIncrementDecrementDeleteCartResultModel()
                emit(Resource.Success(decrementResult))
            } else {
                emit(Resource.Error(decrementItemResponse.message))
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

    override fun removeFromCart(productId: String): Flow<Resource<IncrementDecrementDeleteResultModel>> = flow {
        try {
            emit(Resource.Loading())
            val decrementCartRequest = DecrementDeleteCartItemRequestDto(
                id = productId
            )
            val removeFromCartResponse = shoppieCartApi.removeFromCart(decrementCartRequest)
            val result = removeFromCartResponse.result

            if (removeFromCartResponse.status == 200) {
                val removeResult = result.toIncrementDecrementDeleteCartResultModel()
                emit(Resource.Success(removeResult))
            } else {
                emit(Resource.Error(removeFromCartResponse.message))
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

    override fun getCartTotal(): Flow<Resource<CartTotalModel>> = flow {
        try {
            emit(Resource.Loading())
            val cartTotalResponse = shoppieCartApi.getCartTotal()

            if (cartTotalResponse.status == 200) {
                val cartTotal = cartTotalResponse.result.toCartTotalModel()
                emit(Resource.Success(cartTotal))
            } else {
                emit(Resource.Error(cartTotalResponse.message))
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
}