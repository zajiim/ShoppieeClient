package com.example.shoppieeclient.utils

import android.app.Activity
import android.content.Context
import android.content.ContextWrapper
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

fun String.startsWithAny(vararg prefixes: String): Boolean {
    return prefixes.any { this.startsWith(it) }
}

fun Context.findActivity(): Activity? {
    var context = this
    while (context is ContextWrapper) {
        if (context is Activity) return context
        context = context.baseContext
    }
    return null
}