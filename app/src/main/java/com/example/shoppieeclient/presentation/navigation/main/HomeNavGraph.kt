package com.example.shoppieeclient.presentation.navigation.main

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import androidx.navigation.toRoute
import com.example.shoppieeclient.presentation.home.home.HomeScreen
import com.example.shoppieeclient.presentation.home.cart.CartScreen
import com.example.shoppieeclient.presentation.home.details.DetailsScreen
import com.example.shoppieeclient.presentation.home.favorites.FavoriesScreen
import com.example.shoppieeclient.presentation.home.notifications.NotificationsScreen
import com.example.shoppieeclient.presentation.home.profile.ProfileScreen
import com.example.shoppieeclient.presentation.navigation.Destination
import com.example.shoppieeclient.presentation.navigation.graphs.Graphs


fun NavGraphBuilder.homeNavGraph(
    navController: NavHostController
) {
    navigation<Graphs.Home>(
        startDestination = Destination.Home
    ) {
        composable<Destination.Home> {
            HomeScreen(onNavigateToDetails = {
                navController.navigate(
                    Destination.Details(
                        id = 1, name = "sajim"
                    )
                )
            })
        }

        composable<Destination.Details> { backStackEntry ->
            val user = backStackEntry.toRoute<Destination.Details>()
            DetailsScreen(userDetails = user)
        }

        composable<Destination.Favorites> {
            FavoriesScreen()
        }
        composable<Destination.Cart> {
            CartScreen()
        }
        composable<Destination.Notifications> {
            NotificationsScreen()
        }
        composable<Destination.Profile> {
            ProfileScreen()
        }
    }
}
