package com.example.shoppieeclient.domain.common.repository

import com.example.shoppieeclient.domain.common.model.NetworkStatus
import kotlinx.coroutines.flow.StateFlow

interface NetworkConnectivityObserver {
    val networkStatus: StateFlow<NetworkStatus>
}