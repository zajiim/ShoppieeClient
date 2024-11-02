package com.example.shoppieeclient.presentation.home.common.details

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.shoppieeclient.domain.auth.use_cases.home.details.GetProductDetailsUseCase
import com.example.shoppieeclient.utils.Resource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class DetailsViewModel(
    private val fetchDetailsUseCase: GetProductDetailsUseCase,
): ViewModel() {

    init {
        fetchProductsDetails("67193aa8c2d72a40f98b0d9f")
    }


    private fun fetchProductsDetails(productId: String) = viewModelScope.launch(Dispatchers.IO) {
        fetchDetailsUseCase(productId).collect { result ->
            when(result) {
                is Resource.Error -> {}
                is Resource.Loading -> {}
                is Resource.Success -> {}
            }
        }
    }

}