package com.example.shoppieeclient.presentation.common.components


import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.delay
import androidx.compose.runtime.setValue
import androidx.compose.runtime.getValue
import androidx.compose.ui.tooling.preview.Preview
import com.example.shoppieeclient.R
import com.example.shoppieeclient.ui.theme.BackGroundColor

@Composable
fun CustomToast(
    modifier: Modifier = Modifier,
    message: String,
    iconResId: Int? = null,
    backgroundColor: Color = Color.DarkGray,
    textColor: Color = BackGroundColor
) {
    Box(modifier = modifier.fillMaxWidth()) {
        Surface(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp, vertical = 8.dp),
            color = backgroundColor,
            shadowElevation = 4.dp
        ) {
            Row(
                modifier = Modifier.padding(8.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                iconResId?.let {
                    Icon(
                        painter = painterResource(id = iconResId),
                        contentDescription = "Toast Icon",
                        tint = textColor,
                        modifier = Modifier.size(24.dp)
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                }
                Text(
                    text = message,
                    color = textColor,
                    style = MaterialTheme.typography.bodySmall,
                )
            }
        }
    }
}

@Composable
fun ShowCustomToast(
    message: String,
    iconResId: Int? = null
) {
    var isVisible by remember { mutableStateOf(false) }

    LaunchedEffect(Unit) {
        isVisible = true
        delay(3000)
        isVisible = false
    }

    if (isVisible) {
        CustomToast(message = message, iconResId = iconResId)
    }
}

@Preview
@Composable
private fun PreviewToast() {
    ShowCustomToast(
        message = "This is a toast message",
        iconResId = R.drawable.shoppie_logo__2_
    )
}