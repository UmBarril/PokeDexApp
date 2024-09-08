package com.barril.pokedexapp.ui.components

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeJoin
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.sp

@Composable
fun OutlinedText(
    text: String,
    color: Color = MaterialTheme.colorScheme.surface,
    fontWeight: FontWeight = FontWeight.Bold,
    fontFamily: FontFamily? = null,
    outlineColor: Color = Color(0xFF9CB2D0),
    outlineMiter: Float = 10f,
    outlineWidth: Float = 10f,
    fontSize: TextUnit = 86.sp,
    modifier: Modifier = Modifier
) {
    val superLargeStyle = TextStyle(
        fontFamily = fontFamily,
        fontWeight = fontWeight,
        fontSize = fontSize,
        letterSpacing = fontSize/10,
        lineHeight = 20.sp,
        textAlign = TextAlign.Center,
        color = color
    )
    val superLargeStyleOutline = superLargeStyle.copy(
        color = outlineColor,
        drawStyle = Stroke(
            miter = outlineMiter,
            width = outlineWidth,
            join = StrokeJoin.Miter
        ),
    )

    Text(
        text = text,
        style = superLargeStyleOutline,
        modifier = modifier
    )

    Text(
        text = text,
        style = superLargeStyle,
        modifier = modifier
    )
}
