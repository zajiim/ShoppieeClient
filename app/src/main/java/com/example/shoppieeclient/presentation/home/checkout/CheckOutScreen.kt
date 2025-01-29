package com.example.shoppieeclient.presentation.home.checkout

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
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.dp
import com.example.shoppieeclient.presentation.home.cart.components.CustomCheckOutCard
import com.example.shoppieeclient.presentation.home.checkout.components.CustomCheckOutDetails
import com.example.shoppieeclient.presentation.home.details.components.CustomNavigationTopAppBar
import com.example.shoppieeclient.ui.theme.BackGroundColor

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CheckOutScreen(
    modifier: Modifier = Modifier,
    onNavigateClick: () -> Unit,
    onAddressRoute: () -> Unit,
    onPaymentRoute: () -> Unit
) {
    val checkoutCardHeight = remember { mutableIntStateOf(0) }
    Box(
        modifier = modifier
            .fillMaxSize()
            .background(BackGroundColor)
    ) {
        Column(modifier = Modifier.padding(bottom = with(LocalDensity.current) { checkoutCardHeight.intValue.toDp() })) {
            CustomNavigationTopAppBar(modifier = Modifier
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
                subtitle = "Add new address",
                onNavigateClick = onAddressRoute
            )

            CustomCheckOutDetails(
                modifier = Modifier,
                title = "Payment Method",
                subtitle = "Add new payment method",
                onNavigateClick = onPaymentRoute
            )
        }
        CustomCheckOutCard(
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .wrapContentSize(Alignment.BottomCenter)
                .onGloballyPositioned { layoutCoordinates ->
                    checkoutCardHeight.intValue = layoutCoordinates.size.height
                },
            subTotal = 1212.1,
            platformFees = 12.2,
            totalCost = 13232.23,
            onCheckOutClicked = {},
            buttonText = "Place Order"
        )
    }
}