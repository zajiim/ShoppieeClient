package com.example.shoppieeclient.presentation.home.cart

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
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
import androidx.compose.material3.MaterialTheme
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
import androidx.paging.LoadState
import androidx.paging.compose.collectAsLazyPagingItems
import com.example.shoppieeclient.presentation.common.components.CustomLineProgressIndicator
import com.example.shoppieeclient.presentation.home.cart.components.CustomCartCardList
import com.example.shoppieeclient.presentation.home.cart.components.CustomCheckOutCard
import com.example.shoppieeclient.presentation.home.common.EmptyScreen
import com.example.shoppieeclient.presentation.home.details.components.CustomNavigationTopAppBar
import com.example.shoppieeclient.ui.theme.BackGroundColor
import org.koin.androidx.compose.koinViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CartScreen(
    modifier: Modifier = Modifier,
    cartViewModel: CartViewModel = koinViewModel(),
    onNavigateClick: () -> Unit,
) {
    val checkoutCardHeight = remember { mutableIntStateOf(0) }
    val uiState = cartViewModel.uiState
    val cartItems = uiState.cartItems?.collectAsLazyPagingItems()
    val isPagingLoading = cartItems?.loadState?.refresh is LoadState.Loading

    Box(
        modifier = modifier
            .fillMaxSize()
            .background(BackGroundColor)
    ) {

        if (uiState.isLoading || isPagingLoading) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(Color.Black.copy(alpha = 0.3f)),
                contentAlignment = Alignment.Center
            ) {
                CustomLineProgressIndicator(color = MaterialTheme.colorScheme.primary)
            }
        } else if (uiState.error != null) {
            EmptyScreen(
                title = "Error",
                subtitle = uiState.error,
                modifier = Modifier.fillMaxSize()
            )
        } else if (cartItems?.itemCount == 0) {
            EmptyScreen(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp),
                title = "Cart is empty",
                subtitle = "Go shopping"
            )
        } else {
            Column(
                verticalArrangement = Arrangement.spacedBy(12.dp),
                modifier = Modifier.padding(bottom = with(LocalDensity.current) { checkoutCardHeight.value.toDp() })
            ) {
                CustomNavigationTopAppBar(modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp),
                    title = "My Cart",
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
                CustomCartCardList(
                    modifier = Modifier,
                    cartItems = cartItems,
                    onIncrement = { id, size ->
                        cartViewModel.onEvent(CartEvents.IncrementItem(id, size))
                    },
                    onDecrement = { id ->
                        cartViewModel.onEvent(CartEvents.DecrementItem(id))
                    },
                    onDelete = { id ->
                        cartViewModel.onEvent(CartEvents.RemoveCartItem(id))
                    },
                    isLoading = uiState.isItemLoading
                )
            }
            CustomCheckOutCard(
                modifier = Modifier
                    .align(Alignment.BottomCenter)
                    .wrapContentSize(Alignment.BottomCenter)
                    .onGloballyPositioned { layoutCoordinates ->
                        checkoutCardHeight.intValue = layoutCoordinates.size.height
                    }
            )
        }
    }
}
