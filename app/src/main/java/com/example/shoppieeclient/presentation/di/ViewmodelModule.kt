package com.example.shoppieeclient.presentation.di

import androidx.lifecycle.SavedStateHandle
import com.example.shoppieeclient.presentation.auth.forget_password.ForgotPasswordViewModel
import com.example.shoppieeclient.presentation.auth.main.MainActivityViewModel
import com.example.shoppieeclient.presentation.auth.onboarding.OnBoardingViewModel
import com.example.shoppieeclient.presentation.auth.signin.SignInViewModel
import com.example.shoppieeclient.presentation.auth.signup.SignUpViewModel
import com.example.shoppieeclient.presentation.home.accounts.AccountsViewModel
import com.example.shoppieeclient.presentation.home.address.AddressViewModel
import com.example.shoppieeclient.presentation.home.cart.CartViewModel
import com.example.shoppieeclient.presentation.home.checkout.CheckOutViewModel
import com.example.shoppieeclient.presentation.home.details.DetailsViewModel
import com.example.shoppieeclient.presentation.home.home.HomeViewModel
import com.example.shoppieeclient.presentation.home.order.OrdersViewModel
import com.example.shoppieeclient.presentation.home.payment.PaymentViewModel
import com.example.shoppieeclient.presentation.home.profile.ProfileViewModel
import com.example.shoppieeclient.presentation.home.track_order.TrackOrderViewModel
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

val viewmodelModule = module {
    viewModel<OnBoardingViewModel> { OnBoardingViewModel(get()) }
    viewModel<MainActivityViewModel> {
        MainActivityViewModel(
            readOnBoardingUseCase = get(),
            readAppTokenUseCase = get(),
            saveAppTokenUseCase = get(),
            readUserDetailsUseCase = get(),
            signOutUseCase = get()
        )
    }
    viewModel<SignUpViewModel> { SignUpViewModel(get(), get(), get(), get(), get(), get(), get()) }
    viewModel<SignInViewModel> { SignInViewModel(get(), get(), get(), get(), get(), get(), get()) }
    viewModel<ForgotPasswordViewModel> { ForgotPasswordViewModel(get()) }
    viewModel<HomeViewModel> { HomeViewModel(getHomeApiUseCase = get()) }
    viewModel<DetailsViewModel> { (savedStateHandle: SavedStateHandle) ->
        DetailsViewModel(
            fetchDetailsUseCase = get(),
            addToCartUseCase = get(),
            savedStateHandle = savedStateHandle
        )
    }
    viewModel<CartViewModel> {
        CartViewModel(
            getCartUseCase = get(),
            incrementItemUseCase = get(),
            decrementItemUseCase = get(),
            removeItemUseCase = get(),
            getCartTotalUseCase = get()
        )
    }
    viewModel<ProfileViewModel> { ProfileViewModel() }
    viewModel<AccountsViewModel> {
        AccountsViewModel(
            uploadImageUseCase = get(),
            updateProfileDataUseCase = get(),
            getUserDataUseCase = get(),
            saveUserDetailsUseCase = get()
        )
    }
    viewModel<AddressViewModel> {
        AddressViewModel(
            getAddressListUseCase = get(),
            deleteAddressUseCase = get(),
            addAddressUseCase = get(),
            editAddressUseCase = get(),
            selectAddressUseCase = get()
        )
    }
    viewModel<PaymentViewModel> {
        PaymentViewModel(
            getAllCardsUseCase = get(),
            deleteCardByIdUseCase = get(),
            upsertCardUseCase = get(),
            getCardDetailsByIdUseCase = get(),
            setSelectedCardUseCase = get()
        )
    }
    viewModel<CheckOutViewModel> {
        CheckOutViewModel(
            getSelectedCardUseCase = get(),
            getSelectedAddressUseCase = get(),
            getCartTotalUseCase = get(),
            startRPPaymentUseCase = get(),
            createOrderUseCase = get(),
            verifyPaymentUseCase = get()
        )
    }
    viewModel<OrdersViewModel> {
        OrdersViewModel(
            getOrdersUseCase = get()
        )
    }
    viewModel<TrackOrderViewModel> {
        TrackOrderViewModel(
            getTrackOrderDetailsUseCase = get()
        )
    }
}