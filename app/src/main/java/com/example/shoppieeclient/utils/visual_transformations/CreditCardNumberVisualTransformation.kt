package com.example.shoppieeclient.utils.visual_transformations

import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.input.OffsetMapping
import androidx.compose.ui.text.input.TransformedText
import androidx.compose.ui.text.input.VisualTransformation

class CreditCardNumberVisualTransformation: VisualTransformation {
    override fun filter(text: AnnotatedString): TransformedText {
        val formatted = buildString {
            text.text.forEachIndexed { index, char ->
                append(char)
                if ((index + 1) % 4 == 0 && index < text.length - 1) {
                    append(" ")
                }
            }
        }
        return TransformedText(
            AnnotatedString(formatted),
            object: OffsetMapping {
                override fun originalToTransformed(offset: Int): Int {
                    return offset + (offset - 1) / 4
                }

                override fun transformedToOriginal(offset: Int): Int {
                    return offset - offset / 5
                }

            }
        )
    }
}