package com.example.shoppieeclient.presentation.auth.onboarding.components

import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.togetherWith
import androidx.compose.animation.with
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.shoppieeclient.ui.theme.Primary

@Composable
fun CustomButtonOnBoarding(
    text: String, onClick: () -> Unit
) {
    Button(
        onClick = onClick, colors = ButtonDefaults.buttonColors(
            containerColor = Primary, contentColor = Color.White
        ), shape = RoundedCornerShape(24.dp),
        modifier = Modifier.animateContentSize()
    ) {
        AnimatedContent(
            targetState = text,
            transitionSpec = {
                fadeIn() + slideInHorizontally (animationSpec = tween(300),
                    initialOffsetX =  {fullWidth -> fullWidth }) togetherWith
                        fadeOut(animationSpec = tween(300))
            }, label = ""
        ) { targetText ->
            Text(
                text = targetText,
                style = MaterialTheme.typography.labelMedium.copy(
                    fontWeight = FontWeight.Bold
                )

            )
        }

    }

}