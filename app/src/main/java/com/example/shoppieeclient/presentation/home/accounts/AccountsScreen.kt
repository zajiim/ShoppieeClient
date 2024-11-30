package com.example.shoppieeclient.presentation.home.accounts

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.outlined.Edit
import androidx.compose.material.icons.outlined.FavoriteBorder
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
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.example.shoppieeclient.presentation.home.details.components.CustomNavigationTopAppBar
import com.example.shoppieeclient.ui.theme.PrimaryBlue
import com.example.shoppieeclient.R
import com.example.shoppieeclient.presentation.auth.components.CustomButton
import com.example.shoppieeclient.presentation.auth.components.CustomTextField

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AccountsScreen(
    modifier: Modifier = Modifier,
    onNavigateClick: () -> Unit
) {
    Column(modifier = modifier
        .fillMaxSize()
        .padding(horizontal = 20.dp),
    ) {
        CustomNavigationTopAppBar(
            modifier = Modifier
                .fillMaxWidth(),
            title = "Profile",
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
                IconButton(
                    onClick = { },
                    modifier = Modifier
                        .wrapContentSize()
                        .clip(CircleShape)
                        .background(Color.White)
                ) {
                    Icon(
                        imageVector = Icons.Outlined.Edit,
                        contentDescription = "Edit Success",
                        tint = PrimaryBlue
                    )
                }
            }

        )

        Box(modifier = Modifier
            .fillMaxWidth()
        ) {
            AsyncImage(
                model = "https://picsum.photos/200",
                contentDescription = "Profile Image",
                modifier = Modifier
                    .align(Alignment.Center)
                    .size(90.dp)
                    .clip(CircleShape)
            )
            Box(modifier = Modifier.align(Alignment.BottomCenter)
                .offset(y = 16.dp)
                .size(32.dp)
                .clip(CircleShape)
                .background(PrimaryBlue)
                .clickable {

                },
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    painter = painterResource(R.drawable.ic_add_image),
                    contentDescription = "Edit profile image",
                    tint = Color.White,
                    modifier = Modifier.size(20.dp)
                )
            }
        }

        Text(
            modifier = Modifier.fillMaxWidth().padding(top = 24.dp),
            text = "John Doe",
            style = MaterialTheme.typography.bodyLarge.copy(fontWeight = FontWeight.Bold),
            textAlign = TextAlign.Center
        )

        Spacer(modifier = Modifier.height(32.dp))

        CustomTextField(
            modifier = Modifier.fillMaxWidth(),
            title = "Full Name",
            titleTextColor = Color.Black,
            textValue = "John Doe",
            onValueChange = { },
            hasError = false,
            hint = "Full name",
            keyboardType = KeyboardType.Text,
            cursorColor = PrimaryBlue,
            onTrailingIconClicked = {},
        )

        Spacer(modifier = Modifier.height(24.dp))

        CustomTextField(
            modifier = Modifier.fillMaxWidth(),
            title = "Email Address",
            titleTextColor = Color.Black,
            textValue = "Johndoe@gmail.com",
            onValueChange = { },
            hasError = false,
            hint = "Email address",
            keyboardType = KeyboardType.Text,
            cursorColor = PrimaryBlue,
            onTrailingIconClicked = {},
        )

        Spacer(modifier = Modifier.height(24.dp))

        CustomButton(
            modifier = Modifier.fillMaxWidth(),
            text = "Update",
            backgroundColor = PrimaryBlue,
            onButtonClicked = {},
            isLoading = false,
            contentColor = Color.White
        )

    }

}