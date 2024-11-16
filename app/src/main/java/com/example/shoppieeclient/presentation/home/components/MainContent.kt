package com.example.shoppieeclient.presentation.home.components

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavDestination.Companion.hasRoute
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.example.shoppieeclient.presentation.home.bottom_nav_bar.CustomBottomNavBar
import com.example.shoppieeclient.presentation.home.bottom_nav_bar.listRoutes
import com.example.shoppieeclient.presentation.navigation.Destination
import com.example.shoppieeclient.ui.LocalIsMenuOpen
import com.example.shoppieeclient.ui.LocalToggleMenu

@SuppressLint("RestrictedApi", "UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainContent(
    navController: NavHostController,
    content: @Composable () -> Unit,
//    isMenuOpen: Boolean,
//    onToggleMenu: () -> Unit
) {

    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentDestination = navBackStackEntry?.destination
    val mainDestinations = listOf(
        Destination.Home,
        Destination.Favorites,
        Destination.Notifications,
        Destination.Profile
    )
    val isMenuOpen = LocalIsMenuOpen.current
    val toggleMenu = LocalToggleMenu.current
    Scaffold(bottomBar = {
        if (mainDestinations.any { destination ->
                currentDestination?.hasRoute(destination::class) == true
            }) {
            Box(modifier = Modifier.fillMaxWidth()) {
                CustomBottomNavBar(
                    navController = navController, items = listRoutes
                )

                if (isMenuOpen) {
                    Box(modifier = Modifier
                        .matchParentSize()
                        .clickable {
                            toggleMenu()
                        }
                        .background(Color.Transparent))
                }
            }
        }
    }) {
        content()

    }
}