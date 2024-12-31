package com.example.shoppieeclient.data.di

import com.example.shoppieeclient.data.auth.remote.api.ShoppieApiService
import com.example.shoppieeclient.data.cart.remote.api.ShoppieCartApiService
import com.example.shoppieeclient.data.common.repository.NetworkConnectivityObserverImpl
import com.example.shoppieeclient.data.datamanager.LocalUserManagerImpl
import com.example.shoppieeclient.data.home.account.remote.api.ShoppieeUserProfileService
import com.example.shoppieeclient.data.home.home.remote.api.ShoppieeHomeApiService
import com.example.shoppieeclient.domain.auth.datamanager.LocalUserManager
import com.example.shoppieeclient.domain.auth.use_cases.auth.siginin.ReadAppTokenUseCase
import com.example.shoppieeclient.domain.common.repository.NetworkConnectivityObserver
import com.example.shoppieeclient.domain.home.account.repository.AccountsCloudinaryRepo
import com.example.shoppieeclient.domain.home.account.repository.ShoppieeUserProfileRepo
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
import org.koin.core.qualifier.named
import org.koin.dsl.module

val networkModule = module {
    //Unauthorized HttpClient
    single(named("UnauthorizedHttpClient")) {
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
    single(named("AuthorizedHttpClient")) {
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

    single { CoroutineScope(SupervisorJob() + Dispatchers.Default) }

    // Provide NetworkConnectivityObserver
    single<NetworkConnectivityObserver> {
        NetworkConnectivityObserverImpl(
            context = get(),
            scope = get()
        )
    }

    // Provide ShoppieApiService
    single { ShoppieApiService(get(named("UnauthorizedHttpClient"))) }
    single { ShoppieeHomeApiService(get(named("UnauthorizedHttpClient")), get(named("AuthorizedHttpClient"))) }
    single { ShoppieCartApiService(get(named("AuthorizedHttpClient"))) }
    single { ShoppieeUserProfileService(get(named("AuthorizedHttpClient"))) }

}