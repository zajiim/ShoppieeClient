package com.example.shoppieeclient.presentation.home.payment.components

import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import com.example.shoppieeclient.domain.payment.PaymentCardModel
import com.example.shoppieeclient.presentation.home.address.components.CustomAddressTextField
import com.example.shoppieeclient.presentation.home.payment.PaymentEvents
import com.example.shoppieeclient.presentation.home.payment.PaymentStates
import com.example.shoppieeclient.utils.visual_transformations.CreditCardExpiryVisualTransformation
import com.example.shoppieeclient.utils.visual_transformations.CreditCardNumberVisualTransformation

private const val TAG = "AddCardForm"

@Composable
fun AddCardForm(
    modifier: Modifier = Modifier,
    paymentCardModel: PaymentCardModel?,
    state: PaymentStates,
    onEvent: (PaymentEvents) -> Unit,
    isEditing: Boolean
) {

//    Column(
//        modifier = modifier
//            .fillMaxSize()
//            .imePadding()
//            .verticalScroll(rememberScrollState())
//            .padding(16.dp),
//        verticalArrangement = Arrangement.spacedBy(12.dp)
//    ) {
//        Box(
//            modifier = Modifier
//                .fillMaxWidth()
//                .height(200.dp)
//                .padding(horizontal = 16.dp)
//                .clip(RoundedCornerShape(16.dp))
//                .background(Color.Black)
//        )
//        CustomAddressTextField(
//            modifier = Modifier.fillMaxWidth(),
//            value = paymentCardModel?.cardHolderName ?: "",
//            label = "Name",
//            onValueChange = {
//                Log.e(TAG, "onchange name called", )
//                onEvent(PaymentEvents.CardHolderNameChanged(it))
//            }
//        )
//        CustomAddressTextField(
//            modifier = Modifier.fillMaxWidth(),
//            value = paymentCardModel?.cardNumber ?: "",
//            label = "Card Number",
//            onValueChange = {
//                onEvent(PaymentEvents.CardNumberChanged(it))
//            }
//        )
//        Row(
//            modifier = Modifier.fillMaxWidth(),
//            verticalAlignment = Alignment.CenterVertically,
//            horizontalArrangement = Arrangement.spacedBy(8.dp)
//        ) {
//            CustomAddressTextField(
//                modifier = Modifier.weight(1f),
//                value = paymentCardModel?.expirationDate ?: "",
//                label = "Expiry",
//                onValueChange = {
//                    onEvent(PaymentEvents.CardExpiryChanged(it))
//                }
//            )
//            CustomAddressTextField(
//                modifier = Modifier.weight(1f),
//                value = paymentCardModel?.cvv ?: "",
//                label = "CVV",
//                onValueChange = {
//                    onEvent(PaymentEvents.CardCvvChanged(it))
//                }
//            )
//        }
//
//        Button(
//            onClick = {
//                if (isEditing) {
//
//                } else {
//
//                }
//            },
//            modifier = Modifier.fillMaxWidth()
//        ) {
//            Text(if (isEditing) "Edit Card" else "Save Card")
//        }
//    }


    LazyColumn(
        modifier = modifier
            .fillMaxSize()
            .padding(horizontal = 20.dp)
    ) {
        item {
            CustomPaymentCard(
                paymentCardModel = paymentCardModel,
                onEvent = onEvent,
                state = state
            )
        }

        item {
            CustomAddressTextField(
                modifier = Modifier.fillMaxWidth(),
                value = paymentCardModel?.cardHolderName ?: "",
                label = "Name",
                onValueChange = {
                    Log.e(TAG, "onchange name called")
                    onEvent(PaymentEvents.CardHolderNameChanged(it))
                },

                )

            CustomAddressTextField(
                modifier = Modifier.fillMaxWidth(),
//                value = paymentCardModel?.cardNumber ?: "",
                value = paymentCardModel?.cardNumber ?: "",
                label = "Card Number",
                onValueChange = {
                    Log.e(TAG, "onchange number called")
                    onEvent(PaymentEvents.CardNumberChanged(it))
                },
                keyboardType = KeyboardType.Number,
                imeAction = ImeAction.Next,
                visualTransformation = CreditCardNumberVisualTransformation()
            )

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                CustomAddressTextField(
                    modifier = Modifier.weight(1f),
                    value = paymentCardModel?.expirationDate ?: "",
                    label = "MM/YY",
                    onValueChange = {
                        onEvent(PaymentEvents.CardExpiryChanged(it))
                    },
                    keyboardType = KeyboardType.Number,
                    imeAction = ImeAction.Next,
                    visualTransformation = CreditCardExpiryVisualTransformation()
                )
                CustomAddressTextField(
                    modifier = Modifier.weight(1f),
                    value = paymentCardModel?.cvv ?: "",
                    label = "CVV",
                    onValueChange = {
                        onEvent(PaymentEvents.CardCvvChanged(it))
                    },
                    keyboardType = KeyboardType.Number,
                    imeAction = ImeAction.Done,
                )
            }
        }

    }

}