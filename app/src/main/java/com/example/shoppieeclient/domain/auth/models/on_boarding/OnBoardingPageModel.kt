package com.example.shoppieeclient.domain.auth.models.on_boarding

import androidx.annotation.DrawableRes
import com.example.shoppieeclient.R

sealed class OnBoardingPageModel(
    @DrawableRes val image: Int,
    val title: String,
    val description: String
) {
    data object First : OnBoardingPageModel(
        image = R.drawable.page_1,
        title = "Start Journey with Shoppie",
        description = "Smart, Gorgeous & Fashionable Collection"
    )

    data object Second : OnBoardingPageModel(
        image = R.drawable.page_2,
        title = "Follow Latest Style",
        description = "There are many beautiful and attractive plants to your room"
    )

    data object Third : OnBoardingPageModel(
        image = R.drawable.page_3,
        title = "Summer Shopping 2024",
        description = "Smart, Gorgeous & Fashionable Collection"
    )
}
