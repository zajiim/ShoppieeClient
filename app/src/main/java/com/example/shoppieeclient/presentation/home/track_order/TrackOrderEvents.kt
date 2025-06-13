package com.example.shoppieeclient.presentation.home.track_order

sealed class TrackOrderEvents {
    data class LoadTrackOrderDetails(val id: String): TrackOrderEvents()
}