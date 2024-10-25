package com.example.shoppieeclient.presentation.home.home

import com.example.shoppieeclient.domain.auth.models.home.HomeResultModel
import com.example.shoppieeclient.utils.Resource

data class HomeStates(
    val query: String = "",
    val selectedChip: String = "Nike",
    val isMenuOpen: Boolean = false,
    val isLoading: Boolean = true,
    val storeLocation: String = "New Delhi",
    val brandsList: List<Pair<String, Int>> = emptyList(),
    val homeItemsList: HomeResultModel? = null,
)