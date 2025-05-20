package com.example.shoppieeclient.presentation.home.address

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
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
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.LightGray
import androidx.compose.ui.unit.dp
import com.example.shoppieeclient.presentation.common.components.CustomLineProgressIndicator
import com.example.shoppieeclient.presentation.home.address.components.AddAddressForm
import com.example.shoppieeclient.presentation.home.address.components.CustomAddressItem
import com.example.shoppieeclient.presentation.home.details.components.CustomNavigationTopAppBar
import com.example.shoppieeclient.ui.theme.BackGroundColor
import com.example.shoppieeclient.ui.theme.PrimaryBlue
import org.koin.androidx.compose.koinViewModel

private const val TAG = "AddressScreen"

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddressScreen(
    modifier: Modifier = Modifier,
    onNavigateClick: () -> Unit,
    addressViewModel: AddressViewModel = koinViewModel(),
) {
    val sheetState = rememberModalBottomSheetState()
    val state = addressViewModel.addressState
    Box(
        modifier = modifier
            .fillMaxSize()
            .background(BackGroundColor)
            .clickable(
                interactionSource = remember { MutableInteractionSource() },
                indication = null
            ) {
            }) {
        Column(modifier = Modifier.fillMaxSize()) {
            CustomNavigationTopAppBar(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp),
                title = "Address",
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
                    if (state.addresses.isNotEmpty() == true) {
                        IconButton(
                            onClick = {
                                addressViewModel.onEvent(AddressEvents.ShowAddAddressSheet)
                            },
                            modifier = Modifier
                                .wrapContentSize()
                                .clip(CircleShape)
                                .background(Color.White)
                        ) {
                            Icon(
                                imageVector = Icons.Outlined.Add,
                                contentDescription = "Add Address",
                                tint = PrimaryBlue
                            )
                        }
                    }
                })

            LazyColumn(
                modifier = Modifier.weight(1f),
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                if (state.isLoading) {
                    item {
                        Box(
                            modifier = Modifier.fillMaxSize(),
                            contentAlignment = Alignment.Center
                        ) {
                            CustomLineProgressIndicator()
                        }
                    }
                }

                if (state.addresses.isEmpty()) {
                    item {
                        Box(
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(200.dp)
                                .padding(20.dp)
                                .clip(RoundedCornerShape(8.dp))
                                .background(LightGray)
                                .clickable {
                                    addressViewModel.onEvent(AddressEvents.ShowAddAddressSheet)
                                }, contentAlignment = Alignment.Center
                        ) {
                            Row(
                                modifier = Modifier.fillMaxWidth(),
                                verticalAlignment = Alignment.CenterVertically,
                                horizontalArrangement = Arrangement.Center
                            ) {
                                Icon(
                                    imageVector = Icons.Default.Add,
                                    contentDescription = "Add address"
                                )
                                Spacer(modifier = Modifier.width(12.dp))
                                Text(text = "Add address")
                            }
                        }
                    }
                } else {
                    items(state.addresses) { address ->
                        Log.e(TAG, "address details: ${address.id}")
                        CustomAddressItem(
                            address = address,
                            onClick = {
                                addressViewModel.onEvent(AddressEvents.LoadAddressDetails(address))
                            },
                            onLongClick = {
                                addressViewModel.onEvent(AddressEvents.ShowDeleteDialog(address))
                            },
                            onSelectAddress = {
                                addressViewModel.onEvent(AddressEvents.ShowSelectionDialog(address))
                            }
                        )
                    }
                }
            }
        }


        if (state.showBottomSheet) {
            ModalBottomSheet(
                onDismissRequest = {
                    addressViewModel.onEvent(AddressEvents.DismissAddAddressSheet)
                }, sheetState = sheetState
            ) {
                AddAddressForm(
                    addressStates = state,
                    onEvent = { event ->
                        addressViewModel.onEvent(event)
                    }
                )
            }
        }
    }


    if (state.showDeleteDialog) {
        AlertDialog(
            onDismissRequest = { addressViewModel.onEvent(AddressEvents.DismissDeleteDialog) },
            title = { Text("Delete Address") },
            text = {
                Text("Are you sure you want to delete this address?")
            },
            confirmButton = {
                TextButton(
                    onClick = {
                        state.addressToDeleteId?.let { id ->
                            addressViewModel.onEvent(AddressEvents.DeleteAddress(id))
                        }
                    }) {
                    Text("Delete")
                }
            },
            dismissButton = {
                TextButton(
                    onClick = { addressViewModel.onEvent(AddressEvents.DismissDeleteDialog) }) {
                    Text("Cancel")
                }
            })
    }

    if (state.showSelectionDialog) {
        AlertDialog(
            onDismissRequest = { addressViewModel.onEvent(AddressEvents.DismissSelectionDialog) },
            title = { Text("Set as Default Address") },
            text = {
                Text("Do you want to set this address as your default shipping address?")
            },
            confirmButton = {
                TextButton(
                    onClick = {
                        state.addressToSelect?.let { address ->
                            addressViewModel.onEvent(AddressEvents.ConfirmAddressSelection)
                        }
                    }) {
                    Text("OK")
                }
            },
            dismissButton = {
                TextButton(
                    onClick = { addressViewModel.onEvent(AddressEvents.DismissSelectionDialog) }) {
                    Text("Cancel")
                }
            })
    }

}
