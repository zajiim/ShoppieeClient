package com.example.shoppieeclient.domain.home.track_order.repository

import com.example.shoppieeclient.domain.home.track_order.models.TrackOrderModel
import com.example.shoppieeclient.utils.Resource
import kotlinx.coroutines.flow.Flow

interface ShoppieeTrackOrderRepo {
    fun getTrackOrderDetails(orderId: String): Flow<Resource<TrackOrderModel>>
}