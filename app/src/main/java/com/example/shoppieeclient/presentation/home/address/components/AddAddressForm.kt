package com.example.shoppieeclient.presentation.home.address.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
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
    formState: AddressStates,
    onEvent: (AddressEvents) -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
            .imePadding(),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
       CustomAddressTextField(
           value = "",
           label = "Street Address",
           onValueChange = {  },
           modifier = Modifier.fillMaxWidth(),
           imeAction = ImeAction.Next
       )

        CustomAddressTextField(
            value = "",
            label = "City",
            onValueChange = {  },
            modifier = Modifier.fillMaxWidth(),
            imeAction = ImeAction.Next
        )

        Row(modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            CustomAddressTextField(
                value = "",
                label = "State",
                onValueChange = {  },
                modifier = Modifier.weight(1f),
                imeAction = ImeAction.Next
            )

            CustomAddressTextField(
                value = "",
                label = "Zip Code",
                onValueChange = {  },
                modifier = Modifier.weight(1f),
                imeAction = ImeAction.Done
            )
        }
        Button(
            onClick = {
                onEvent
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Save Address")
        }
    }
}

