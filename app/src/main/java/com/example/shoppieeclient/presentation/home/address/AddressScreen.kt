package com.example.shoppieeclient.presentation.home.address

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.BottomSheetScaffold
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.LightGray
import androidx.compose.ui.unit.dp
import com.example.shoppieeclient.presentation.home.details.components.CustomNavigationTopAppBar
import com.example.shoppieeclient.presentation.home.home.components.AddAddressForm
import com.example.shoppieeclient.ui.theme.BackGroundColor
import org.koin.androidx.compose.koinViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddressScreen(
    modifier: Modifier = Modifier,
    onNavigateClick: () -> Unit,
    addressViewModel: AddressViewModel = koinViewModel()
) {
    val sheetState = rememberModalBottomSheetState()
    val scope = rememberCoroutineScope()
    Box(
        modifier = modifier
            .fillMaxSize()
            .background(BackGroundColor)
    ) {
        Column(modifier = Modifier) {
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
                })

            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(200.dp)
                    .padding(20.dp)
                    .clip(RoundedCornerShape(8.dp))
                    .background(LightGray)
                    .clickable {
                        addressViewModel.onEvent(AddressEvents.AddAddressClicked)
                    },
                contentAlignment = Alignment.Center
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


        if (addressViewModel.addressState.isAddAddressClicked) {
            ModalBottomSheet(
                onDismissRequest = {
                    addressViewModel.onEvent(AddressEvents.DismissBottomSheet)
                },
                sheetState = sheetState
            ) {
                AddAddressForm(
                    formState = addressViewModel.addressState,
                    onEvent = {
//                        addressViewModel.onEvent(AddressEvents.AddAddressSubmit)
                    }
                )
            }
        }
    }
}