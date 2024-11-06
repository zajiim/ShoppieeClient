package com.example.shoppieeclient.presentation.di

import androidx.lifecycle.SavedStateHandle
import com.example.shoppieeclient.presentation.auth.forget_password.ForgotPasswordViewModel
import com.example.shoppieeclient.presentation.auth.main.MainActivityViewModel
import com.example.shoppieeclient.presentation.auth.onboarding.OnBoardingViewModel
import com.example.shoppieeclient.presentation.auth.signin.SignInViewModel
import com.example.shoppieeclient.presentation.auth.signup.SignUpViewModel
import com.example.shoppieeclient.presentation.home.details.DetailsViewModel
import com.example.shoppieeclient.presentation.home.home.HomeViewModel
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

val viewmodelModule = module {
    viewModel<OnBoardingViewModel> { OnBoardingViewModel(get()) }
    viewModel<MainActivityViewModel> { MainActivityViewModel(get(), get()) }
    viewModel<SignUpViewModel> { SignUpViewModel(get(), get()) }
    viewModel<SignInViewModel> { SignInViewModel(get(), get(), get()) }
    viewModel<ForgotPasswordViewModel> { ForgotPasswordViewModel(get()) }
    viewModel<HomeViewModel>{ HomeViewModel(getHomeApiUseCase = get()) }
    viewModel<DetailsViewModel>{ (savedStateHandle: SavedStateHandle) ->
        DetailsViewModel(fetchDetailsUseCase = get(), savedStateHandle = savedStateHandle) }

}