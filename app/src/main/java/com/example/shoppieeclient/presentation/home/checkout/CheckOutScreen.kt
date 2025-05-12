package com.example.shoppieeclient.presentation.home.checkout

import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.dp
import com.example.shoppieeclient.presentation.common.components.CustomLineProgressIndicator
import com.example.shoppieeclient.presentation.home.cart.components.CustomCheckOutCard
import com.example.shoppieeclient.presentation.home.checkout.components.CustomCheckOutDetails
import com.example.shoppieeclient.presentation.home.details.components.CustomNavigationTopAppBar
import com.example.shoppieeclient.ui.theme.BackGroundColor
import com.example.shoppieeclient.utils.findActivity
import com.razorpay.PaymentData
import com.razorpay.PaymentResultWithDataListener
import org.koin.androidx.compose.koinViewModel
import org.koin.compose.koinInject

private const val TAG = "CheckOutScreen"

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CheckOutScreen(
    modifier: Modifier = Modifier,
    onNavigateClick: () -> Unit,
    onAddressRoute: () -> Unit,
    onPaymentRoute: () -> Unit,
    viewModel: CheckOutViewModel,
    onPaymentSuccess: () -> Unit
) {
    val checkoutCardHeight = remember { mutableIntStateOf(0) }
    val checkOutState = viewModel.checkOutState
    val context = LocalContext.current
    val activity = context.findActivity()


    if (checkOutState.isLoading) {
        Box(
            modifier = modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            CustomLineProgressIndicator()
        }
    }

    checkOutState.error?.let { errorMsg ->
        Log.e(TAG, "CheckOutScreen: error ===> $errorMsg")
        Toast.makeText(context, errorMsg, Toast.LENGTH_SHORT).show()
    }

    // Handle payment success

    Box(
        modifier = modifier
            .fillMaxSize()
            .background(BackGroundColor)
    ) {
        Column(/*modifier = Modifier.padding(bottom = with(LocalDensity.current) { checkoutCardHeight.intValue.toDp() })*/) {
            CustomNavigationTopAppBar(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp),
                title = "CheckOut",
                navigationIcon = {
                    IconButton(
                        onClick = onNavigateClick,
                        modifier = Modifier
                            .wrapContentSize()
                            .clip(CircleShape)
                            .background(Color.White)
                    ) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Default.ArrowBack,
                            contentDescription = "Go back"
                        )
                    }
                })


            CustomCheckOutDetails(
                modifier = Modifier,
                title = "Delivery Address",
                subtitle = if (!checkOutState.selectedAddress.isNullOrEmpty()) {
                    val address = checkOutState.selectedAddress[0]
                    "${address.streetAddress}, ${address.state}"
                } else {
                    "Add new address"
                },
                onNavigateClick = onAddressRoute
            )

            CustomCheckOutDetails(
                modifier = Modifier,
                title = "Payment Method",
                subtitle = checkOutState.selectedCard?.let {
                    "**** **** **** ${
                        it.cardNumber.takeLast(
                            4
                        )
                    }"
                } ?: "Add new payment card",
                onNavigateClick = onPaymentRoute
            )
        }
        CustomCheckOutCard(
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .wrapContentSize(Alignment.BottomCenter)
            /*.onGloballyPositioned { layoutCoordinates ->
                checkoutCardHeight.intValue = layoutCoordinates.size.height
            }*/,
            subTotal = checkOutState.subTotal,
            platformFees = checkOutState.platformFees,
            totalCost = checkOutState.totalCost,
            onCheckOutClicked = {
                if (checkOutState.selectedAddress.isNullOrEmpty()) {
                    Toast.makeText(context, "Please add a delivery address", Toast.LENGTH_SHORT).show()
                    return@CustomCheckOutCard
                }
                val addressId = checkOutState.selectedAddress[0].id
                val totalAmountToBePaid = checkOutState.totalCost
                val currency = "INR"

                viewModel.onEvent(CheckoutEvents.CreateOrder(
//                    amount = totalAmountToBePaid,
                    amount = 10.0,
                    activity = activity,
                    currency = currency,
                    addressId = addressId.toString(),
                ))
            },
            buttonText = "Place Order"
        )
    }
}