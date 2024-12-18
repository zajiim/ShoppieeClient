package com.example.shoppieeclient.utils

import android.util.Patterns

fun isNumber(value: String): Boolean {
    return value.isEmpty() || Regex("^\\d+$").matches(value)
}

fun isEmailValid(email: String): Boolean {
    return Patterns.EMAIL_ADDRESS.matcher(email).matches()
}

fun isPasswordValid(password: String): Boolean {
    return password.any {it.isDigit()} && password.any {it.isLetter()}
}

fun Double.roundToTwoDecimalPlaces(): Double {
    return String.format("%.2f".format(this)).toDouble()
}