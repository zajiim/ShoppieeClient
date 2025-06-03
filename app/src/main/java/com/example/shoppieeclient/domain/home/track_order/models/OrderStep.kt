package com.example.shoppieeclient.domain.home.track_order.models

import androidx.compose.ui.graphics.Color


data class OrderStep(
    val id: Int,
    val title: String,
    val date: String,
    val icon: Int
)

enum class StepStatus {
    COMPLETED, CURRENT, PENDING
}

data class StepColors(
    val circleColor: Color,
    val lineColor: Color,
    val textColor: Color,
    val dateColor: Color,
    val iconColor: Color
)