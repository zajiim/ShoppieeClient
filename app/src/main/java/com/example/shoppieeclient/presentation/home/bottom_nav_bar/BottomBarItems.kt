package com.example.shoppieeclient.presentation.home.bottom_nav_bar

import com.example.shoppieeclient.R
import androidx.annotation.DrawableRes
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.outlined.Check
import androidx.compose.material.icons.outlined.FavoriteBorder
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.Notifications
import androidx.compose.material.icons.outlined.Person
import androidx.compose.ui.graphics.vector.ImageVector
import com.example.shoppieeclient.presentation.navigation.Destination

sealed class BottomBarScreen(
    val title: String,
    val destination: Destination,
    @DrawableRes val icon: Int? = null
//    val selectedIcon: ImageVector? = null,
//    val unSelectedIcon: ImageVector? = null,
) {
    data object Home: BottomBarScreen(
        title = "Home",
        destination = Destination.Home,
        icon = R.drawable.ic_home
//        selectedIcon = Icons.Filled.Home,
//        unSelectedIcon = Icons.Outlined.Check
    )

    data object Favorite: BottomBarScreen(
        title = "Favorite",
        destination = Destination.Favorites,
        icon = R.drawable.ic_favorites
//        selectedIcon = Icons.Filled.Favorite,
//        unSelectedIcon = Icons.Outlined.FavoriteBorder
    )

    data object Cart: BottomBarScreen(
        title = "Cart",
        destination = Destination.Cart,
//        icon = R.drawable.ic_cart
//        selectedIcon = null,
//        unSelectedIcon = null
    )

    data object Notification: BottomBarScreen(
        title = "Notification",
        destination = Destination.Notifications,
        icon = R.drawable.ic_notification
//        selectedIcon = Icons.Filled.Notifications,
//        unSelectedIcon = Icons.Outlined.Notifications
    )

    data object Profile: BottomBarScreen(
        title = "Profile",
        destination = Destination.Profile,
        icon = R.drawable.ic_profile
//        selectedIcon = Icons.Filled.Person,
//        unSelectedIcon = Icons.Outlined.Person
    )
}