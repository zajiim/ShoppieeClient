package com.example.shoppieeclient.presentation.home.track_order

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.shoppieeclient.domain.home.track_order.use_cases.GetTrackOrderDetailsUseCase
import com.example.shoppieeclient.utils.Resource
import kotlinx.coroutines.launch

class TrackOrderViewModel(
    private val getTrackOrderDetailsUseCase: GetTrackOrderDetailsUseCase
): ViewModel() {

    var uiState by mutableStateOf(TrackOrderStates())
        private set


    fun onEvent(event: TrackOrderEvents) {
        when (event) {
            is TrackOrderEvents.LoadTrackOrderDetails -> {
                fetchTrackOrderDetails(event.id)
            }
        }
    }

    private fun fetchTrackOrderDetails(orderId: String) = viewModelScope.launch {
        uiState = uiState.copy(isLoading = true)
        getTrackOrderDetailsUseCase(orderId = orderId).collect { result ->
            when(result) {
                is Resource.Loading -> {
                    uiState = uiState.copy(isLoading = true)
                }
                is Resource.Success -> {
                    uiState = uiState.copy(
                        isLoading = false,
                        trackOrderDetails = result.data
                    )
                }
                is Resource.Error -> {
                    uiState = uiState.copy(
                        isLoading = false,
                        error = result.message ?: "An unexpected error occurred"
                    )
                }
            }

        }
        /*when (val result = getTrackOrderDetailsUseCase(orderId)) {
            is Resource.Success -> {
                uiState = uiState.copy(
                    isLoading = false,
                    trackOrderDetails = result.data
                )
            }
            is Resource.Error -> {
                uiState = uiState.copy(
                    isLoading = false,
                    error = result.message ?: "An unexpected error occurred"
                )
            }
        }*/
    }



}