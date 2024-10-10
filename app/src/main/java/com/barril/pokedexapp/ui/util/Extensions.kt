package com.barril.pokedexapp.ui.util

import androidx.compose.foundation.border
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.graphics.TileMode
import androidx.compose.ui.unit.Density
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

fun Modifier.offsetGradientBackground(
    colors: List<Color>,
    width: Density.() -> Float,
    offset: Density.() -> Float = { 0f }
) = drawBehind {
    val actualOffset = offset()

    drawRect(
        Brush.horizontalGradient(
            colors = colors,
            startX = -actualOffset,
            endX = width() - actualOffset,
            tileMode = TileMode.Mirror
        )
    )
}

fun Modifier.diagonalGradientBorder(
    colors: List<Color>,
    borderSize: Dp = 2.dp,
    shape: Shape
) = border(
    width = borderSize,
    brush = Brush.linearGradient(colors),
    shape = shape
)
