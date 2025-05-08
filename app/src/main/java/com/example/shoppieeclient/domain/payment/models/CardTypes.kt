package com.example.shoppieeclient.domain.payment.models

import androidx.annotation.DrawableRes
import com.example.shoppieeclient.R

enum class CardTypes(val title: String, @DrawableRes val image: Int? = null) {
    VISA("Visa", R.drawable.visa),
    MASTERCARD("Mastercard", R.drawable.mastercard),
    RUPAY("Rupay", R.drawable.rupay),
    NONE("None")
}