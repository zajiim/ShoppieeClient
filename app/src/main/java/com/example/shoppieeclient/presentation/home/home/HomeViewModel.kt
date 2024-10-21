package com.example.shoppieeclient.presentation.home.home

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.example.shoppieeclient.utils.searchKeys


class HomeViewModel : ViewModel() {

    //    private val _uiState = MutableStateFlow(HomeStates(
//        selectedChip = searchKeys.keys.first(),
//        brandsList = searchKeys.toList()
//    ))
//    val uiState: StateFlow<HomeStates> = _uiState.asStateFlow()
    var uiState by mutableStateOf(
        HomeStates(
            selectedChip = searchKeys.keys.first(),
            brandsList = searchKeys.toList()
        )
    )

    fun onEvent(events: HomeEvents) {
        when (events) {
            is HomeEvents.OnChipSelected -> {
                uiState = uiState.copy(
                    selectedChip = events.chip
                )
            }

            is HomeEvents.OnQueryChange -> {
                uiState = uiState.copy(
                    query = events.query
                )
            }
        }
    }

}