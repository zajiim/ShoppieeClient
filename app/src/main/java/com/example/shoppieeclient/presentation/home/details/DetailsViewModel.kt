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
import kotlinx.coroutines.launch


private const val TAG = "DetailsViewModel"

class DetailsViewModel(
    private val fetchDetailsUseCase: GetProductDetailsUseCase,
    private val addToCartUseCase: AddToCartUseCase,
    savedStateHandle: SavedStateHandle
) : ViewModel() {

    var uiState by mutableStateOf(DetailsState())
        private set

    init {
        val productId: String = checkNotNull(savedStateHandle["id"])
        fetchDetails(productId)
    }

    fun onEvent(event: DetailsEvent) {
        when (event) {
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
                uiState = uiState.copy(selectedSize = event.size)
            }

            is DetailsEvent.AddToCart -> {
                Log.e(
                    TAG,
                    "onEvent: ${event.productId}, reg = ${event.selectedRegion}, size = ${event.selectedSize}",
                )
                addToCart(event.productId, event.selectedRegion, event.selectedSize)
            }
        }
    }

    private fun addToCart(productId: String, selectedRegion: String, selectedSize: Int) =
        viewModelScope.launch {
            uiState = uiState.copy(isLoading = true)
            Log.e(TAG, "addToCart:jaba ..... $selectedSize, $selectedRegion", )
            addToCartUseCase(productId, selectedRegion, selectedSize).collect { result ->
                when (result) {
                    is Resource.Error -> {
                        uiState = uiState.copy(
                            isLoading = false, error = result.message, isAddedToCart = false
                        )
                    }

                    is Resource.Loading -> {
                        uiState = uiState.copy(isLoading = true)
                    }

                    is Resource.Success -> {
                        uiState = uiState.copy(
                            isLoading = false, error = result.message, isAddedToCart = true
                        )
                    }
                }
            }

        }

    private fun fetchDetails(productId: String) = viewModelScope.launch {
        uiState = uiState.copy(isLoading = true)
        fetchDetailsUseCase(productId).collect { result ->
            when (result) {
                is Resource.Error -> {
                    uiState = uiState.copy(
                        isLoading = false, error = result.message, details = null
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