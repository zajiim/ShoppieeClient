package com.example.shoppieeclient.presentation.navigation

import androidx.compose.animation.AnimatedContentTransitionScope
import androidx.compose.animation.core.tween
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.shoppieeclient.domain.common.repository.NetworkConnectivityObserver
import com.example.shoppieeclient.presentation.auth.forget_password.ForgotPasswordScreen
import com.example.shoppieeclient.presentation.auth.main.MainActivityViewModel
import com.example.shoppieeclient.presentation.auth.onboarding.OnBoardingViewModel
import com.example.shoppieeclient.presentation.auth.onboarding.OnboardingScreen
import com.example.shoppieeclient.presentation.auth.signin.SignInScreen
import com.example.shoppieeclient.presentation.auth.signup.SignUpScreen
import com.example.shoppieeclient.presentation.home.HomeScreen
import org.koin.androidx.compose.koinViewModel
import org.koin.java.KoinJavaComponent.inject

@Composable
fun ShoppieNavGraph(
    navController: NavHostController,
    mainActivityViewModel: MainActivityViewModel = koinViewModel(),
    connectivityObserver: NetworkConnectivityObserver
) {
//    val startDestination by mainActivityViewModel.startDestination.collectAsState()
    val startDestination = mainActivityViewModel.startDestination
    NavHost(navController = navController, startDestination = startDestination, enterTransition = {
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
    }) {
        composable<Destination.Onboarding> {
            val onBoardingViewModel: OnBoardingViewModel = koinViewModel()
            OnboardingScreen(isLoading = onBoardingViewModel.onBoardingState.isLoading,
                event = onBoardingViewModel::onEvent,
                navigateToAuth = { navController.navigate(Destination.SignIn) })
        }

        composable<Destination.SignIn> {
            SignInScreen(
                onForgotPasswordClicked = {
                navController.navigate(Destination.Forgot)
            },
                onSignUpClicked = {
                navController.navigate(Destination.SignUp) {
                    popUpTo(Destination.SignIn) { inclusive = false }
                }
            },
                onSignInSuccessful = {
                    navController.navigate(Destination.Home) {
                        popUpTo(Destination.SignIn) {
                            inclusive = true
                        }
                        launchSingleTop = true
                    }
                },
                connectivityObserver = connectivityObserver
            )
        }

        composable<Destination.SignUp> {
            SignUpScreen(onSignInClicked = {
//                    navController.navigateUp()
                navController.navigate(Destination.SignIn) {
                    launchSingleTop = true
                    popUpTo(Destination.SignUp) { inclusive = true }
                }
            }, onBackClicked = {
                navController.navigateUp()
            })
        }

        composable<Destination.Forgot> {
            ForgotPasswordScreen(onBackClicked = {
                navController.navigateUp()
            })
        }

        composable<Destination.Home> {
            HomeScreen()
        }

    }
}