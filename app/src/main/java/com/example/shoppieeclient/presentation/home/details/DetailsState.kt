package com.example.shoppieeclient.presentation.home.details

import com.example.shoppieeclient.domain.home.home.models.DetailsProductModel

data class DetailsState(
    val isLoading: Boolean = false,
    val error: String? = null,
    val details: DetailsProductModel? = null,
    val isTextExpanded: Boolean = false,
    val selectedIndex: Int = 0,
    val selectedRegion: String = "EU",
    val selectedSize: Int = 38,
    val selectedSizeIndex: Int = 0,
    val isAddedToCart: Boolean = false,
)