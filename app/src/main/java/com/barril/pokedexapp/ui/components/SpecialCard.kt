package com.barril.pokedexapp.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CutCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.blur
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.FilterQuality
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.TileMode
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.imageResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Density
import androidx.compose.ui.unit.DpOffset
import androidx.compose.ui.unit.dp
import com.barril.pokedexapp.R

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

//fun Modifier.diagonalGradientBorder(
//    colors: List<Color>,
//    borderSize: Dp = 2.dp,
//    shape: Shape
//) = border(
//    width = borderSize,
//    brush = Brush.linearGradient(colors),
//    shape = shape
//)

@Composable
fun ImageWithShadow2(
    bitmap: ImageBitmap,
    shadowOffset: DpOffset = DpOffset(3.dp, 3.dp),
    modifier: Modifier
) {
    Box(/*.height(70.dp)*/) {
        // sombra
        Image(
            bitmap = bitmap,
            contentDescription = null,
            contentScale = ContentScale.FillWidth,
            colorFilter = ColorFilter.tint(Color.Black),
            alpha = 0.4f,
            modifier = modifier
                .offset(x =  shadowOffset.x, y = shadowOffset.y)
                .blur(radius = 1.dp)
        )
        // imagem
        Image(
            bitmap = bitmap,
            contentDescription = null,
            contentScale = ContentScale.FillWidth,
            filterQuality = FilterQuality.None,
            modifier = modifier
        )
    }
}

@Preview
@Composable
fun SpecialCard(modifier: Modifier = Modifier) {
    val imageSize = 120.dp
    Box(modifier = Modifier.height(120.dp)) {
        Card(
            shape = CutCornerShape(topEnd = 30.dp),
            elevation = CardDefaults.cardElevation(8.dp),
            modifier = Modifier
                .fillMaxWidth()
                .offsetGradientBackground(
                    colors = listOf(Color.Blue,Color.DarkGray,Color.White),
                    width = { 6f }
                )
                .align(Alignment.Center)
        ) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Column {

                }
                IconButton(
                    onClick = { /* todo */ },
                ) {
                    Icon(
                        Icons.Default.Clear,
                        contentDescription = null,
                        modifier = Modifier
                    )
                }
                IconButton(
                    onClick = { /* todo */ }
                ) {
                    Icon(
                        Icons.Default.FavoriteBorder,
                        contentDescription = null,
                        modifier = Modifier
                    )
                }
            }
        }
        Image(
            bitmap = ImageBitmap.imageResource(R.drawable.big_pokemon),
            contentDescription = null,
            contentScale = ContentScale.FillWidth,
            modifier = Modifier
                .align(Alignment.CenterStart)
                .width(imageSize)
        )
    }

//    BoxWithConstraints {
//        Text("a")
//        Card()
//        Image()
//        PokemonTypeIcon()
//    Icon()
//    }

}