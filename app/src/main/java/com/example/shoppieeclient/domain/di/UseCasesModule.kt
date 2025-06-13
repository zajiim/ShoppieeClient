package com.example.shoppieeclient.domain.di

import com.example.shoppieeclient.domain.address.use_cases.AddAddressUseCase
import com.example.shoppieeclient.domain.address.use_cases.DeleteAddressUseCase
import com.example.shoppieeclient.domain.address.use_cases.EditAddressUseCase
import com.example.shoppieeclient.domain.address.use_cases.GetAddressListUseCase
import com.example.shoppieeclient.domain.address.use_cases.GetSelectedAddressUseCase
import com.example.shoppieeclient.domain.address.use_cases.SelectAddressUseCase
import com.example.shoppieeclient.domain.auth.use_cases.auth.siginin.OAuthSignInUseCase
import com.example.shoppieeclient.domain.auth.use_cases.auth.siginin.ReadAppTokenUseCase
import com.example.shoppieeclient.domain.auth.use_cases.auth.siginin.ReadUserDetailsUseCase
import com.example.shoppieeclient.domain.auth.use_cases.auth.siginin.SaveTokenUseCase
import com.example.shoppieeclient.domain.auth.use_cases.auth.siginin.SaveUserDetailsUseCase
import com.example.shoppieeclient.domain.auth.use_cases.auth.siginin.SignInUseCase
import com.example.shoppieeclient.domain.auth.use_cases.auth.siginin.SignInWithFacebookUseCase
import com.example.shoppieeclient.domain.auth.use_cases.auth.siginin.SignInWithGoogleUseCase
import com.example.shoppieeclient.domain.auth.use_cases.auth.signout.SignOutUseCase
import com.example.shoppieeclient.domain.auth.use_cases.auth.signup.SignUpUseCase
import com.example.shoppieeclient.domain.auth.use_cases.home.GetHomeApiUseCase
import com.example.shoppieeclient.domain.auth.use_cases.home.details.AddToCartUseCase
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
import com.example.shoppieeclient.domain.cart.use_cases.DecrementItemUseCase
import com.example.shoppieeclient.domain.cart.use_cases.GetCartTotalUseCase
import com.example.shoppieeclient.domain.cart.use_cases.GetCartUseCase
import com.example.shoppieeclient.domain.cart.use_cases.IncrementItemUseCase
import com.example.shoppieeclient.domain.cart.use_cases.RemoveItemUseCase
import com.example.shoppieeclient.domain.checkout.use_cases.CreateOrderUseCase
import com.example.shoppieeclient.domain.checkout.use_cases.PaymentVerificationUseCase
import com.example.shoppieeclient.domain.checkout.use_cases.StartRPPaymentUseCase
import com.example.shoppieeclient.domain.home.account.use_cases.GetProfileDataUseCase
import com.example.shoppieeclient.domain.home.account.use_cases.UpdateProfileDataUseCase
import com.example.shoppieeclient.domain.home.account.use_cases.UploadImageUseCase
import com.example.shoppieeclient.domain.home.track_order.use_cases.GetTrackOrderDetailsUseCase
import com.example.shoppieeclient.domain.order.use_case.GetOrdersUseCase
import com.example.shoppieeclient.domain.payment.use_cases.DeleteCardByIdUseCase
import com.example.shoppieeclient.domain.payment.use_cases.GetAllCardsUseCase
import com.example.shoppieeclient.domain.payment.use_cases.GetCardByIdUseCase
import com.example.shoppieeclient.domain.payment.use_cases.GetSelectedCardUseCase
import com.example.shoppieeclient.domain.payment.use_cases.SetSelectedCardUseCase
import com.example.shoppieeclient.domain.payment.use_cases.UpsertCardUseCase
import org.koin.dsl.module
import kotlin.math.sin

val useCasesModule = module {
    single { SaveOnBoardingUseCase(get()) }
    single { ReadOnBoardingUseCase(get()) }
    single { SaveTokenUseCase(get()) }
    single { ReadAppTokenUseCase(get()) }
    single { SaveUserDetailsUseCase(get()) }
    single { ReadUserDetailsUseCase(get()) }

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
        OAuthSignInUseCase(
            repository = get()
        )
    }

    single {
        SignInWithFacebookUseCase(
            repository = get()
        )
    }

    single {
        SignInWithGoogleUseCase(
            socialMediaSignInRepo = get()
        )
    }

    single {
        SignOutUseCase(
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
    single {
        AddToCartUseCase(
            shoppieeHomeRepo = get()
        )
    }
    single {
        GetCartUseCase(
            shoppieCartRepo = get()
        )
    }
    single {
        IncrementItemUseCase(
            shoppieCartRepo = get()
        )
    }
    single {
        DecrementItemUseCase(
            shoppieCartRepo = get()
        )
    }
    single {
        RemoveItemUseCase(
            shoppieCartRepo = get()
        )
    }

    single {
        GetCartTotalUseCase(
            shoppieCartRepo = get()
        )
    }

    single {
        UploadImageUseCase(
            accountsCloudinaryRepo = get()
        )
    }
    single {
        UpdateProfileDataUseCase(
            shoppieeUserProfileRepo = get()
        )
    }

    single {
        GetProfileDataUseCase(
            shoppieeUserProfileRepo = get()
        )
    }

    single {
        GetAddressListUseCase(
            addressRepo = get()
        )
    }

    single {
        DeleteAddressUseCase(
            addressRepo = get()
        )
    }

    single {
        AddAddressUseCase(
            addressRepo = get()
        )
    }

    single {
        EditAddressUseCase(
            addressRepo = get()
        )
    }

    single {
        SelectAddressUseCase(
            addressRepo = get()
        )
    }


    single {
        GetCardByIdUseCase(
            paymentRepository = get()
        )
    }

    single {
        GetAllCardsUseCase(
            paymentRepository = get()
        )
    }

    single {
        DeleteCardByIdUseCase(
            paymentRepository = get()
        )
    }

    single {
        UpsertCardUseCase(
            paymentRepository = get()
        )
    }

    single {
        SetSelectedCardUseCase(
            paymentRepository = get()
        )
    }

    single {
        GetSelectedCardUseCase(
            paymentRepository = get()
        )
    }

    single {
        GetSelectedAddressUseCase(
            addressRepo = get()
        )
    }


    single {
        StartRPPaymentUseCase(get())
    }

    single {
        CreateOrderUseCase(get())
    }

    single {
        PaymentVerificationUseCase(get())
    }

    single {
        GetOrdersUseCase(
            shoppieeOrderRepo = get()
        )
    }

    single {
        GetTrackOrderDetailsUseCase(
            shoppieeTrackOrderRepo = get()
        )
    }

}