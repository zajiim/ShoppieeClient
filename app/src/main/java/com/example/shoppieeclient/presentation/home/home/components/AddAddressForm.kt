package com.example.shoppieeclient.presentation.home.home.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
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
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        OutlinedTextField(
            value = "",
            onValueChange = {  },
            label = { Text(text = "Street Address") },
            isError = true,
            modifier = Modifier.fillMaxWidth()
        )



        Button(
            onClick = {  },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Save Address")
        }
    }
}

