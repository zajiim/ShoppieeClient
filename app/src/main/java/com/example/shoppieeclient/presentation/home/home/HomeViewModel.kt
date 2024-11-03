package com.example.shoppieeclient.presentation.home.home

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.shoppieeclient.domain.auth.use_cases.home.GetHomeApiUseCase
import com.example.shoppieeclient.utils.Resource
import com.example.shoppieeclient.utils.searchKeys
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext


class HomeViewModel(
    private val getHomeApiUseCase: GetHomeApiUseCase
) : ViewModel() {

    var uiState by mutableStateOf(
        HomeStates(
            selectedChip = searchKeys.keys.first(),
            brandsList = searchKeys.toList()
        )
    )
        private set

    init {
        fetchHomeApiItems(uiState.selectedChip)
    }

    fun onEvent(events: HomeEvents) {
        when (events) {
            is HomeEvents.OnChipSelected -> {
                uiState = uiState.copy(
                    selectedChip = events.chip
                )
                fetchHomeApiItems(events.chip)

            }

            is HomeEvents.OnQueryChange -> {
                uiState = uiState.copy(
                    query = events.query
                )
            }
        }
    }

    private fun fetchHomeApiItems(brand: String) = viewModelScope.launch {
        withContext(Dispatchers.IO) {
            getHomeApiUseCase(brand).collect { result ->
                when (result) {
                    is Resource.Error -> {
                        uiState = uiState.copy(
                            homeItemsList = null,
                            isLoading = false,
                            homeError = result.message
                        )
                    }

                    is Resource.Loading -> {
                        uiState = uiState.copy(
                            homeItemsList = null,
                            isLoading = true,
                            homeError = null
                        )
                    }

                    is Resource.Success -> {
                        uiState = uiState.copy(
                            homeItemsList = result.data,
                            isLoading = false,
                            homeError = null
                        )
                    }
                }
            }
        }
    }
}