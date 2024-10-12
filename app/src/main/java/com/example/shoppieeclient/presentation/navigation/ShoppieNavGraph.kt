package com.example.shoppieeclient.presentation.navigation

import androidx.compose.animation.AnimatedContentTransitionScope
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.navigation.NavDestination.Companion.hasRoute
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import com.example.shoppieeclient.domain.common.repository.NetworkConnectivityObserver
import com.example.shoppieeclient.presentation.auth.main.MainActivityViewModel
import com.example.shoppieeclient.presentation.auth.onboarding.OnBoardingViewModel
import com.example.shoppieeclient.presentation.auth.onboarding.OnboardingScreen
import com.example.shoppieeclient.presentation.home.bottom_nav_bar.CustomBottomNavBar
import com.example.shoppieeclient.presentation.home.bottom_nav_bar.ShoppieeBottomNavBar
import com.example.shoppieeclient.presentation.home.bottom_nav_bar.listRoutes
import com.example.shoppieeclient.presentation.navigation.auth.authNavGraph
import com.example.shoppieeclient.presentation.navigation.graphs.Graphs
import com.example.shoppieeclient.presentation.navigation.main.homeNavGraph
import org.koin.androidx.compose.koinViewModel


private const val TAG = "ShoppieNavGraph"

@Composable
fun ShoppieNavGraph(
    navController: NavHostController,
    mainActivityViewModel: MainActivityViewModel = koinViewModel(),
    connectivityObserver: NetworkConnectivityObserver
) {
    val startDestination = mainActivityViewModel.startDestination

    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentDestination = navBackStackEntry?.destination
    val mainDestinations = listOf(
        Destination.Home,
        Destination.Favorites,
        Destination.Cart,
        Destination.Notifications,
        Destination.Profile
    )

    Scaffold(bottomBar = {
        if (mainDestinations.any { destination ->
                currentDestination?.hasRoute(destination::class) == true
            })
            CustomBottomNavBar(
                navController = navController,
                items = listRoutes
                )
//            ShoppieeBottomNavBar(navController)
    }) { padding ->
        NavHost(navController = navController,
            startDestination = startDestination,
            modifier = Modifier.padding(padding),
            enterTransition = {
                slideIntoContainer(
                    AnimatedContentTransitionScope.SlideDirection.Left, animationSpec = tween(600)
                )
            },
            exitTransition = {
                slideOutOfContainer(
                    AnimatedContentTransitionScope.SlideDirection.Left, animationSpec = tween(600)
                )
            },
            popEnterTransition = {
                slideIntoContainer(
                    AnimatedContentTransitionScope.SlideDirection.Right, animationSpec = tween(600)
                )
            },
            popExitTransition = {
                slideOutOfContainer(
                    AnimatedContentTransitionScope.SlideDirection.Right, animationSpec = tween(600)
                )
            }) {
            composable<Graphs.OnBoarding> {
                val onBoardingViewModel: OnBoardingViewModel = koinViewModel()
                OnboardingScreen(isLoading = onBoardingViewModel.onBoardingState.isLoading,
                    event = onBoardingViewModel::onEvent,
                    navigateToAuth = { navController.navigate(Graphs.Auth) })
            }

            authNavGraph(navController, connectivityObserver)

            homeNavGraph(navController)
        }
    }
}

