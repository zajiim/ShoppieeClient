package com.example.shoppieeclient.core.di

import com.example.shoppieeclient.data.datamanager.LocalUserManagerImpl
import com.example.shoppieeclient.domain.auth.datamanager.LocalUserManager
import com.example.shoppieeclient.domain.auth.use_cases.onboarding.ReadOnBoardingUseCase
import com.example.shoppieeclient.domain.auth.use_cases.onboarding.SaveOnBoardingUseCase
import com.example.shoppieeclient.domain.auth.use_cases.validations.signup.SignupValidationsUseCase
import com.example.shoppieeclient.domain.auth.use_cases.validations.auth.ValidateConfirmPasswordUseCase
import com.example.shoppieeclient.domain.auth.use_cases.validations.auth.ValidateEmailUseCase
import com.example.shoppieeclient.domain.auth.use_cases.validations.auth.ValidatePasswordUseCase
import com.example.shoppieeclient.domain.auth.use_cases.validations.auth.ValidateUserNameUseCase
import com.example.shoppieeclient.domain.auth.use_cases.validations.signin.SignInValidationsUseCases
import com.example.shoppieeclient.presentation.auth.main.MainActivityViewModel
import com.example.shoppieeclient.presentation.auth.onboarding.OnBoardingViewModel
import com.example.shoppieeclient.presentation.auth.signin.SignInState
import com.example.shoppieeclient.presentation.auth.signin.SignInViewModel
import com.example.shoppieeclient.presentation.auth.signup.SignUpViewModel
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

val appModule = module {
    single<LocalUserManager> { LocalUserManagerImpl(get()) }
    single { SaveOnBoardingUseCase(get()) }
    single { ReadOnBoardingUseCase(get()) }

    single { ValidateUserNameUseCase() }
    single { ValidateEmailUseCase() }
    single { ValidatePasswordUseCase() }
    single { ValidateConfirmPasswordUseCase() }

    single { SignupValidationsUseCase(
        validateUserName = get(),
        validateEmail = get(),
        validatePassword = get(),
        validateConfirmPassword = get()
    ) }

    single { SignInValidationsUseCases(
        validateEmail = get(),
        validatePassword = get()
    ) }


    viewModel<OnBoardingViewModel> { OnBoardingViewModel(get()) }
    viewModel<MainActivityViewModel> { MainActivityViewModel(get()) }
    viewModel<SignUpViewModel> { SignUpViewModel(get()) }
    viewModel<SignInViewModel> { SignInViewModel(get()) }
}