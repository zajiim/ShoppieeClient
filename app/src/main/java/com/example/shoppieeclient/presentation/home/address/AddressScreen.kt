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
import androidx.compose.foundation.layout.safeGesturesPadding
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
import androidx.compose.material3.Button
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
import com.example.shoppieeclient.presentation.common.components.CustomAlertBox
import com.example.shoppieeclient.presentation.common.components.CustomLineProgressIndicator
import com.example.shoppieeclient.presentation.home.address.components.AddAddressForm
import com.example.shoppieeclient.presentation.home.address.components.AddressItem
import com.example.shoppieeclient.presentation.home.details.components.CustomNavigationTopAppBar
import com.example.shoppieeclient.ui.theme.BackGroundColor
import com.example.shoppieeclient.ui.theme.PrimaryBlue
import org.koin.androidx.compose.koinViewModel
import com.example.shoppieeclient.R

private const val TAG = "AddressScreen"

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddressScreen(
    modifier: Modifier = Modifier,
    onNavigateClick: () -> Unit,
    addressViewModel: AddressViewModel = koinViewModel(),
    onNavigateBack: () -> Unit
) {
    val sheetState = rememberModalBottomSheetState()
    val state = addressViewModel.addressState
    Box(modifier = modifier
        .fillMaxSize()
        .background(BackGroundColor)
        .clickable(
            interactionSource = remember { MutableInteractionSource() }, indication = null
        ) {
            if (state.selectedForDeletion != null) {
                addressViewModel.onEvent(AddressEvents.UnSelectAddress)
            }
        }) {
        Column(modifier = Modifier.fillMaxSize()) {
            CustomNavigationTopAppBar(modifier = Modifier
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
                    if (state.addresses?.isNotEmpty() == true) {
                        IconButton(
                            onClick = {
                                addressViewModel.onEvent(AddressEvents.AddAddressClicked)
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
            when {
                state.isLoading -> {
                    Box(
                        modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center
                    ) {
                        CustomLineProgressIndicator()
                    }
                }

                state.addresses?.isEmpty() == true -> {
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(200.dp)
                            .padding(20.dp)
                            .clip(RoundedCornerShape(8.dp))
                            .background(LightGray)
                            .clickable {
                                addressViewModel.onEvent(AddressEvents.AddAddressClicked)
                            }, contentAlignment = Alignment.Center
                    ) {
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.Center
                        ) {
                            Icon(
                                imageVector = Icons.Default.Add, contentDescription = "Add address"
                            )
                            Spacer(modifier = Modifier.width(12.dp))
                            Text(text = "Add address")
                        }
                    }
                }

                state.addresses?.isNotEmpty() == true -> {
                    LazyColumn(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 16.dp),
                        verticalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        items(state.addresses) { address ->
                            AddressItem(
                                address = address,
                                isSelected = address == state.selectedForDeletion,
                                onEditClick = {
                                    addressViewModel.onEvent(AddressEvents.EditButtonClicked(address))
                                },
                                onLongClick = {
                                    if (address == state.selectedForDeletion) {
                                        addressViewModel.onEvent(AddressEvents.UnSelectAddress)
                                    } else {
                                        addressViewModel.onEvent(
                                            AddressEvents.LongPressAddress(
                                                address
                                            )
                                        )
                                    }
                                },
                                onDeleteClick = {
                                    addressViewModel.onEvent(AddressEvents.DeleteClicked)
                                },
                                onDismissSelection = {
                                    addressViewModel.onEvent(AddressEvents.UnSelectAddress)
                                },
                                onSelectAddress = {
                                    addressViewModel.onEvent(AddressEvents.SelectAddress(address))
                                }
                            )
                        }
                    }
                }
            }
        }

        if (state.addresses?.any { it.isSelected } == true) {
                Button(
                    onClick = { state.selectedAddress?.id?.let { addressViewModel.onEvent(AddressEvents.ConfirmAddressSelection(addressId = it)) } },
                    modifier = Modifier
                        .fillMaxWidth()
                        .align(Alignment.BottomCenter)
                        .safeGesturesPadding()
                ) {
                    Text("Confirm Selection")
                }
            }
        Log.e(TAG, "database address value: ${state.selectedAddress}")



        if (state.showDeleteConfirmation) {
            AlertDialog(onDismissRequest = { addressViewModel.onEvent(AddressEvents.CancelDelete) },
                title = { Text(text = "Remove address") },
                text = { Text(text = "Are you sure you want to remove this address?") },
                confirmButton = {
                    TextButton(onClick = {
                        state.selectedForDeletion?.let { address ->
                            addressViewModel.onEvent(AddressEvents.ConfirmDelete(address))
                        }
                    }) {
                        Text("Yes")
                    }
                },
                dismissButton = {
                    TextButton(onClick = { addressViewModel.onEvent(AddressEvents.CancelDelete) }) {
                        Text("No")
                    }
                })
        }



        if (state.isAddAddressClicked) {
            ModalBottomSheet(
                onDismissRequest = {
                    addressViewModel.onEvent(AddressEvents.DismissBottomSheet)
                }, sheetState = sheetState
            ) {
                AddAddressForm(
                    address = state.selectedAddress,
                    onEvent = { onEditClickEvents ->
                        addressViewModel.onEvent(onEditClickEvents)
                    },
                    isEditing = state.selectedAddress?.id?.isNotEmpty() == true
                )

            }
        }

        if(state.confirmSelectedAddress) {
            CustomAlertBox(
                buttonText = "Go Back",
                onDismiss = {},
                onButtonClick = {
                    onNavigateBack()
                },
                animationRes = R.raw.success,
                message = "Address added successfully"
            )
        }

    }
}