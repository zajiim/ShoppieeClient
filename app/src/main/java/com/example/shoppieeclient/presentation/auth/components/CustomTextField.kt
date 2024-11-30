package com.example.shoppieeclient.presentation.auth.components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.expandVertically
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.shrinkVertically
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import com.example.shoppieeclient.ui.theme.Primary
import com.example.shoppieeclient.ui.theme.PrimaryBlue
import com.example.shoppieeclient.utils.UiText

@Composable
fun CustomTextField(
    modifier: Modifier = Modifier,
    title: String,
    titleTextColor: Color = PrimaryBlue,
    textValue: String,
    onValueChange: (String) -> Unit,
    cursorColor: Color = Primary,
    hint: String,
    keyboardType: KeyboardType = KeyboardType.Ascii,
    visualTransformation: VisualTransformation = VisualTransformation.None,
    hasError: Boolean= false,
    errorString: UiText? = null,
    trailingIcon: ImageVector? = null,
    onTrailingIconClicked: (() -> Unit)?,
) {
    val context = LocalContext.current
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        Text(
            text = title,
            color = titleTextColor,
            style = MaterialTheme.typography.titleSmall
        )

        OutlinedTextField(
            modifier = Modifier.fillMaxWidth(),
            value = textValue,
            onValueChange = onValueChange,
            singleLine = true,
            colors = OutlinedTextFieldDefaults.colors(
                focusedContainerColor = Color.White,
                unfocusedContainerColor = Color.White,
                cursorColor = cursorColor,
                focusedBorderColor = PrimaryBlue,
                unfocusedBorderColor = Color.Transparent
            ),
            shape = RoundedCornerShape(16.dp),
            placeholder = {
                Text(text = hint)
            },
            textStyle = MaterialTheme.typography.bodySmall,
            keyboardOptions = KeyboardOptions(keyboardType = keyboardType),
            visualTransformation = visualTransformation,
            trailingIcon = {
                trailingIcon?.let {  icon ->
                     Icon(
                         imageVector = icon,
                         contentDescription = null,
                         tint = cursorColor,
                         modifier = Modifier.clickable {
                              onTrailingIconClicked?.invoke()
                         }
                     )
                }
            }
        )

        AnimatedVisibility(
            visible = hasError,
            enter = fadeIn() + expandVertically(),
            exit = fadeOut() + shrinkVertically()
        ) {
            Text(
                text = errorString?.asString(context) ?: "",
                color = Color.Red,
                style = MaterialTheme.typography.bodySmall
            )
        }

    }
}