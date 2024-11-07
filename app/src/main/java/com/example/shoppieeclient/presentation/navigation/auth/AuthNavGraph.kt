package com.example.shoppieeclient.presentation.navigation.auth

import androidx.compose.animation.AnimatedContentTransitionScope
import androidx.compose.animation.core.tween
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.example.shoppieeclient.domain.common.repository.NetworkConnectivityObserver
import com.example.shoppieeclient.presentation.auth.forget_password.ForgotPasswordScreen
import com.example.shoppieeclient.presentation.auth.signin.SignInScreen
import com.example.shoppieeclient.presentation.auth.signup.SignUpScreen
import com.example.shoppieeclient.presentation.navigation.Destination
import com.example.shoppieeclient.presentation.navigation.graphs.Graphs


fun NavGraphBuilder.authNavGraph(
    navController: NavHostController,
    connectivityObserver: NetworkConnectivityObserver
) {
    navigation<Graphs.Auth>(
        startDestination = Destination.SignIn,
    ) {

        composable<Destination.SignIn>(
            enterTransition = {
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
            }
        ) {
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
    }
}