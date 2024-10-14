package com.example.shoppieeclient.presentation.home.bottom_nav_bar

import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Outline
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.unit.Density
import androidx.compose.ui.unit.LayoutDirection

class CustomBottomNavigationShape : Shape {
    override fun createOutline(
        size: Size, layoutDirection: LayoutDirection, density: Density
    ): Outline {
        val width = size.width
        val height = size.height
        val path = Path().apply {


            moveTo(0f, 0f)
            cubicTo(
                width * 0.047f,
                height * 0.069f,
                width * 0.17f,
                height * 0.20f,
                width * 0.324f,
                height * 0.193f
            )
            cubicTo(
                width * 0.389f,
                height * 0.193f,
                width * 0.382f,
                height * 0.297f,
                width * 0.385f,
                height * 0.419f
            )
            cubicTo(
                width * 0.388f,
                height * 0.523f,
                width * 0.415f,
                height * 0.688f,
                width * 0.554f,
                height * 0.627f
            )
            cubicTo(
                width * 0.625f,
                height * 0.596f,
                width * 0.612f,
                height * 0.377f,
                width * 0.612f,
                height * 0.297f
            )
            cubicTo(
                width * 0.612f,
                height * 0.216f,
                width * 0.623f,
                height * 0.188f,
                width * 0.680f,
                height * 0.188f
            )
            cubicTo(
                width * 0.738f, height * 0.188f, width * 0.927f, height * 0.183f, width, 0f
            )
            lineTo(width, height)
            lineTo(0f, height)
            close()

        }


        return Outline.Generic(path)
    }



}