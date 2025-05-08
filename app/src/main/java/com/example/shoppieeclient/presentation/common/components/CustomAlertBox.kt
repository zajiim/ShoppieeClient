package com.example.shoppieeclient.presentation.common.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.LottieConstants
import com.airbnb.lottie.compose.rememberLottieComposition
import com.example.shoppieeclient.R
import com.example.shoppieeclient.presentation.auth.components.CustomButton
import com.example.shoppieeclient.ui.theme.Primary
import com.example.shoppieeclient.ui.theme.PrimaryBlue

@Composable
fun CustomAlertBox(
    onDismiss: () -> Unit,
    onButtonClick: () -> Unit,
    animationRes: Int,
    message: String,
    buttonText: String,
    isLoading: Boolean = false
) {
    Dialog(onDismissRequest = onDismiss) {
        Surface(
            shape = RoundedCornerShape(24.dp),
            color = Color.White,
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier.padding(16.dp)
            ) {
                val composition by rememberLottieComposition(LottieCompositionSpec.RawRes(animationRes))
                LottieAnimation(
                    composition = composition,
                    iterations = LottieConstants.IterateForever,
                    modifier = Modifier.size(200.dp)
                )

                Spacer(modifier = Modifier.height(16.dp))


                Text(
                    text = message,
                    style = MaterialTheme.typography.bodyLarge,
                    textAlign = TextAlign.Center,
                    modifier = Modifier.padding(horizontal = 16.dp)
                )

                Spacer(modifier = Modifier.height(24.dp))


                CustomButton(
                    modifier = Modifier.fillMaxWidth(),
                    text = buttonText,
                    backgroundColor = PrimaryBlue,
                    contentColor = Color.White,
                    onButtonClicked = { onButtonClick() },
                    isLoading = isLoading
                )
            }
        }
    }
}


@Preview
@Composable
private fun LottiePreview() {
    CustomAlertBox(
        onDismiss = { },
        onButtonClick = {  },
        animationRes = R.raw.success,
        message = "Success go to login",
        buttonText = "Login"
    )
}