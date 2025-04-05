package com.example.shoppieeclient.presentation.home.payment.components

import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import com.example.shoppieeclient.presentation.auth.components.CustomTextField
import com.example.shoppieeclient.presentation.home.payment.PaymentEvents
import com.example.shoppieeclient.presentation.home.payment.PaymentStates
import com.example.shoppieeclient.utils.visual_transformations.CreditCardExpiryVisualTransformation
import com.example.shoppieeclient.utils.visual_transformations.CreditCardNumberVisualTransformation

@Composable
fun PaymentDetailsContent(
    cardState: PaymentStates,
    onEvent: (PaymentEvents) -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 20.dp, vertical = 16.dp)
    ) {
        CustomPaymentCard(
            modifier = Modifier.padding(bottom = 16.dp)
        )

        CustomCardTextField(
            textValue = cardState.nameText,
            onValueChange = { onEvent(PaymentEvents.CardNameChanged(it)) },
            hint = "Card holder name",
            label = "Card holder name"
        )

        CustomCardTextField(
            textValue = cardState.cardNumberText,
            onValueChange = { onEvent(PaymentEvents.CardNumberChanged(it)) },
            hint = "Card number",
            label = "Card number",
            keyboardType = KeyboardType.Number,
            visualTransformation = CreditCardNumberVisualTransformation()
        )

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 16.dp), horizontalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            CustomCardTextField(
                modifier = Modifier.weight(1f),
                textValue = cardState.expiryText,
                onValueChange = { onEvent(PaymentEvents.CardExpiryChanged(it)) },
                hint = "MM/YYYY",
                label = "MM/YYYY",
                keyboardType = KeyboardType.Number,
                visualTransformation = CreditCardExpiryVisualTransformation()
            )

            CustomCardTextField(
                modifier = Modifier
                    .weight(1f)
                    .animateContentSize(),
                textValue = cardState.cvvText,
                onValueChange = { onEvent(PaymentEvents.CardCvvChanged(it)) },
                hint = "Cvv",
                label = "Cvv",
                keyboardType = KeyboardType.Number
            )
        }

        Button(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 32.dp),
            onClick = { onEvent(PaymentEvents.SaveCard) }
        ) {
            Text(text = if (cardState.isEditing) "Update Card" else "Add Card")
        }
    }
}

