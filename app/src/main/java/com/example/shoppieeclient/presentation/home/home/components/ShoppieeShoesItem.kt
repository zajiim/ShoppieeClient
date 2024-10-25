package com.example.shoppieeclient.presentation.home.home.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.shoppieeclient.domain.auth.models.home.HomeProductModel
import com.example.shoppieeclient.ui.theme.Primary
import com.example.shoppieeclient.ui.theme.TitleColor

@Composable
fun ShoppieeShoesItem(
    modifier: Modifier = Modifier,
    leadingTitle: String,
    trailingTitle: String,
    shoeItems: List<HomeProductModel>,
    isLoading: Boolean,
//    onItemClick: (String) -> Unit,
) {
    Column(modifier = modifier) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 20.dp, vertical = 8.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = leadingTitle,
                style = MaterialTheme.typography.bodyMedium.copy(color = TitleColor)
            )
            Text(
                text = trailingTitle,
                style = MaterialTheme.typography.bodySmall.copy(color = Primary)
            )
        }
        LazyRow(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(16.dp),
            contentPadding = PaddingValues(horizontal = 20.dp)
        ) {
            if (isLoading) {
                items(3) {
                    ShoeCard(
                        isLoading = true
                    )
                }
            } else {
                items(shoeItems) { shoe ->
                    ShoeCard(
                        shoe = shoe,
                        isLoading = false,
                        onClick = { }

                    )

                }

            }
        }
    }

}