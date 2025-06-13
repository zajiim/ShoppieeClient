package com.example.shoppieeclient.domain.home.track_order.use_cases

import com.example.shoppieeclient.domain.home.track_order.models.TrackOrderModel
import com.example.shoppieeclient.domain.home.track_order.repository.ShoppieeTrackOrderRepo
import com.example.shoppieeclient.utils.Resource
import kotlinx.coroutines.flow.Flow

class GetTrackOrderDetailsUseCase(
    private val shoppieeTrackOrderRepo: ShoppieeTrackOrderRepo
) {
    operator fun invoke(orderId: String): Flow<Resource<TrackOrderModel>> {
        return shoppieeTrackOrderRepo.getTrackOrderDetails(orderId)
    }

}