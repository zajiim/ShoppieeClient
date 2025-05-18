package com.example.shoppieeclient.presentation.home.order

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.dp
import androidx.paging.LoadState
import androidx.paging.compose.collectAsLazyPagingItems
import com.example.shoppieeclient.R
import com.example.shoppieeclient.presentation.common.components.CustomLineProgressIndicator
import com.example.shoppieeclient.presentation.home.common.EmptyScreen
import com.example.shoppieeclient.presentation.home.details.components.CustomNavigationTopAppBar
import com.example.shoppieeclient.presentation.home.order.components.CustomOrderItem
import com.example.shoppieeclient.ui.theme.BackGroundColor
import org.koin.androidx.compose.koinViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun OrderScreen(
    modifier: Modifier = Modifier,
    onNavigateClick: () -> Unit,
    ordersViewModel: OrdersViewModel = koinViewModel()
) {
    val orderCardHeight = remember { mutableIntStateOf(0) }
    val orderUiStates = ordersViewModel.ordersUiStates
    val orderItems = orderUiStates.orders?.collectAsLazyPagingItems()


    Box(
        modifier = modifier
            .fillMaxSize()
            .background(BackGroundColor)
    ) {

        when {
            orderUiStates.isLoading || orderItems?.loadState?.refresh is LoadState.Loading -> {
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(Color.Black.copy(alpha = 0.3f)),
                    contentAlignment = Alignment.Center
                ) {
                    CustomLineProgressIndicator(color = MaterialTheme.colorScheme.primary)
                }
            }

            orderUiStates.error != null -> {
                EmptyScreen(
                    title = "Error",
                    subtitle = orderUiStates.error,
                    modifier = Modifier.fillMaxSize()
                )
            }

            orderItems?.itemCount == 0 -> {
                EmptyScreen(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(16.dp),
                    title = "Cart is empty",
                    subtitle = "Go shopping"
                )
            }

            else -> {
                Column(
                    modifier = Modifier
                        .padding(bottom = with(LocalDensity.current) {
                            orderCardHeight.intValue.toDp()
                        })
                ) {
                    CustomNavigationTopAppBar(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 16.dp),
                        title = "My Orders",
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
                    CustomOrderItem(
                        modifier = Modifier,
                        orderItems = orderItems,
                        onTrackOrderClick = {

                        }
                    )


                }
            }
        }


    }


}