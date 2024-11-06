package com.example.shoppieeclient.presentation.home.details

sealed class DetailsEvent {
    data class LoadDetails(val id: String): DetailsEvent()
    object ToggleDescription : DetailsEvent()
    data class SelectImage(val index: Int) : DetailsEvent()
    data class SelectRegion(val region: String): DetailsEvent()
    data class SelectSize(val size: Int, val index: Int): DetailsEvent()
}