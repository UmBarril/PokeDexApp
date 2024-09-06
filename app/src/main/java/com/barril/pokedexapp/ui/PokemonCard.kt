package com.barril.pokedexapp.ui

import androidx.compose.animation.core.animateDpAsState
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.geometry.Rect
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.FilterQuality
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.Outline
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.graphics.painter.BitmapPainter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.imageResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Density
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.zIndex
import com.barril.pokedexapp.PokemonType
import com.barril.pokedexapp.R
import com.bumptech.glide.integration.compose.GlideImage
import java.util.EnumSet

@Composable
fun PokemonCard(pokemonName: String, pokemonType: EnumSet<PokemonType>, modifier: Modifier = Modifier) {
    var isExpanded by remember { mutableStateOf(true) }
//    val normalIconSize = 24.dp
//    val iconSize = animateDpAsState(targetValue = if (isExpanded) normalIconSize * 4 else 24.dp)
//    val iconOffset = animateDpAsState(targetValue = if (isExpanded) -16.dp else 0.dp)

    val paddingModifier = Modifier.padding(10.dp, 4.dp)
    Box(
        Modifier.padding(0.dp, 8.dp, 0.dp, 0.dp)
    ) {
        val imageBitmap = ImageBitmap.imageResource(R.drawable.balbasaur)
        val imageWidth = with(LocalDensity.current) {
            imageBitmap.width.toDp()
        }
        Image(
            bitmap = imageBitmap,
            contentDescription = null,
            contentScale = ContentScale.Crop,
            filterQuality = FilterQuality.None,
            modifier = paddingModifier
                .offset(x = 28.dp, y = 4.dp)
                .graphicsLayer { scaleX = 5f; scaleY = 5f }
                .zIndex(2f)
        )
        Card(
            shape = MaterialTheme.shapes.large,
            modifier = paddingModifier.fillMaxWidth(),
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Spacer(Modifier.padding(imageWidth + 40.dp, 50.dp, 0.dp, 0.dp))
                Column(
                    modifier = Modifier.padding(4.dp)
                ) {
                    Text(
                        text = pokemonName,
                        color = Color.Black,
                        fontFamily = FontFamily(
                            Font(
                                R.font.roboto_bold
                            )
                        )
                    )
                    for(type in pokemonType) {
                        PokemonTypeIcons.fromPokemonType(type, 12.sp)
                    }
                }

                Spacer(Modifier.weight(1f))

                // TODO: colocar propriedades de acessibilidade
                Box(
                    Modifier.background(Color.Green)
                ) {
                    Icon(
                        imageVector = Icons.Default.Star,
                        contentDescription = stringResource(R.string.favorites_button_description),
                        tint = Color.Yellow,
                        modifier = Modifier
                            .padding(20.dp, 0.dp)
                            .clickable { },
                    )
                }
            }
        }
    }
}

class RectangleShape : Shape {
    override fun createOutline(
        size: Size,
        layoutDirection: LayoutDirection,
        density: Density
    ): Outline {
        return Outline.Rectangle(Rect(10f, 10f, 10f, 10f))
    }
}

@Preview
@Composable
fun PokemonCardPreview() {
    PokemonCard("Bulbasaur", EnumSet.of(PokemonType.GRASS))
}
