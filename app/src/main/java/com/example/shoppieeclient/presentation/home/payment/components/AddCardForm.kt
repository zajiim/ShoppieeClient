package com.example.shoppieeclient.presentation.home.payment.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.shoppieeclient.domain.payment.PaymentCardModel
import com.example.shoppieeclient.presentation.auth.components.CustomButton
import com.example.shoppieeclient.presentation.home.address.AddressEvents
import com.example.shoppieeclient.presentation.home.address.components.CustomAddressTextField
import com.example.shoppieeclient.presentation.home.payment.PaymentEvents

@Composable
fun AddCardForm(
    modifier: Modifier = Modifier,
    paymentCardModel: PaymentCardModel?,
    onEvent: (PaymentEvents) -> Unit,
    isEditing: Boolean
) {

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp)
            .imePadding(),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(220.dp)
                .padding(horizontal = 16.dp)
                .clip(RoundedCornerShape(16.dp))
                .background(Color.Black)
        )
        CustomAddressTextField(
            modifier = Modifier.fillMaxWidth(),
            value = paymentCardModel?.cardHolderName ?: "",
            label = "Name",
            onValueChange = {
                onEvent(PaymentEvents.CardHolderNameChanged(it))
            }
        )
        CustomAddressTextField(
            modifier = Modifier.fillMaxWidth(),
            value = paymentCardModel?.cardNumber ?: "",
            label = "Card Number",
            onValueChange = {
                onEvent(PaymentEvents.CardNumberChanged(it))
            }
        )
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            CustomAddressTextField(
                modifier = Modifier.weight(1f),
                value = paymentCardModel?.expirationDate ?: "",
                label = "Expiry",
                onValueChange = {
                    onEvent(PaymentEvents.CardExpiryChanged(it))
                }
            )
            CustomAddressTextField(
                modifier = Modifier.weight(1f),
                value = paymentCardModel?.cvv ?: "",
                label = "CVV",
                onValueChange = {
                    onEvent(PaymentEvents.CardCvvChanged(it))
                }
            )
        }

        Button(
            onClick = {
                if (isEditing) {

                } else {

                }
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(if (isEditing) "Edit Card" else "Save Card")
        }
    }

}