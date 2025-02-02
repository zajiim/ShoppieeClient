package com.example.shoppieeclient.presentation.home.address.components

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color.Companion.Black
import androidx.compose.ui.graphics.Color.Companion.LightGray
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import com.example.shoppieeclient.ui.theme.PrimaryBlue

@Composable
fun CustomAddressTextField(
    modifier: Modifier = Modifier,
    value: String,
    label: String,
    onValueChange: (String) -> Unit,
    imeAction: ImeAction = ImeAction.Default
) {
    OutlinedTextField(
        value = value,
        onValueChange = onValueChange,
        label = { Text(text = label) },
        modifier = modifier,
        shape = RoundedCornerShape(8.dp),
        colors = OutlinedTextFieldDefaults.colors(
            unfocusedContainerColor = LightGray,
            focusedContainerColor = LightGray,
            focusedBorderColor = PrimaryBlue,
            unfocusedBorderColor = LightGray,
            cursorColor = PrimaryBlue,
            focusedTextColor = Black
        ),
        keyboardOptions = KeyboardOptions(
            imeAction = imeAction
        )

    )

}