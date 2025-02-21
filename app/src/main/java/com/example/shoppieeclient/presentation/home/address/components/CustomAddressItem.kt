package com.example.shoppieeclient.presentation.home.address.components

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.combinedClickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.Black
import androidx.compose.ui.graphics.Color.Companion.LightGray
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.shoppieeclient.domain.address.models.AddressModel
import com.example.shoppieeclient.ui.theme.PrimaryBlue

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun AddressItem(
    address: AddressModel,
    isSelected: Boolean,
    isSetSelected: Boolean = false,
    onEditClick: () -> Unit,
    onLongClick: () -> Unit,
    onDeleteClick: () -> Unit,
    onDismissSelection: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Card(
        modifier = modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(8.dp))
            .combinedClickable(onClick = { onDismissSelection() }, onLongClick = { onLongClick() }),
        colors = CardDefaults.cardColors(
            containerColor = if (isSelected) LightGray else Color.White
        ),
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column(verticalArrangement = Arrangement.spacedBy(4.dp)) {
                Text(
                    text = address.streetAddress,
                    overflow = TextOverflow.Ellipsis,
                    style = MaterialTheme.typography.bodyLarge.copy(
                        color = PrimaryBlue, fontWeight = FontWeight.Bold
                    )
                )
                Text(
                    text = address.state,
                    overflow = TextOverflow.Ellipsis,
                    style = MaterialTheme.typography.bodyMedium.copy(color = Black)
                )
                Text(
                    text = address.city,
                    overflow = TextOverflow.Ellipsis,
                    style = MaterialTheme.typography.bodyMedium.copy(color = Black)
                )
                Text(
                    text = address.zipCode,
                    overflow = TextOverflow.Ellipsis,
                    style = MaterialTheme.typography.bodyMedium.copy(color = Black)
                )
            }

            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                if (isSetSelected) {
                    Box(
                        modifier = Modifier
                            .size(24.dp)
                            .clip(CircleShape)
                            .border(
                                width = 8.dp,
                                color = PrimaryBlue,
                                shape = CircleShape
                            )
                    )

                }
                TextButton(
                    onClick = if (isSelected) onDeleteClick else onEditClick
                ) {
                    Text(
                        text = if (isSelected) "Delete" else "Edit",
                        color = if (isSelected) Color.Red else PrimaryBlue
                    )
                }
            }
        }
    }

}


@Preview(showBackground = true)
@Composable
private fun PreviewAddress() {
    AddressItem(address = AddressModel(
        id = "1",
        streetAddress = "123 Main St",
        city = "Something",
        state = "CA",
        zipCode = "12345",
        userId = "sdsd"
    ),
        isSelected = false,
        isSetSelected = true,
        onEditClick = {},
        onLongClick = {},
        onDeleteClick = {},
        onDismissSelection = {}

    )
}