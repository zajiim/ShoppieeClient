package com.example.shoppieeclient.utils.visual_transformations

import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.input.OffsetMapping
import androidx.compose.ui.text.input.TransformedText
import androidx.compose.ui.text.input.VisualTransformation

class CreditCardExpiryVisualTransformation: VisualTransformation {
    override fun filter(text: AnnotatedString): TransformedText {
        val formattedText = buildString {
            text.text.forEachIndexed { index, char ->
                append(char)
                if ((index + 1) % 2 == 0 && index < text.length - 1) {
                    append("/")
                }
            }
        }
        return TransformedText(
            AnnotatedString(formattedText),
            object : OffsetMapping {
                override fun originalToTransformed(offset: Int): Int {
                    return offset + (offset - 1) / 2
                }

                override fun transformedToOriginal(offset: Int): Int {
                    return offset - offset / 3
                }

            }
        )
    }
}