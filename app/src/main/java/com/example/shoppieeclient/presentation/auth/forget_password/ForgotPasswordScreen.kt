package com.example.shoppieeclient.presentation.auth.forget_password

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.shoppieeclient.presentation.auth.components.CustomBackButton
import com.example.shoppieeclient.presentation.auth.components.CustomButton
import com.example.shoppieeclient.presentation.auth.components.CustomTextField
import com.example.shoppieeclient.ui.theme.Primary
import org.koin.androidx.compose.koinViewModel

@Composable
fun ForgotPasswordScreen(
    onBackClicked: () -> Unit,
    forgotPasswordViewModel: ForgotPasswordViewModel = koinViewModel()
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .systemBarsPadding()
            .imePadding()
    ) {
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(top = 56.dp),
            contentPadding = PaddingValues(horizontal = 24.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            item {
                Text(
                    text = "Recovery Password",
                    style = MaterialTheme.typography.titleLarge,
                    color = Color.Black
                )
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = "Please Enter Your Email Address To Receive a Verification Code",
                    style = MaterialTheme.typography.bodyLarge,
                    color = Color.DarkGray,
                    textAlign = TextAlign.Center,
                    modifier = Modifier.padding(horizontal = 26.dp)
                )
                Spacer(modifier = Modifier.height(32.dp))


                CustomTextField(
                    modifier = Modifier.fillMaxWidth(),
                    title = "Email Address",
                    textValue = forgotPasswordViewModel.forgotPasswordFormState.email,
                    onValueChange = { forgotPasswordViewModel.onEvent(ForgotPasswordEvents.EmailChanged(it)) },
                    hasError = forgotPasswordViewModel.forgotPasswordFormState.emailError != null,
                    hint = "Enter your email",
                    keyboardType = KeyboardType.Email,
                    errorString = forgotPasswordViewModel.forgotPasswordFormState.emailError,
                    trailingIcon = null,
                    onTrailingIconClicked = null
                )

                Spacer(modifier = Modifier.height(50.dp))



                CustomButton(
                    text = "Continue",
                    backgroundColor = Primary,
                    contentColor = Color.White,
                    onButtonClicked = {},
                    isLoading = false,
                    enabled = true,
                    modifier = Modifier.fillMaxWidth()
                )
                Spacer(modifier = Modifier.height(16.dp))

            }
        }
        CustomBackButton(
            modifier = Modifier
                .padding(top = 16.dp, start = 24.dp)
                .align(alignment = Alignment.TopStart),
            onBackClicked = onBackClicked
        )
    }
}