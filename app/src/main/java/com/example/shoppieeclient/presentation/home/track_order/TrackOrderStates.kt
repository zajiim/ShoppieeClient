package com.example.shoppieeclient.presentation.home.track_order

import com.example.shoppieeclient.domain.home.track_order.models.TrackOrderModel

data class TrackOrderStates(
    val isLoading: Boolean = false,
    val error: String? = null,
    val trackOrderDetails: TrackOrderModel? = null
)
