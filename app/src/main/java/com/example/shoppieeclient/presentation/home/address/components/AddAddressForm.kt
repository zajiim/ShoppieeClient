package com.example.shoppieeclient.presentation.home.address.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import com.example.shoppieeclient.presentation.home.address.AddressEvents
import com.example.shoppieeclient.presentation.home.address.AddressStates


@Composable
fun AddAddressForm(
    modifier: Modifier = Modifier,
    addressStates: AddressStates,
    onEvent: (AddressEvents) -> Unit,
) {

    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 20.dp, vertical = 16.dp)
    ) {

        CustomAddressTextField(
            modifier = Modifier.fillMaxWidth(),
            value = addressStates.streetAddressText,
            label = "Street Address",
            onValueChange = { onEvent(AddressEvents.StreetAddressChanged(it)) },
            imeAction = ImeAction.Next
        )

        CustomAddressTextField(
            modifier = Modifier.fillMaxWidth(),
            value = addressStates.cityText,
            label = "City",
            onValueChange = { onEvent(AddressEvents.CityChanged(it)) },
            imeAction = ImeAction.Next
        )

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            CustomAddressTextField(
                modifier = Modifier.weight(1f),
                value = addressStates.stateText,
                label = "State",
                onValueChange = { onEvent(AddressEvents.StateChanged(it)) },
                imeAction = ImeAction.Next
            )

            CustomAddressTextField(
                modifier = Modifier.weight(1f),
                value = addressStates.zipCodeText,
                label = "Zip Code",
                onValueChange = { onEvent(AddressEvents.ZipCodeChanged(it)) },
                imeAction = ImeAction.Done
            )
        }

        Button(
            modifier = Modifier.fillMaxWidth(), onClick = { onEvent(AddressEvents.SaveAddress) }) {
            Text(
                text = if (addressStates.isEditing) "Update Address" else "Add Address",
                style = MaterialTheme.typography.bodyLarge
            )
        }
    }
}

