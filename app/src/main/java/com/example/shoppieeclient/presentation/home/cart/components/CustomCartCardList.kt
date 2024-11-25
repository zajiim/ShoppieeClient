package com.example.shoppieeclient.presentation.home.cart.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.paging.compose.LazyPagingItems
import com.example.shoppieeclient.domain.cart.models.CartProductModel

@Composable
fun CustomCartCardList(
    modifier: Modifier = Modifier,
    cartItems: LazyPagingItems<CartProductModel>?,
    onIncrement: (String, String) -> Unit,
    onDecrement: (String) -> Unit,
    onDelete: (String) -> Unit,
    isLoading: Boolean,
    showToast: () -> Unit
    ) {

    LazyColumn(
        modifier = modifier.fillMaxWidth().padding(horizontal = 16.dp),
        verticalArrangement = Arrangement.spacedBy(30.dp)
    ) {
        items(cartItems?.itemCount ?: 0) { cartItem ->
            val cart = cartItems?.get(cartItem)
            if (cart != null) {
                CustomCartCard(
                    cartItem = cart,
                    onIncrement = onIncrement,
                    onDecrement = onDecrement,
                    isLoading = isLoading,
                    onDelete = onDelete,
                    showToast = showToast
                )
            }

        }

    }

}