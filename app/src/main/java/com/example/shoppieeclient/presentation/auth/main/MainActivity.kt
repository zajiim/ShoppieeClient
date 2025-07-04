package com.example.shoppieeclient.presentation.auth.main

import android.os.Build
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.annotation.RequiresApi
import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.navigation.compose.rememberNavController
import com.example.shoppieeclient.domain.common.repository.NetworkConnectivityObserver
import com.example.shoppieeclient.presentation.home.checkout.CheckOutViewModel
import com.example.shoppieeclient.presentation.home.checkout.CheckoutEvents
import com.example.shoppieeclient.presentation.navigation.ShoppieNavGraph
import com.example.shoppieeclient.ui.theme.ShoppieeClientTheme
import com.razorpay.Checkout
import com.razorpay.PaymentData
import com.razorpay.PaymentResultWithDataListener
import org.koin.android.ext.android.inject
import org.koin.androidx.compose.KoinAndroidContext
import org.koin.androidx.viewmodel.ext.android.viewModel

private const val TAG = "MainActivity"

class MainActivity : ComponentActivity(), PaymentResultWithDataListener {
    private val connectivityObserver: NetworkConnectivityObserver by inject()
    private val mainActivityViewModel: MainActivityViewModel by viewModel()

    //    private val paymentHandler: PaymentHandler by inject()
//    private val checkOutViewModel: CheckOutViewModel by viewModel()
    private val sharedCheckOutViewModel: CheckOutViewModel by viewModel()

//    private val checkOutViewModel: CheckOutViewModel by viewModels()

    @RequiresApi(Build.VERSION_CODES.TIRAMISU)
    @OptIn(ExperimentalSharedTransitionApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        installSplashScreen().setKeepOnScreenCondition {
            Log.e(TAG, "onCreate: value ===> ${mainActivityViewModel.splashCondition.value}")
            mainActivityViewModel.splashCondition.value
        }
        enableEdgeToEdge(
//            statusBarStyle = SystemBarStyle.light(
//                scrim = ContextCompat.getColor(this, R.color.primary),
//                darkScrim = ContextCompat.getColor(this, R.color.primary)
//            ),
//            navigationBarStyle = SystemBarStyle.light(
//                scrim = ContextCompat.getColor(this, R.color.primary),
//                darkScrim = ContextCompat.getColor(this, R.color.primary)
//            )
        )
        Checkout.preload(applicationContext)
        setContent {
            KoinAndroidContext {
                ShoppieeClientTheme(darkTheme = false) {
                    Scaffold(
                        modifier = Modifier.fillMaxSize(),
                    ) { innerPadding ->
                        innerPadding
                        val navController = rememberNavController()
                        ShoppieNavGraph(
                            navController = navController,
                            connectivityObserver = connectivityObserver,
                            mainActivityViewModel = mainActivityViewModel,
                            checkOutViewModel = sharedCheckOutViewModel
                        )
                    }
                }
            }
        }
    }

    override fun onPaymentSuccess(paymentId: String?, data: PaymentData?) {
        data?.let {
//            paymentHandler.onPaymentSuccess(paymentId ?: "Unknown error occurred")
            Log.e(TAG, "onPaymentSuccess: ${data.signature}")
            Log.e(TAG, "Before event - OrderID: ${sharedCheckOutViewModel.checkOutState.orderId}, " +
                    "RazorPayID: ${sharedCheckOutViewModel.checkOutState.razorPayOrderId}")

            sharedCheckOutViewModel.onEvent(
                CheckoutEvents.PaymentSuccess(
                    paymentId = it.paymentId,
                    signature = data.signature
                )
            )
//            finish()
        }
    }

    override fun onPaymentError(code: Int, description: String?, data: PaymentData?) {
//        paymentHandler.onPaymentError(code, description ?: "Unknown error occurred")
        Log.e(TAG, "onPaymentError: ")
        sharedCheckOutViewModel.onEvent(
            CheckoutEvents.PaymentError(
                message = description ?: "Payment failed"
            )
        )
//        finish()
    }
}
