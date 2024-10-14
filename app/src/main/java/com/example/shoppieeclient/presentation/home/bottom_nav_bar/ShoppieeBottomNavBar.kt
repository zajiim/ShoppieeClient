package com.example.shoppieeclient.presentation.home.bottom_nav_bar

import android.annotation.SuppressLint
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.res.painterResource
import androidx.navigation.NavDestination.Companion.hasRoute
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.example.shoppieeclient.ui.theme.Primary
import com.example.shoppieeclient.ui.theme.SelectedColor
import com.example.shoppieeclient.ui.theme.UnSelectedColor


//@SuppressLint("RestrictedApi")
//@Composable
//fun ShoppieeBottomNavBar(
//    navController: NavHostController,
//) {
//    val bottomBarItems = listOf(
//        BottomBarScreen.Home,
//        BottomBarScreen.Favorite,
//        BottomBarScreen.Cart,
//        BottomBarScreen.Notification,
//        BottomBarScreen.Profile
//    )
//    NavigationBar(
//        containerColor = Primary
//    ) {
//        val navBackStackEntry by navController.currentBackStackEntryAsState()
//        val currentDestination = navBackStackEntry?.destination
//        bottomBarItems.forEach { screen ->
////            val isSelected =
////                currentDestination?.hierarchy?.any { item -> item.hasRoute(screen.destination::class) } == true
//            val isSelected = currentDestination?.hierarchy?.any { item -> item == screen.destination } == true
//            NavigationBarItem(
//                selected = isSelected,
//                onClick = {
//                    navController.navigate(screen.destination) {
//                        popUpTo(navController.graph.findStartDestination().id) {
//                            saveState = true
//                        }
//                        launchSingleTop = true
//                        restoreState = true
//                    }
//                }, icon = {
////                    screen.icon
////                    screen.icon?.let { iconSettings ->
////                        Icon(
////                            tint = if (currentDestination == screen) SelectedColor else UnSelectedColor,
////                            painter = painterResource(id = iconSettings),
////                            contentDescription = screen.title
////                        )
////                    }
////                    screen.selectedIcon?.let { selectedIcon ->
////                        Icon(
////                            imageVector = if (currentDestination == screen) {
////                                selectedIcon
////                            } else {
////                                screen.unSelectedIcon ?: selectedIcon
////                            }, contentDescription = screen.title
////                        )
////                    }
//
//                }, label = {
//                    Text(text = screen.title, color = Primary)
//                }, alwaysShowLabel = true, colors = NavigationBarItemDefaults.colors(
//                    selectedIconColor = MaterialTheme.colorScheme.onPrimary,
//                    unselectedIconColor = MaterialTheme.colorScheme.onPrimary.copy(alpha = 0.6f),
//                    selectedTextColor = MaterialTheme.colorScheme.onPrimary,
//                    unselectedTextColor = MaterialTheme.colorScheme.onPrimary.copy(alpha = 0.6f)
//                )
//            )
//        }
//    }
//
//}