package com.example.shoppieeclient.data.di

import com.example.shoppieeclient.data.auth.remote.api.ShoppieApiService
import com.example.shoppieeclient.data.auth.repository.ShoppieeRepoImpl
import com.example.shoppieeclient.data.cart.repository.ShoppieCartRepoImpl
import com.example.shoppieeclient.data.home.remote.api.ShoppieeHomeApiService
import com.example.shoppieeclient.data.home.repository.ShoppieeHomeRepoImpl
import com.example.shoppieeclient.domain.auth.repository.ShoppieRepo
import com.example.shoppieeclient.domain.auth.repository.home.ShoppieeHomeRepo
import com.example.shoppieeclient.domain.cart.repository.ShoppieCartRepo
import org.koin.dsl.module

val repositoryModule = module {

    // Provide Repository
    single<ShoppieRepo> { ShoppieeRepoImpl(get()) }
    single<ShoppieeHomeRepo> { ShoppieeHomeRepoImpl(get()) }
    single<ShoppieCartRepo> { ShoppieCartRepoImpl(get()) }
}