package com.example.shoppieeclient.presentation.home.bottom_nav_bar

import android.graphics.Matrix
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Outline
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.graphics.asAndroidPath
import androidx.compose.ui.graphics.asComposePath
import androidx.compose.ui.unit.Density
import androidx.compose.ui.unit.LayoutDirection
import androidx.core.graphics.PathParser

class CustomNavBarShape : Shape {
    override fun createOutline(
        size: Size,
        layoutDirection: LayoutDirection,
        density: Density
    ): Outline {
        return Outline.Generic(
            path = PathParser.createPathFromPathData(
                "M121.66,24.5C66.79,25.7 17.69,11.33 0,4V110H375V4C347.96,23.5 276.87,24 255.34,24C233.81,24 229.81,27 229.81,35.5C229.81,44 234.38,67.18 207.78,70.5C155.71,77 145.54,59.5 144.69,48.5C143.69,35.5 146.2,24.5 121.66,24.5Z"
            ).asComposePath().apply {
                val pathSize = getBounds().size
                val matrix = Matrix()
                matrix.postScale(
                    size.width / pathSize.width,
                    size.height / pathSize.height
                )
                asAndroidPath().transform(matrix)
                val left = getBounds().left
                val right = getBounds().right
                val top = getBounds().top
                val bottom = getBounds().bottom
                translate(Offset(
                    x = -left,
                    y = -top
                ))
            }
        )
    }

}