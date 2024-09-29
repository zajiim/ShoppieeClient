package com.example.shoppieeclient.common.di

import com.example.shoppieeclient.data.datamanager.LocalUserManagerImpl
import com.example.shoppieeclient.domain.auth.datamanager.LocalUserManager
import com.example.shoppieeclient.domain.auth.use_cases.ReadOnBoardingUseCase
import com.example.shoppieeclient.domain.auth.use_cases.SaveOnBoardingUseCase
import com.example.shoppieeclient.presentation.auth.main.MainActivityViewModel
import com.example.shoppieeclient.presentation.auth.onboarding.OnBoardingViewModel
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

val appModule = module {
    single<LocalUserManager> { LocalUserManagerImpl(get()) }
    single { SaveOnBoardingUseCase(get()) }
    single { ReadOnBoardingUseCase(get()) }
    viewModel<OnBoardingViewModel> { OnBoardingViewModel(get()) }
    viewModel { MainActivityViewModel(get()) }
}