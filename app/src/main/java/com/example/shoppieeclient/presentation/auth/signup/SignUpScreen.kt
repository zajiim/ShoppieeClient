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
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import com.example.shoppieeclient.R
import com.example.shoppieeclient.presentation.auth.components.CustomBackButton
import com.example.shoppieeclient.presentation.auth.components.CustomButton
import com.example.shoppieeclient.presentation.auth.components.CustomSocialMediaButton
import com.example.shoppieeclient.presentation.auth.components.CustomTextButtonQuery
import com.example.shoppieeclient.presentation.auth.components.CustomTextField
import com.example.shoppieeclient.presentation.common.components.CustomAlertBox
import com.example.shoppieeclient.ui.theme.Primary
import org.koin.androidx.compose.koinViewModel

private const val TAG = "SignUpScreen"

@Composable
fun SignUpScreen(
    onSignInClicked: () -> Unit,
    onBackClicked: () -> Unit,
    signUpViewModel: SignUpViewModel = koinViewModel()
) {
    val visiblePasswordIcon = ImageVector.vectorResource(id = R.drawable.ic_visibility_on)
    val inVisiblePasswordIcon = ImageVector.vectorResource(id = R.drawable.ic_visibility_off)

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
                    textValue = signUpViewModel.signUpFormState.userName,
                    onValueChange = { signUpViewModel.onEvent(SignUpEvents.UsernameChanged(it)) },
                    hasError = signUpViewModel.signUpFormState.userNameError != null,
                    hint = "Enter your name",
                    keyboardType = KeyboardType.Text,
                    errorString = signUpViewModel.signUpFormState.userNameError,
                    trailingIcon = null,
                    onTrailingIconClicked = null
                )

                Spacer(modifier = Modifier.height(16.dp))

                CustomTextField(
                    modifier = Modifier.fillMaxWidth(),
                    title = "Email Address",
                    textValue = signUpViewModel.signUpFormState.email,
                    onValueChange = { signUpViewModel.onEvent(SignUpEvents.EmailChanged(it)) },
                    hasError = signUpViewModel.signUpFormState.emailError != null,
                    hint = "Enter your email",
                    keyboardType = KeyboardType.Email,
                    errorString = signUpViewModel.signUpFormState.emailError,
                    trailingIcon = null,
                    onTrailingIconClicked = null
                )


                Spacer(modifier = Modifier.height(16.dp))

                CustomTextField(modifier = Modifier.fillMaxWidth(),
                    title = "Password",
                    textValue = signUpViewModel.signUpFormState.password,
                    onValueChange = { signUpViewModel.onEvent(SignUpEvents.PasswordChanged(it)) },
                    hasError = signUpViewModel.signUpFormState.passwordError != null,
                    hint = "Enter your password",
                    keyboardType = KeyboardType.Password,
                    errorString = signUpViewModel.signUpFormState.passwordError,
                    trailingIcon = if (signUpViewModel.signUpFormState.visiblePassword) visiblePasswordIcon else inVisiblePasswordIcon,
                    visualTransformation = if (signUpViewModel.signUpFormState.visiblePassword) VisualTransformation.None else PasswordVisualTransformation(),
                    onTrailingIconClicked = {
                        signUpViewModel.onEvent(
                            SignUpEvents.VisiblePasswordChanged(
                                !(signUpViewModel.signUpFormState.visiblePassword)
                            )
                        )
                    })

                Spacer(modifier = Modifier.height(16.dp))

                CustomTextField(
                    modifier = Modifier.fillMaxWidth(),
                    title = "Confirm Password",
                    textValue = signUpViewModel.signUpFormState.confirmPassword,
                    onValueChange = { signUpViewModel.onEvent(SignUpEvents.ConfirmPasswordChanged(it)) },
                    hasError = signUpViewModel.signUpFormState.confirmPasswordError != null,
                    hint = "Confirm your password",
                    keyboardType = KeyboardType.Password,
                    errorString = signUpViewModel.signUpFormState.confirmPasswordError,
                    trailingIcon = if (signUpViewModel.signUpFormState.visibleConfirmPassword) visiblePasswordIcon else inVisiblePasswordIcon,
                    visualTransformation = if (signUpViewModel.signUpFormState.visibleConfirmPassword) VisualTransformation.None else PasswordVisualTransformation(),
                    onTrailingIconClicked = {
                        signUpViewModel.onEvent(
                            SignUpEvents.VisibleConfirmPasswordChanged(
                                !(signUpViewModel.signUpFormState.visibleConfirmPassword)
                            )
                        )
                    }
                )


                Spacer(modifier = Modifier.height(24.dp))


                CustomButton(
                    text = "Sign In",
                    backgroundColor = Primary,
                    contentColor = Color.White,
                    onButtonClicked = { signUpViewModel.onEvent(SignUpEvents.Submit) },
                    isLoading = signUpViewModel.signUpFormState.isLoading,
                    enabled = !(signUpViewModel.signUpFormState.isLoading),
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
        if (signUpViewModel.signUpFormState.alertDialog != null) {
            CustomAlertBox(
                onDismiss = {
                    signUpViewModel.onEvent(SignUpEvents.DismissDialog)
                },
                onButtonClick = {
                    if (signUpViewModel.signUpFormState.isSignUpSuccessful)
                        onSignInClicked()
                    else
                        signUpViewModel.onEvent(SignUpEvents.DismissDialog)
                },
                animationRes = if (signUpViewModel.signUpFormState.isSignUpSuccessful) R.raw.success else R.raw.failure,
                message = signUpViewModel.signUpFormState.alertDialog
                    ?: "Unknown error, Please try once again",
                buttonText = signUpViewModel.signUpFormState.alertButtonString!!
            )
        }

    }
}