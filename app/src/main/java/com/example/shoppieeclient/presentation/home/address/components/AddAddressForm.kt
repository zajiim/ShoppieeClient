package com.example.shoppieeclient.presentation.home.address.components

import android.util.Log
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
import com.example.shoppieeclient.data.address.remote.dto.AddressRequest
import com.example.shoppieeclient.domain.address.models.AddressModel
import com.example.shoppieeclient.presentation.home.address.AddressEvents

private const val TAG = "AddAddressForm"
@Composable
fun AddAddressForm(
    address: AddressModel?,
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
           value = address?.streetAddress ?: "",
           label = "Street Address",
           onValueChange = { onEvent(AddressEvents.UpdateStreetAddress(it)) },
           modifier = Modifier.fillMaxWidth(),
           imeAction = ImeAction.Next
       )

        CustomAddressTextField(
            value = address?.city ?: "",
            label = "City",
            onValueChange = { onEvent(AddressEvents.UpdateCity(it)) },
            modifier = Modifier.fillMaxWidth(),
            imeAction = ImeAction.Next
        )

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            CustomAddressTextField(
                value = address?.state ?: "",
                label = "State",
                onValueChange = { onEvent(AddressEvents.UpdateState(it)) },
                modifier = Modifier.weight(1f),
                imeAction = ImeAction.Next
            )

            CustomAddressTextField(
                value = address?.zipCode ?: "",
                label = "Zip Code",
                onValueChange = { onEvent(AddressEvents.UpdateZipCode(it)) },
                modifier = Modifier.weight(1f),
                imeAction = ImeAction.Done
            )
        }
        Button(
            onClick = {
                onEvent(AddressEvents.AddAddressSubmit)
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Save Address")
        }
    }
}

