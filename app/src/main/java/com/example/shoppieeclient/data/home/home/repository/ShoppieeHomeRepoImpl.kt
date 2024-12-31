package com.example.shoppieeclient.data.home.home.repository

import android.util.Log
import com.example.shoppieeclient.data.home.home.remote.api.ShoppieeHomeApiService
import com.example.shoppieeclient.data.home.home.remote.dto.details.AddToCartRequestDto
import com.example.shoppieeclient.data.home.home.remote.mapper.home.toAddToCartResultModel
import com.example.shoppieeclient.data.home.home.remote.mapper.home.toHomeResultModel
import com.example.shoppieeclient.data.home.home.remote.mapper.home.toProductDetailsModel
import com.example.shoppieeclient.domain.home.home.models.AddToCartResultModel
import com.example.shoppieeclient.domain.home.home.models.DetailsProductModel
import com.example.shoppieeclient.domain.home.home.models.HomeResultModel
import com.example.shoppieeclient.domain.home.home.repository.ShoppieeHomeRepo
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

private const val TAG = "ShoppieeHomeRepoImpl"

class ShoppieeHomeRepoImpl(
    private val shoppieeHomeApi: ShoppieeHomeApiService
) : ShoppieeHomeRepo {
    override fun getBrandsData(category: String): Flow<Resource<HomeResultModel>> =
        flow {
            try {
                emit(Resource.Loading())
                val brandProductsResponse = shoppieeHomeApi.getBrandProductsData(category)
                Log.e(TAG, "getPopularResponse==>: $brandProductsResponse")
                val result = brandProductsResponse.result
                if (brandProductsResponse.status == 200 && result != null) {
                    val brandsResultModel = result.toHomeResultModel()
                    emit(Resource.Success(brandsResultModel))
                } else {
                    emit(Resource.Error(brandProductsResponse.message))
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
            emit(Resource.Error(e.localizedMessage ?: "Unexpected error occurred"))
        }.flowOn(Dispatchers.IO)

    override fun fetchProductDetails(productId: String): Flow<Resource<DetailsProductModel>> =
        flow {

            try {
                emit(Resource.Loading())
                val productDetailsResponse = shoppieeHomeApi.fetchProductDetails(productId)
                Log.e(TAG, "fetchDetailResponse==>: $productDetailsResponse")
                val result = productDetailsResponse.result
                Log.e(TAG, "fetchProductDetails before: $result")
                if (productDetailsResponse.status == 200 && result != null) {
                    Log.e(TAG, "fetchProductDetails: $result")
                    val productDetailsModel = result.product?.toProductDetailsModel()
                    Log.e(TAG, "fetchProductDetails after mapping: $productDetailsModel")
                    emit(Resource.Success(productDetailsModel))
                } else {
                    emit(Resource.Error(productDetailsResponse.message))
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

    override fun addToCart(
        productId: String,
        selectedRegion: String,
        selectedSize: Int
    ): Flow<Resource<AddToCartResultModel>> = flow {
        try {
            emit(Resource.Loading())
            val addToCartRequest = AddToCartRequestDto(
                id = productId,
                region = selectedRegion,
                size = selectedSize
            )
            Log.e(TAG, "addToCartRequest==>: $addToCartRequest")
            val addToCartResponse = shoppieeHomeApi.addToCart(addToCartRequest)
            val result = addToCartResponse.result
            Log.e(TAG, "addToCartRequest==>: $result")
            if (addToCartResponse.status == 200) {
                val addToCartResultModel = result.toAddToCartResultModel()
                emit(Resource.Success(addToCartResultModel))
            } else {
                emit(Resource.Error(addToCartResponse.message))
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