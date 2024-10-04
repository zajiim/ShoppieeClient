package com.example.shoppieeclient.core.di

import com.example.shoppieeclient.data.auth.remote.api.ShoppieApiService
import com.example.shoppieeclient.data.auth.repository.ShoppieeRepoImpl
import com.example.shoppieeclient.data.common.repository.NetworkConnectivityObserverImpl
import com.example.shoppieeclient.data.datamanager.LocalUserManagerImpl
import com.example.shoppieeclient.domain.auth.datamanager.LocalUserManager
import com.example.shoppieeclient.domain.auth.repository.ShoppieRepo
import com.example.shoppieeclient.domain.auth.use_cases.auth.siginin.ReadAppTokenUseCase
import com.example.shoppieeclient.domain.auth.use_cases.auth.siginin.SaveTokenUseCase
import com.example.shoppieeclient.domain.auth.use_cases.auth.siginin.SignInUseCase
import com.example.shoppieeclient.domain.auth.use_cases.auth.signup.SignUpUseCase
import com.example.shoppieeclient.domain.auth.use_cases.onboarding.ReadOnBoardingUseCase
import com.example.shoppieeclient.domain.auth.use_cases.onboarding.SaveOnBoardingUseCase
import com.example.shoppieeclient.domain.auth.use_cases.validations.auth.ValidateConfirmPasswordUseCase
import com.example.shoppieeclient.domain.auth.use_cases.validations.auth.ValidateEmailUseCase
import com.example.shoppieeclient.domain.auth.use_cases.validations.auth.ValidatePasswordUseCase
import com.example.shoppieeclient.domain.auth.use_cases.validations.auth.ValidateUserNameUseCase
import com.example.shoppieeclient.domain.auth.use_cases.validations.forgot_password.ForgotPasswordValidationUseCase
import com.example.shoppieeclient.domain.auth.use_cases.validations.signin.SignInValidationsUseCases
import com.example.shoppieeclient.domain.auth.use_cases.validations.signup.SignupValidationsUseCase
import com.example.shoppieeclient.domain.common.repository.NetworkConnectivityObserver
import com.example.shoppieeclient.presentation.auth.forget_password.ForgotPasswordViewModel
import com.example.shoppieeclient.presentation.auth.main.MainActivityViewModel
import com.example.shoppieeclient.presentation.auth.onboarding.OnBoardingViewModel
import com.example.shoppieeclient.presentation.auth.signin.SignInViewModel
import com.example.shoppieeclient.presentation.auth.signup.SignUpViewModel
import io.ktor.client.HttpClient
import io.ktor.client.engine.cio.CIO
import io.ktor.client.plugins.auth.Auth
import io.ktor.client.plugins.auth.providers.BearerTokens
import io.ktor.client.plugins.auth.providers.bearer
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.logging.LogLevel
import io.ktor.client.plugins.logging.Logger
import io.ktor.client.plugins.logging.Logging
import io.ktor.serialization.kotlinx.json.json
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.serialization.json.Json
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

val appModule = module {


    //Unauthorized HttpClient
    single {
        HttpClient(CIO) {
            install(ContentNegotiation) {
                json(Json {
                    prettyPrint = true
                    isLenient = true
                    ignoreUnknownKeys = true
                })
            }
            install(Logging) {
                logger = object : Logger {
                    override fun log(message: String) {
                        println("Ktor Log: $message")
                    }
                }
                level = LogLevel.ALL
            }
        }
    }

    //Authorized HttpClient
    single {
        HttpClient(CIO) {
            install(ContentNegotiation) {
                json(Json {
                    prettyPrint = true
                    isLenient = true
                    ignoreUnknownKeys = true
                })
            }
            install(Auth) {
                bearer {
                    loadTokens {
                        val token = get<ReadAppTokenUseCase>().invoke().firstOrNull()
                        token?.let {
                            BearerTokens(token, "")
                        }
                    }
                }
            }
            install(Logging) {
                logger = object : Logger {
                    override fun log(message: String) {
                        println("Ktor Log(Authorized): $message")
                    }
                }
                level = LogLevel.ALL
            }
        }
    }

    single<LocalUserManager> { LocalUserManagerImpl(get()) }
    single { SaveOnBoardingUseCase(get()) }
    single { ReadOnBoardingUseCase(get()) }
    single { SaveTokenUseCase(get()) }
    single { ReadAppTokenUseCase(get()) }

    single { ValidateUserNameUseCase() }
    single { ValidateEmailUseCase() }
    single { ValidatePasswordUseCase() }
    single { ValidateConfirmPasswordUseCase() }

    // Network connectivity
    single { CoroutineScope(SupervisorJob() + Dispatchers.Default) }

    // Provide NetworkConnectivityObserver
    single<NetworkConnectivityObserver> {
        NetworkConnectivityObserverImpl(
            context = get(),
            scope = get()
        )
    }

    // Provide ShoppieApiService
    single { ShoppieApiService(get()) }

    // Provide Repository
    single<ShoppieRepo> { ShoppieeRepoImpl(get()) }

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
            validateEmail = get(),
            validatePassword = get()
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



    viewModel<OnBoardingViewModel> { OnBoardingViewModel(get()) }
    viewModel<MainActivityViewModel> { MainActivityViewModel(get(), get()) }
    viewModel<SignUpViewModel> { SignUpViewModel(get(), get()) }
    viewModel<SignInViewModel> { SignInViewModel(get(), get(), get()) }
    viewModel<ForgotPasswordViewModel> { ForgotPasswordViewModel(get()) }
}