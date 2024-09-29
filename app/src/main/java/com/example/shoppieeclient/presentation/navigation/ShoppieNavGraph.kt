package com.example.shoppieeclient.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.shoppieeclient.presentation.auth.main.MainActivityViewModel
import com.example.shoppieeclient.presentation.auth.onboarding.OnBoardingViewModel
import com.example.shoppieeclient.presentation.auth.onboarding.OnboardingScreen
import com.example.shoppieeclient.presentation.auth.signin.SignInScreen
import com.example.shoppieeclient.presentation.auth.signup.SignUpScreen
import com.example.shoppieeclient.presentation.home.HomeScreen
import org.koin.androidx.compose.koinViewModel
import org.koin.androidx.viewmodel.ext.android.getViewModel

@Composable
fun ShoppieNavGraph(
    navController: NavHostController,
    mainActivityViewModel: MainActivityViewModel = koinViewModel()
) {
    val startDestination by mainActivityViewModel.startDestination.collectAsState()
    NavHost(
        navController = navController,
        startDestination = startDestination
    ) {
        composable<Destination.Onboarding> {
            val onBoardingViewModel: OnBoardingViewModel = koinViewModel()
            OnboardingScreen(
                navController = navController, event = onBoardingViewModel::onEvent
            )
        }

        composable<Destination.SignIn> {
            SignInScreen()
        }

        composable<Destination.SignUp> {
            SignUpScreen()
        }
        composable<Destination.Home> {
            HomeScreen()
        }

    }
}