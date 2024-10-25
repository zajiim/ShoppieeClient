package com.example.shoppieeclient.data.home.repository

import android.util.Log
import com.example.shoppieeclient.data.home.remote.api.ShoppieeHomeApiService
import com.example.shoppieeclient.data.home.remote.mapper.home.toHomeResultModel
import com.example.shoppieeclient.domain.auth.models.home.HomeResultModel
import com.example.shoppieeclient.domain.auth.repository.home.ShoppieeHomeRepo
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
}