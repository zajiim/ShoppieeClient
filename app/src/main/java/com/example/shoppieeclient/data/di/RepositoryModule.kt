package com.example.shoppieeclient.data.di

import com.example.shoppieeclient.data.address.remote.repository.AddressRepoImpl
import com.example.shoppieeclient.data.auth.repository.ShoppieeRepoImpl
import com.example.shoppieeclient.data.auth.repository.SocialMediaSignInRepoImpl
import com.example.shoppieeclient.data.cart.repository.ShoppieCartRepoImpl
import com.example.shoppieeclient.data.checkout.repository.RazorPayPaymentRepoImpl
import com.example.shoppieeclient.data.home.account.repository.AccountsCloudinaryRepoImpl
import com.example.shoppieeclient.data.home.account.repository.ShoppieeUpdateProfileRepoImpl
import com.example.shoppieeclient.data.home.home.repository.ShoppieeHomeRepoImpl
import com.example.shoppieeclient.data.home.track_order.repository.ShoppieeTrackOrderRepoImpl
import com.example.shoppieeclient.data.order.repository.ShoppieeOrderRepoImpl
import com.example.shoppieeclient.data.payment.repository.PaymentRepoImpl
import com.example.shoppieeclient.domain.address.repository.AddressRepo
import com.example.shoppieeclient.domain.auth.repository.ShoppieRepo
import com.example.shoppieeclient.domain.auth.repository.SocialMediaSignInRepo
import com.example.shoppieeclient.domain.home.home.repository.ShoppieeHomeRepo
import com.example.shoppieeclient.domain.cart.repository.ShoppieCartRepo
import com.example.shoppieeclient.domain.checkout.repository.RazorPayPaymentRepository
import com.example.shoppieeclient.domain.home.account.repository.AccountsCloudinaryRepo
import com.example.shoppieeclient.domain.home.account.repository.ShoppieeUserProfileRepo
import com.example.shoppieeclient.domain.home.track_order.repository.ShoppieeTrackOrderRepo
import com.example.shoppieeclient.domain.order.repository.ShoppieeOrderRepo
import com.example.shoppieeclient.domain.payment.repository.PaymentRepository
import org.koin.dsl.module
import kotlin.math.sin

val repositoryModule = module {

    // Provide Repository
    single<ShoppieRepo> { ShoppieeRepoImpl(get()) }
    single<ShoppieeHomeRepo> { ShoppieeHomeRepoImpl(get()) }
    single<ShoppieCartRepo> { ShoppieCartRepoImpl(get()) }
    single<ShoppieeOrderRepo> { ShoppieeOrderRepoImpl(get()) }
    single<AccountsCloudinaryRepo> { AccountsCloudinaryRepoImpl() }
    single<ShoppieeUserProfileRepo> { ShoppieeUpdateProfileRepoImpl(shoppieeUserProfileService = get(), profileDao = get()) }
    single<SocialMediaSignInRepo> { SocialMediaSignInRepoImpl(credentialManager = get(), callbackManager = get()) }
    single<AddressRepo> { AddressRepoImpl(addressApiService = get()) }
    single<PaymentRepository> { PaymentRepoImpl(paymentDao = get()) }
    single<RazorPayPaymentRepository> { RazorPayPaymentRepoImpl(shoppieCheckoutApiService = get()) }
    single<ShoppieeTrackOrderRepo> { ShoppieeTrackOrderRepoImpl(shoppieeTrackOrderApiService = get()) }
//    single { PaymentHandler(get()) }
}