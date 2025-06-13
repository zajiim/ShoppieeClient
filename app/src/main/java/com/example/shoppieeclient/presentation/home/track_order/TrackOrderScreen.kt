package com.example.shoppieeclient.presentation.home.track_order

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBars
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.dp
import com.example.shoppieeclient.presentation.home.details.components.CustomNavigationTopAppBar
import com.example.shoppieeclient.presentation.home.track_order.components.OrderDetailSection
import com.example.shoppieeclient.presentation.home.track_order.components.OrderHeaderSection
import com.example.shoppieeclient.presentation.home.track_order.components.OrderStatusSection
import com.example.shoppieeclient.ui.theme.BackGroundColor
import org.koin.androidx.compose.koinViewModel


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TrackOrderScreen(
    modifier: Modifier = Modifier,
    orderId: String? = null,
    onNavigateClick: () -> Unit,
    viewModel: TrackOrderViewModel = koinViewModel(),
) {
    val orderCardHeight = remember { mutableIntStateOf(0) }
    val bottomBarHeight = WindowInsets.navigationBars.asPaddingValues().calculateBottomPadding()
    val state = viewModel.uiState
    val order = state.trackOrderDetails?.order

    val currentStep = when {
        order?.paymentStatus == "completed" && order.status == "processing" -> 2
        order?.paymentStatus == "completed" && order.status == "shipped" -> 3
        order?.paymentStatus == "completed" && order.status == "delivered" -> 4
        order?.paymentStatus == "pending" -> 1
        else -> 1
    }

    LaunchedEffect(Unit) {
        viewModel.onEvent(TrackOrderEvents.LoadTrackOrderDetails(orderId ?: ""))
    }


    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(BackGroundColor)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(top = 64.dp, bottom = bottomBarHeight)
                .verticalScroll(rememberScrollState())
                .padding(
                    top = 64.dp,
                    bottom = with(LocalDensity.current) { orderCardHeight.intValue.toDp() })
        ) {
            order?.items?.forEach { orderItem ->
                OrderHeaderSection(
                    modifier = modifier
                        .padding(horizontal = 20.dp),
                    item = orderItem
                )
            }
            Spacer(modifier = Modifier.height(20.dp))
            HorizontalDivider(
                thickness = 1.dp,
                color = Color.LightGray,
                modifier = Modifier.padding(horizontal = 20.dp)
            )
            Spacer(modifier = Modifier.height(20.dp))
            OrderDetailSection(
                modifier = modifier.padding(horizontal = 20.dp),
                totalAmount = order?.totalAmount.toString()
            )
            Spacer(modifier = Modifier.height(20.dp))
            HorizontalDivider(
                thickness = 1.dp,
                color = Color.LightGray,
                modifier = Modifier.padding(horizontal = 20.dp)
            )
            Spacer(modifier = Modifier.height(20.dp))
            OrderStatusSection(modifier = modifier.padding(horizontal = 20.dp), currentStep = currentStep)
        }
        CustomNavigationTopAppBar(
            modifier = Modifier
                .fillMaxWidth()
                .background(BackGroundColor)
                .padding(horizontal = 16.dp), title = "Track Orders", navigationIcon = {
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
    }
}