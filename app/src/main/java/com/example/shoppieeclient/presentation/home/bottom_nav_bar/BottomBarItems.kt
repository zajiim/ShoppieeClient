package com.example.shoppieeclient.presentation.home.bottom_nav_bar

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.outlined.FavoriteBorder
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.Notifications
import androidx.compose.material.icons.outlined.Person
import androidx.compose.ui.graphics.vector.ImageVector
import com.example.shoppieeclient.presentation.navigation.Destination

sealed class BottomBarScreen(
    val title: String,
    val destination: Destination,
    val selectedIcon: ImageVector? = null,
    val unSelectedIcon: ImageVector? = null,
) {
    data object Home: BottomBarScreen(
        title = "Home",
        destination = Destination.Home,
        selectedIcon = Icons.Filled.Home,
        unSelectedIcon = Icons.Outlined.Home
    )

    data object Favorite: BottomBarScreen(
        title = "Favorite",
        destination = Destination.Favorites,
        selectedIcon = Icons.Filled.Favorite,
        unSelectedIcon = Icons.Outlined.FavoriteBorder
    )

    data object Cart: BottomBarScreen(
        title = "Cart",
        destination = Destination.Cart,
        selectedIcon = null,
        unSelectedIcon = null
    )

    data object Notification: BottomBarScreen(
        title = "Notification",
        destination = Destination.Notifications,
        selectedIcon = Icons.Filled.Notifications,
        unSelectedIcon = Icons.Outlined.Notifications
    )

    data object Profile: BottomBarScreen(
        title = "Profile",
        destination = Destination.Profile,
        selectedIcon = Icons.Filled.Person,
        unSelectedIcon = Icons.Outlined.Person
    )
}