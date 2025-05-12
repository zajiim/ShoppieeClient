package com.example.shoppieeclient.presentation.home.profile

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowRight
import androidx.compose.material.icons.filled.KeyboardArrowRight
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.shoppieeclient.presentation.home.details.components.CustomNavigationTopAppBar
import com.example.shoppieeclient.R
import com.example.shoppieeclient.presentation.home.profile.components.CustomAppSetting
import com.example.shoppieeclient.presentation.home.profile.components.CustomSections
import org.koin.androidx.compose.koinViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProfileScreen(
    modifier: Modifier = Modifier,
    profileViewModel: ProfileViewModel = koinViewModel(),
    onNavigateClick: () -> Unit,
    onProfileSettingsClick: () -> Unit,
    onShippingAddressClick: () -> Unit,
    onPaymentInfoClick: () -> Unit,
    onDeleteAccountClick: () -> Unit,
    onOrderClick: () -> Unit,
 ) {

    val state = profileViewModel.profileStates

    val sections = listOf(
        ProfileSectionItem(
            icon = painterResource(R.drawable.ic_profile),
            title = "Profile Settings",
            onClick = onProfileSettingsClick,
        ),
        ProfileSectionItem(
            icon = painterResource(R.drawable.ic_shopping),
            title = "Shipping Address",
            onClick = onShippingAddressClick
        ),
        ProfileSectionItem(
            icon = painterResource(R.drawable.ic_payment),
            title = "Payment Info",
            onClick = onPaymentInfoClick
        ),
        ProfileSectionItem(
            icon = painterResource(R.drawable.ic_delete_account),
            title = "Delete Account",
            onClick = onDeleteAccountClick
        ),
        ProfileSectionItem(
            icon = painterResource(R.drawable.ic_order),
            title = "Orders",
            onClick = onOrderClick
        )
    )
    Column(
        modifier = modifier.fillMaxSize()
            .padding(horizontal = 20.dp),
    ) {
        CustomNavigationTopAppBar(
            modifier = Modifier
                .fillMaxWidth(),
            title = "Accounts & Settings",
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
            }
        )
        Spacer(modifier = Modifier.height(24.dp))
        Text(text = "Account", style = MaterialTheme.typography.titleMedium)
        Spacer(modifier = Modifier.height(24.dp))

        sections.forEach { item ->
            CustomSections(
                icon = item.icon,
                title = item.title,
                onClick = item.onClick,
            )
        }

        Spacer(modifier = Modifier.height(24.dp))
        Text(text = "App Settings", style = MaterialTheme.typography.titleMedium)
        Spacer(modifier = Modifier.height(24.dp))
        CustomAppSetting(
            title = "Enable Biometric",
            isChecked = state.isBiometricEnabled,
            onCheckedChange = {
                profileViewModel.onEvent(ProfileEvents.ToggleBiometric(it))
            }
        )
        Spacer(modifier = Modifier.height(24.dp))

        CustomAppSetting(
            title = "Enable Push Notification",
            isChecked = state.isPushNotificationEnabled,
            onCheckedChange = {
                profileViewModel.onEvent(ProfileEvents.TogglePushNotification(it))
            }
        )
        Spacer(modifier = Modifier.height(24.dp))

        CustomAppSetting(
            title = "Enable Location Services",
            isChecked = state.isLocationServiceEnabled,
            onCheckedChange = {
                profileViewModel.onEvent(ProfileEvents.ToggleLocationService(it))
            }
        )
        Spacer(modifier = Modifier.height(24.dp))

        CustomAppSetting(
            title = "Dark Mode",
            isChecked = state.isDarkModeEnabled,
            onCheckedChange = {
                profileViewModel.onEvent(ProfileEvents.ToggleDarkMode(it))
            }
        )
        Spacer(modifier = Modifier.height(24.dp))


    }


}

data class ProfileSectionItem(
    val icon: Painter,
    val title: String,
    val onClick: () -> Unit,
)