package com.example.shoppieeclient.domain.di

import com.example.shoppieeclient.domain.auth.use_cases.auth.siginin.ReadAppTokenUseCase
import com.example.shoppieeclient.domain.auth.use_cases.auth.siginin.ReadProfileImageUseCase
import com.example.shoppieeclient.domain.auth.use_cases.auth.siginin.ReadUsernameUseCase
import com.example.shoppieeclient.domain.auth.use_cases.auth.siginin.SaveTokenUseCase
import com.example.shoppieeclient.domain.auth.use_cases.auth.siginin.SaveUserDetailsUseCase
import com.example.shoppieeclient.domain.auth.use_cases.auth.siginin.SignInUseCase
import com.example.shoppieeclient.domain.auth.use_cases.auth.signup.SignUpUseCase
import com.example.shoppieeclient.domain.auth.use_cases.home.GetHomeApiUseCase
import com.example.shoppieeclient.domain.auth.use_cases.home.details.GetProductDetailsUseCase
import com.example.shoppieeclient.domain.auth.use_cases.onboarding.ReadOnBoardingUseCase
import com.example.shoppieeclient.domain.auth.use_cases.onboarding.SaveOnBoardingUseCase
import com.example.shoppieeclient.domain.auth.use_cases.validations.auth.ValidateConfirmPasswordUseCase
import com.example.shoppieeclient.domain.auth.use_cases.validations.auth.ValidateEmailUseCase
import com.example.shoppieeclient.domain.auth.use_cases.validations.auth.ValidatePasswordUseCase
import com.example.shoppieeclient.domain.auth.use_cases.validations.auth.ValidateUserNameUseCase
import com.example.shoppieeclient.domain.auth.use_cases.validations.forgot_password.ForgotPasswordValidationUseCase
import com.example.shoppieeclient.domain.auth.use_cases.validations.signin.SignInValidationsUseCases
import com.example.shoppieeclient.domain.auth.use_cases.validations.signup.SignupValidationsUseCase
import org.koin.dsl.module

val useCasesModule = module {
    single { SaveOnBoardingUseCase(get()) }
    single { ReadOnBoardingUseCase(get()) }
    single { SaveTokenUseCase(get()) }
    single { ReadAppTokenUseCase(get()) }
    single { SaveUserDetailsUseCase(get()) }
    single { ReadUsernameUseCase(get()) }
    single { ReadProfileImageUseCase(get()) }

    single { ValidateUserNameUseCase() }
    single { ValidateEmailUseCase() }
    single { ValidatePasswordUseCase() }
    single { ValidateConfirmPasswordUseCase() }
    single {
        SignupValidationsUseCase(
            validateUserName = get(),
            validateEmail = get(),
            validatePassword = get(),
            validateConfirmPassword = get()
        )
    }

    single {
        SignInValidationsUseCases(
            validateEmail = get(), validatePassword = get()
        )
    }

    single {
        ForgotPasswordValidationUseCase(
            validateEmail = get()
        )
    }

    single {
        SignUpUseCase(
            repository = get()
        )
    }

    single {
        SignInUseCase(
            repository = get()
        )
    }

    single {
        GetHomeApiUseCase(
            shoppieeHomeRepo = get()
        )
    }

    single {
        GetProductDetailsUseCase(
            shoppieeHomeRepo = get()
        )
    }
}