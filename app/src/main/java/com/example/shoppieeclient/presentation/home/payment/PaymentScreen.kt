package com.example.shoppieeclient.presentation.home.payment

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.outlined.Add
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.shoppieeclient.presentation.home.address.AddressEvents
import com.example.shoppieeclient.presentation.home.details.components.CustomNavigationTopAppBar
import com.example.shoppieeclient.presentation.home.payment.components.CardItem
import com.example.shoppieeclient.presentation.home.payment.components.PaymentDetailsContent
import com.example.shoppieeclient.ui.theme.BackGroundColor
import com.example.shoppieeclient.ui.theme.PrimaryBlue
import org.koin.androidx.compose.koinViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PaymentScreen(
    modifier: Modifier = Modifier,
    onNavigateClick: () -> Unit,
    paymentViewModel: PaymentViewModel = koinViewModel()
) {
    val sheetState = rememberModalBottomSheetState(skipPartiallyExpanded = true)
    val state = paymentViewModel.paymentState


    Box(
        modifier = modifier
            .fillMaxSize()
            .background(BackGroundColor)
    ) {
        Column(modifier = Modifier.fillMaxSize()) {
            CustomNavigationTopAppBar(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp),
                title = "Payment Cards",
                navigationIcon = {
                    IconButton(
                        onClick = onNavigateClick,
                        modifier = Modifier
                            .wrapContentSize()
                            .clip(CircleShape)
                            .background(Color.White)
                    ) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Default.ArrowBack,
                            contentDescription = "Go back"
                        )
                    }
                },
                actions = {
                    if (state.cards.isNotEmpty() == true) {
                        IconButton(
                            onClick = {
                                paymentViewModel.onEvent(PaymentEvents.ShowAddPaymentSheet)
                            },
                            modifier = Modifier
                                .wrapContentSize()
                                .clip(CircleShape)
                                .background(Color.White)
                        ) {
                            Icon(
                                imageVector = Icons.Outlined.Add,
                                contentDescription = "Add Payment",
                                tint = PrimaryBlue
                            )
                        }
                    }
                })
            LazyColumn(
                modifier = Modifier.weight(1f), verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                if (state.cards.isNotEmpty()) {
                    items(state.cards) { cards ->
                        CardItem(card = cards,
                            onClick = {
                            paymentViewModel.onEvent(PaymentEvents.LoadPaymentDetails(cards.id))
                        }, onLongClick = {
                            paymentViewModel.onEvent(PaymentEvents.ShowDeleteDialog(cards))
                        }, onSelectCard = {
                            paymentViewModel.onEvent(PaymentEvents.ShowSelectionDialog(cards))
                        })
                    }
                } else {
                    item {
                        Box(
                            modifier = Modifier
                                .fillMaxSize()
                                .padding(horizontal = 20.dp)
                                .height(160.dp)
                                .clip(RoundedCornerShape(8.dp))
                                .background(Color.LightGray)
                                .clickable {
                                    paymentViewModel.onEvent(PaymentEvents.ShowAddPaymentSheet)
                                }) {
                            Image(
                                modifier = Modifier.align(Alignment.Center),
                                imageVector = Icons.Default.Add,
                                contentDescription = "add item"
                            )
                        }
                    }
                }
            }




            // Delete dialog
            if (state.showDeleteDialog) {
                AlertDialog(
                    onDismissRequest = { paymentViewModel.onEvent(PaymentEvents.DismissDeleteDialog) },
                    title = { Text("Delete Card") },
                    text = {
                        Text("Are you sure you want to delete this card?")
                    },
                    confirmButton = {
                        TextButton(
                            onClick = {
                                state.cardToDeleteId?.let { id ->
                                    paymentViewModel.onEvent(PaymentEvents.DeleteCard(id))
                                }
                            }) {
                            Text("Delete")
                        }
                    },
                    dismissButton = {
                        TextButton(
                            onClick = { paymentViewModel.onEvent(PaymentEvents.DismissDeleteDialog) }) {
                            Text("Cancel")
                        }
                    })
            }


            // Selection confirmation dialog
            if (state.showSelectionDialog) {
                AlertDialog(
                    onDismissRequest = { paymentViewModel.onEvent(PaymentEvents.DismissSelectionDialog) },
                    title = { Text("Set as Default Card") },
                    text = {
                        Text("Do you want to set this card as your default payment method?")
                    },
                    confirmButton = {
                        TextButton(
                            onClick = {
                                paymentViewModel.onEvent(PaymentEvents.ConfirmCardSelection)
                                onNavigateClick()
                            }) {
                            Text("OK")
                        }
                    },
                    dismissButton = {
                        TextButton(
                            onClick = { paymentViewModel.onEvent(PaymentEvents.DismissSelectionDialog) }) {
                            Text("Cancel")
                        }
                    })
            }


            if (state.showBottomSheet) {
                ModalBottomSheet(
                    onDismissRequest = {
                        paymentViewModel.onEvent(PaymentEvents.DismissAddPaymentSheet)
                    }, sheetState = sheetState
                ) {
                    PaymentDetailsContent(
                        cardState = state, onEvent = paymentViewModel::onEvent
                    )
                }
            }

        }


    }

}