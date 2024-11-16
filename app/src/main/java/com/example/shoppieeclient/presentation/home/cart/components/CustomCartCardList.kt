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
    cartItems: LazyPagingItems<CartProductModel>
    ) {

    LazyColumn(
        modifier = modifier.fillMaxWidth().padding(horizontal = 16.dp),
        verticalArrangement = Arrangement.spacedBy(30.dp)
    ) {
        items(cartItems.itemCount) { cartItem ->
            val cart = cartItems[cartItem]
            if (cart != null) {
                CustomCartCard(cartItem = cart)
            }

        }

    }

}