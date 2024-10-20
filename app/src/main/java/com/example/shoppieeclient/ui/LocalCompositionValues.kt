package com.example.shoppieeclient.ui

import androidx.compose.runtime.compositionLocalOf

val LocalIsMenuOpen = compositionLocalOf { false }
val LocalToggleMenu = compositionLocalOf<() -> Unit> { {} }