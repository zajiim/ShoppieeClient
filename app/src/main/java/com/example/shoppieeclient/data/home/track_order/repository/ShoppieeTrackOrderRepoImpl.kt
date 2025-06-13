package com.example.shoppieeclient.data.home.track_order.repository

import android.util.Log
import com.example.shoppieeclient.data.home.track_order.remote.api.ShoppieeTrackOrderApiService
import com.example.shoppieeclient.data.home.track_order.remote.mapper.toTrackOrderModel
import com.example.shoppieeclient.domain.home.track_order.models.TrackOrderModel
import com.example.shoppieeclient.domain.home.track_order.repository.ShoppieeTrackOrderRepo
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

private const val TAG = "ShoppieeTrackOrderRepoImpl"

class ShoppieeTrackOrderRepoImpl(
    private val shoppieeTrackOrderApiService: ShoppieeTrackOrderApiService
): ShoppieeTrackOrderRepo {
    override fun getTrackOrderDetails(orderId: String): Flow<Resource<TrackOrderModel>> = flow {
        try {
            emit(Resource.Loading())
            val trackOrderResponse = shoppieeTrackOrderApiService.getTrackOrderData(orderId)
            Log.e(TAG, "getTrackOrderRes: $trackOrderResponse ...")
            if (trackOrderResponse.status == 200) {
                Log.e(TAG, "getTrackOrderDetails: inside success block")
                val trackOrderModel = trackOrderResponse.result?.toTrackOrderModel()
                Log.e(TAG, "getTrackOrderDetails: == $trackOrderModel", )
                emit(Resource.Success(trackOrderModel))
            } else {
                emit(Resource.Error("Error: ${trackOrderResponse.message}"))
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