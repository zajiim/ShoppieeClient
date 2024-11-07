package com.example.shoppieeclient.presentation.navigation.main

import androidx.compose.animation.AnimatedVisibilityScope
import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.animation.SharedTransitionLayout
import androidx.compose.animation.SharedTransitionScope
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import androidx.navigation.toRoute
import com.example.shoppieeclient.presentation.home.home.HomeScreen
import com.example.shoppieeclient.presentation.home.cart.CartScreen
import com.example.shoppieeclient.presentation.home.details.DetailsScreen
import com.example.shoppieeclient.presentation.home.details.DetailsViewModel
import com.example.shoppieeclient.presentation.home.favorites.FavoriesScreen
import com.example.shoppieeclient.presentation.home.notifications.NotificationsScreen
import com.example.shoppieeclient.presentation.home.profile.ProfileScreen
import com.example.shoppieeclient.presentation.navigation.Destination
import com.example.shoppieeclient.presentation.navigation.graphs.Graphs
import org.koin.androidx.compose.koinViewModel


@OptIn(ExperimentalMaterial3Api::class, ExperimentalSharedTransitionApi::class)
fun NavGraphBuilder.homeNavGraph(
    navController: NavHostController,
    sharedTransitionScope: SharedTransitionScope,
) {
    navigation<Graphs.Home>(
        startDestination = Destination.Home
    ) {
            composable<Destination.Home> {
                HomeScreen(
                    onNavigateToDetails = {
                        navController.navigate(
                            Destination.Details(
                                id = it
                            )
                        )
                    },
                    sharedTransitionScope = sharedTransitionScope,
                    animatedVisibilityScope = this
                )
            }

            composable<Destination.Details> { backStackEntry ->
                val user = backStackEntry.toRoute<Destination.Details>()
                val viewModel: DetailsViewModel = koinViewModel()
                DetailsScreen(
                    viewModel = viewModel,
                    onNavigateClick = { navController.navigateUp() },
                    sharedTransitionScope = sharedTransitionScope,
                    animatedVisibilityScope = this
                )
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
