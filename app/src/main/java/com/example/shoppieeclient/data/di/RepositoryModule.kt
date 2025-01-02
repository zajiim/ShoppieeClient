package com.example.shoppieeclient.data.di

import com.example.shoppieeclient.data.auth.repository.ShoppieeRepoImpl
import com.example.shoppieeclient.data.cart.repository.ShoppieCartRepoImpl
import com.example.shoppieeclient.data.home.account.repository.AccountsCloudinaryRepoImpl
import com.example.shoppieeclient.data.home.account.repository.ShoppieeUpdateProfileRepoImpl
import com.example.shoppieeclient.data.home.home.repository.ShoppieeHomeRepoImpl
import com.example.shoppieeclient.domain.auth.repository.ShoppieRepo
import com.example.shoppieeclient.domain.home.home.repository.ShoppieeHomeRepo
import com.example.shoppieeclient.domain.cart.repository.ShoppieCartRepo
import com.example.shoppieeclient.domain.home.account.repository.AccountsCloudinaryRepo
import com.example.shoppieeclient.domain.home.account.repository.ShoppieeUserProfileRepo
import org.koin.dsl.module

val repositoryModule = module {

    // Provide Repository
    single<ShoppieRepo> { ShoppieeRepoImpl(get()) }
    single<ShoppieeHomeRepo> { ShoppieeHomeRepoImpl(get()) }
    single<ShoppieCartRepo> { ShoppieCartRepoImpl(get()) }
    single<AccountsCloudinaryRepo> { AccountsCloudinaryRepoImpl() }
    single<ShoppieeUserProfileRepo> { ShoppieeUpdateProfileRepoImpl(shoppieeUserProfileService = get(), profileDao = get()) }
}