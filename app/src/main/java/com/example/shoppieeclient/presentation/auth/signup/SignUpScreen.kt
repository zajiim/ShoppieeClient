package com.example.shoppieeclient.presentation.auth.signup

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
import androidx.compose.ui.unit.dp
import com.example.shoppieeclient.R
import com.example.shoppieeclient.presentation.auth.components.CustomBackButton
import com.example.shoppieeclient.presentation.auth.components.CustomButton
import com.example.shoppieeclient.presentation.auth.components.CustomSocialMediaButton
import com.example.shoppieeclient.presentation.auth.components.CustomTextButtonQuery
import com.example.shoppieeclient.presentation.auth.components.CustomTextField
import com.example.shoppieeclient.ui.theme.Primary

@Composable
fun SignUpScreen(
    onSignInClicked: () -> Unit, onBackClicked: () -> Unit
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
            verticalArrangement = Arrangement.Center
        ) {
            item {
                Spacer(modifier = Modifier.height(40.dp))
                Text(
                    text = "Create Account",
                    style = MaterialTheme.typography.titleLarge,
                    color = Color.Black
                )
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = "Let\'s Create Account Together",
                    style = MaterialTheme.typography.bodyMedium,
                    color = Color.DarkGray
                )
                Spacer(modifier = Modifier.height(50.dp))


                CustomTextField(
                    modifier = Modifier.fillMaxWidth(),
                    title = "Your name",
                    textValue = "",
                    onValueChange = {},
                    hint = "Enter your name",
                    keyboardType = KeyboardType.Text,
                    errorString = "Invalid name",
                    trailingIcon = null,
                    onTrailingIconClicked = null
                )

                Spacer(modifier = Modifier.height(16.dp))

                CustomTextField(
                    modifier = Modifier.fillMaxWidth(),
                    title = "Email Address",
                    textValue = "",
                    onValueChange = {},
                    hint = "Enter your email",
                    keyboardType = KeyboardType.Email,
                    errorString = "Invalid email",
                    trailingIcon = null,
                    onTrailingIconClicked = null
                )


                Spacer(modifier = Modifier.height(16.dp))

                CustomTextField(
                    modifier = Modifier.fillMaxWidth(),
                    title = "Password",
                    textValue = "",
                    onValueChange = {},
                    hint = "Enter your password",
                    keyboardType = KeyboardType.Password,
                    errorString = "Invalid password",
                    trailingIcon = null,
                    onTrailingIconClicked = null
                )

                Spacer(modifier = Modifier.height(16.dp))

                CustomTextField(
                    modifier = Modifier.fillMaxWidth(),
                    title = "Confirm Password",
                    textValue = "",
                    onValueChange = {},
                    hint = "Confirm your password",
                    keyboardType = KeyboardType.Password,
                    errorString = "Passwords must be same",
                    trailingIcon = null,
                    onTrailingIconClicked = null
                )


                Spacer(modifier = Modifier.height(24.dp))


                CustomButton(
                    text = "Sign In",
                    backgroundColor = Primary,
                    contentColor = Color.White,
                    onButtonClicked = {},
                    isLoading = false,
                    enabled = true,
                    modifier = Modifier.fillMaxWidth()
                )
                Spacer(modifier = Modifier.height(16.dp))

                CustomSocialMediaButton(
                    title = "Sign in with Google",
                    icon = R.drawable.ic_google_logo,
                    onClick = {},
                    modifier = Modifier.fillMaxWidth(),
                )
                Spacer(modifier = Modifier.height(24.dp))
                CustomTextButtonQuery(title = "Already have an Account?",
                    clickableText = "Sign In",
                    onClick = { onSignInClicked() })
                Spacer(modifier = Modifier.height(32.dp))
            }
        }
        CustomBackButton(
            modifier = Modifier
                .padding(top = 16.dp, start = 24.dp)
                .align(alignment = Alignment.TopStart), onBackClicked = onBackClicked
        )
    }
}