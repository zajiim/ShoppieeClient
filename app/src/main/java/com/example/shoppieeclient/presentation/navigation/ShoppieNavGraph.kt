package com.example.shoppieeclient.presentation.navigation

import android.annotation.SuppressLint
import android.util.Log
import androidx.compose.animation.AnimatedContentTransitionScope
import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.animation.SharedTransitionLayout
import androidx.compose.animation.core.tween
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.shoppieeclient.domain.common.repository.NetworkConnectivityObserver
import com.example.shoppieeclient.presentation.auth.main.MainActivityViewModel
import com.example.shoppieeclient.presentation.auth.onboarding.OnBoardingViewModel
import com.example.shoppieeclient.presentation.auth.onboarding.OnboardingScreen
import com.example.shoppieeclient.presentation.home.components.CustomShoppieeScaffold
import com.example.shoppieeclient.presentation.navigation.auth.authNavGraph
import com.example.shoppieeclient.presentation.navigation.graphs.Graphs
import com.example.shoppieeclient.presentation.navigation.main.homeNavGraph
import org.koin.androidx.compose.koinViewModel
import androidx.compose.runtime.getValue


private const val TAG = "ShoppieNavGraph"

@OptIn(ExperimentalMaterial3Api::class)
@ExperimentalSharedTransitionApi
@SuppressLint("RestrictedApi", "UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun ShoppieNavGraph(
    navController: NavHostController,
    mainActivityViewModel: MainActivityViewModel,
    connectivityObserver: NetworkConnectivityObserver
) {
    val startDestination = mainActivityViewModel.startDestination
    val userDetails by mainActivityViewModel.userDetails.collectAsState()

    CustomShoppieeScaffold(
        navController = navController,
        userName = userDetails.name,
        userProfileImage = userDetails.profileImage,
        mainActivityViewModel = mainActivityViewModel
    ) {
        Log.e(TAG, "ShoppieNavGraph: startDestination====> $startDestination", )
        SharedTransitionLayout {
            NavHost(
                navController = navController,
                startDestination = startDestination,

                /*enterTransition = {
                    slideIntoContainer(
                        AnimatedContentTransitionScope.SlideDirection.Left, animationSpec = tween(600)
                    )
                }, exitTransition = {
                    slideOutOfContainer(
                        AnimatedContentTransitionScope.SlideDirection.Left, animationSpec = tween(600)
                    )
                }, popEnterTransition = {
                    slideIntoContainer(
                        AnimatedContentTransitionScope.SlideDirection.Right, animationSpec = tween(600)
                    )
                }, popExitTransition = {
                    slideOutOfContainer(
                        AnimatedContentTransitionScope.SlideDirection.Right, animationSpec = tween(600)
                    )
                }*/) {
                composable<Graphs.OnBoarding> {
                    val onBoardingViewModel: OnBoardingViewModel = koinViewModel()
                    OnboardingScreen(isLoading = onBoardingViewModel.onBoardingState.isLoading,
                        event = onBoardingViewModel::onEvent,
                        navigateToAuth = { navController.navigate(Graphs.Auth) })
                }

                authNavGraph(navController, connectivityObserver)

                homeNavGraph(
                    navController = navController,
                    sharedTransitionScope = this@SharedTransitionLayout,
                )
            }
        }
    }
}

