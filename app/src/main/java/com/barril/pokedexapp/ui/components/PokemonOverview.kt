package com.barril.pokedexapp.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.AbsoluteAlignment
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.FilterQuality
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.TileMode
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.imageResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Density
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.barril.pokedexapp.R
import com.barril.pokedexapp.domain.PokemonType

@Composable
fun PokemonOverview(
    pokemonName: String,
    pokemonTypes: List<PokemonType>,
    pokemonDescription: String,
    pokemonId: String,
    isFavorite: Boolean,
    onFavoriteButtonPressed: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier
            .background(MaterialTheme.colorScheme.background)
            .fillMaxSize()
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(350.dp)
        ){
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(200.dp)
                    .background(Color.Yellow)
            )
            Image (
                ImageBitmap.imageResource(R.drawable.balbasaur),
                contentDescription = null,
                filterQuality = FilterQuality.None,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .size(300.dp)
                    .align(Alignment.BottomCenter)
            )

            Text(
                text = pokemonName.uppercase(),
                fontSize = 50.sp,
                color = MaterialTheme.colorScheme.onSurface,
                modifier = Modifier
                    .align(Alignment.BottomCenter),
                fontFamily = FontFamily(
                    Font(
                        R.font.roboto_bold,
                    )
                )
            )

            Icon(
                painter = painterResource(R.drawable.play_circle_24px),
                contentDescription = "Tocar som do Pokémon", // TODO: adicionar ao res
                tint = MaterialTheme.colorScheme.surfaceTint,
                modifier = Modifier
                    .padding(20.dp)
                    .align(AbsoluteAlignment.CenterRight)
                    .offset(
                        x = 0.dp,
                        y = 60.dp
                    )
                    .size(40.dp)
                    .clickable { TODO() }
            )

            Icon(
                if (isFavorite)
                    Icons.Default.Favorite
                else
                    Icons.Default.FavoriteBorder,
                tint = MaterialTheme.colorScheme.surfaceTint,
                contentDescription = "Botão de favorito", // TODO por isso no res
                modifier = Modifier
                    .padding(20.dp)
                    .size(40.dp)
                    .align(Alignment.TopEnd)
            )

            Surface(
                shape = CircleShape,
                modifier = Modifier
                    .align(AbsoluteAlignment.CenterLeft)
                    .padding(20.dp)
                    .offset(
                        x = 0.dp,
                        y = 60.dp
                    )
            ) {
                Text(
                    text = pokemonId,
                    fontSize = 35.sp,
                    color = MaterialTheme.colorScheme.onSurface,
                )
            }

            Column (
                modifier = Modifier
                    .padding(20.dp, 30.dp)
            ) {
                for (type in pokemonTypes) {
                    PokemonTypeIcon(
                        type = PokemonType.POISON,
                        fontSize = 14.sp,
                        modifier = Modifier
                    )
                }
            }
        }
        HorizontalDivider(modifier.padding(0.dp, 24.dp))
        Row(
            modifier = Modifier.padding(20.dp),
            horizontalArrangement = Arrangement.Center
        ) {
            Text(
                text = pokemonDescription,
                color = MaterialTheme.colorScheme.onSurface,
                fontSize = 24.sp
            )
        }
    }
}

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

@Preview
@Composable
fun PokemonOverviewPreview() {
    var isFavorite by remember { mutableStateOf(false) }
    PokemonOverview(
        pokemonName = "Bulbasaur",
        pokemonTypes = listOf(PokemonType.POISON),
        pokemonDescription = "Alguma coisa",
        pokemonId = "#0",
        isFavorite = isFavorite,
        onFavoriteButtonPressed = {
            isFavorite = !isFavorite
        }
    )
}
