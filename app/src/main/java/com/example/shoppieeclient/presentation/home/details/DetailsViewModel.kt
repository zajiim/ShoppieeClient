package com.example.shoppieeclient.presentation.home.details

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.shoppieeclient.domain.auth.use_cases.home.details.AddToCartUseCase
import com.example.shoppieeclient.domain.auth.use_cases.home.details.GetProductDetailsUseCase
import com.example.shoppieeclient.utils.Resource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext


private const val TAG = "DetailsViewModel"
class DetailsViewModel(
    private val fetchDetailsUseCase: GetProductDetailsUseCase,
    private val addToCartUseCase: AddToCartUseCase,
    savedStateHandle: SavedStateHandle
) : ViewModel() {
//    val productId: String = checkNotNull(savedStateHandle["id"])

    var uiState by mutableStateOf(DetailsState())
        private set
//    init {
//        Log.d(TAG, ":prod id ===> $productId")
//        fetchDetails(productId)
//    }

//    fun onEvent(event: DetailsEvent) {
//        when(event) {
//            is DetailsEvent.LoadDetails -> {
//                fetchDetails(event.id)
//            }
//        }
//    }


    init {
        val productId: String = checkNotNull(savedStateHandle["id"])
        fetchDetails(productId)
    }

    fun onEvent(event: DetailsEvent) {
        when(event) {
            is DetailsEvent.LoadDetails -> {
                fetchDetails(event.id)
            }
            DetailsEvent.ToggleDescription -> {
                uiState = uiState.copy(isTextExpanded = !uiState.isTextExpanded)
            }

            is DetailsEvent.SelectImage -> {
                uiState = uiState.copy(selectedIndex = event.index)
            }

            is DetailsEvent.SelectRegion -> {
                uiState = uiState.copy(selectedRegion = event.region)
            }
            is DetailsEvent.SelectSize -> {
                uiState = uiState.copy(selectedSize = event.size, selectedSizeIndex = event.index)
            }

            is DetailsEvent.AddToCart -> {
                addToCart(event.productId, event.selectedRegion, event.selectedSize)
            }
        }
    }

    private fun addToCart(productId: String, selectedRegion: String, selectedSize: Int) = viewModelScope.launch {
        withContext(Dispatchers.IO) {
            uiState = uiState.copy(isLoading = true)
            addToCartUseCase(productId).collect { result ->
                when(result) {
                    is Resource.Error -> {
                        uiState = uiState.copy(
                            isLoading = false,
                            error = result.message,
                            isAddedToCart = false
                        )
                    }
                    is Resource.Loading -> {
                        uiState = uiState.copy(isLoading = true)
                    }
                    is Resource.Success -> {
                        uiState = uiState.copy(
                            isLoading = false,
                            error = result.message,
                            isAddedToCart = true
                        )
                    }
                }
            }
        }
    }

    private fun fetchDetails(productId: String) = viewModelScope.launch {
        withContext(Dispatchers.IO) {
            uiState = uiState.copy(isLoading = true)
            fetchDetailsUseCase(productId).collect { result ->
                Log.d("fetchDetailsUseCase", "API result: ${result.data}")
                when(result) {
                    is Resource.Error -> {
                        uiState = uiState.copy(
                            isLoading = false,
                            error = result.message,
                            details = null
                        )
                    }
                    is Resource.Loading -> {
                        uiState = uiState.copy(isLoading = true)
                    }
                    is Resource.Success -> {
                        uiState = uiState.copy(
                            isLoading = false,
                            error = null,
                            details = result.data,
                        )
                    }
                }
            }
        }

    }

}