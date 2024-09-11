package com.barril.pokedexapp.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.Spacer
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
import androidx.compose.material3.VerticalDivider
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
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.graphics.TileMode
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.imageResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Density
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.barril.pokedexapp.R
import com.barril.pokedexapp.domain.PokemonType
import com.barril.pokedexapp.ui.util.offsetGradientBackground

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
//                    .background(Color.Yellow)
                    .offsetGradientBackground(
                        listOf(grassColor, Color.White),
                        { 1000f }
                    )
            )
            ImageWithShadow (
                ImageBitmap.imageResource(R.drawable.balbasaur),
                contentDescription = null,
//                filterQuality = FilterQuality.None,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .size(300.dp)
                    .align(Alignment.BottomCenter)
            )

            Text(
                text = pokemonName.uppercase(),
                fontSize = 30.sp,
                color = MaterialTheme.colorScheme.onSurface,
                modifier = Modifier
                    .align(Alignment.BottomCenter),
                fontFamily = FontFamily(
                    Font(
                        R.font.roboto_bold,
                    )
                )
            )

            Surface(
                shape = CircleShape,
                color = Color(0xFF3448FF),
                modifier = Modifier
                    .padding(20.dp)
                    .offset(
                        x = 0.dp,
                        y = 60.dp
                    )
                    .size(35.dp)
                    .align(AbsoluteAlignment.CenterRight)
                    .clickable { TODO() }
            ) {
                MaleIcon(
                    contentDescription = null,
                    tint = Color.White,
                    modifier = Modifier
                        .fillMaxSize()
//                        .padding(2.dp)
                )
            }

            Icon(
                if (isFavorite)
                    Icons.Default.Favorite
                else
                    Icons.Default.FavoriteBorder,
                tint = MaterialTheme.colorScheme.onSurface,
                contentDescription = "Botão de favorito", // TODO por isso no res
                modifier = Modifier
                    .padding(20.dp)
                    .size(40.dp)
                    .align(Alignment.TopEnd)
            )

            Text(
                text = pokemonId,
                fontSize = 35.sp,
                color = MaterialTheme.colorScheme.onSurface,
                modifier = Modifier
                    .align(AbsoluteAlignment.CenterLeft)
                    .padding(20.dp)
                    .offset(
                        x = 0.dp,
                        y = 60.dp
                    )
            )

            Column (
                modifier = Modifier
                    .padding(10.dp, 10.dp)
            ) {
                for (type in pokemonTypes) {
                    PokemonTypeIcon(
                        type = type,
                        fontSize = 16.sp,
                        modifier = Modifier.padding(2.dp)
                    )
                }
            }
        }
        Spacer(Modifier.padding(15.dp))
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceEvenly,
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 40.dp)
                .height(50.dp)
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = "3.3 kg",
                    fontWeight = FontWeight.Bold,
                    fontSize = 20.sp,
                )
                Text(
                    text = "Peso",
                    fontSize = 15.sp,
                )
            }
            VerticalDivider(Modifier.padding(20.dp))
            Column(
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Icon(
                    painter = painterResource(R.drawable.play_circle_24px),
                    contentDescription = "Tocar som do Pokémon", // TODO: adicionar ao res
                    modifier = Modifier
                        .size(30.dp)
    //                tint = MaterialTheme.colorScheme.surfaceTint,
                )
                Text(
                    text = "Som",
                    fontSize = 15.sp,
                )
            }
            VerticalDivider(Modifier.padding(20.dp))
            Column(
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = "1.10 m",
                    fontWeight = FontWeight.Bold,
                    fontSize = 20.sp,
                )
                Text(
                    text = "Altura",
                    fontSize = 15.sp,
                )
            }
        }
        HorizontalDivider(modifier.padding(top = 24.dp))

        listOf("HP", "ATTACK", "DEFENSE", "SP. ATK", "SP. DEF", "SPEED", "NATURE", "ABILITY")
        Column(
            Modifier.padding(5.dp)
        ) {
            PokemonOverviewRow(
                modifier = Modifier
//                    .offsetGradientBackground(
//                        listOf(
//                            Color(0xFF838383),
//                            Color.Transparent
//                        ),
//                        { 1000f }
//                    )
            ) {
                Text(
                    text = "HP: <valor>", // TODO
                    fontSize = 24.sp
                )
            }
            HorizontalDivider(modifier
//                .offsetGradientBackground(
//                    listOf(
//                        Color.Blue,
//                        Color.Transparent
//                    ),
//                    { 10000f }
//                )
            )
            PokemonOverviewRow(
                modifier = Modifier
//                    .offsetGradientBackground(
//                        listOf(
//                            Color(0xFFCBC6C6),
//                            Color.Transparent
//                        ),
//                        { 1000f }
//                    )
            ) {
                Text(
                    text = "ATTACK:", // TODO
                    fontSize = 24.sp,
                    fontFamily = FontFamily(
                        Font(
                            R.font.roboto_bold // TODO ver fonte legal
                        )
                    )
                )
                Spacer(Modifier.padding(start = 150.dp))
                Text(
                    text = "10", // TODO
                    fontSize = 24.sp

                )
            }
            PokemonOverviewRow {
                Text(
                    text = "DEF: <valor>", // TODO
                    fontSize = 24.sp
                )
            }
        }
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

@Composable
fun PokemonOverviewRow(
    modifier: Modifier = Modifier,
    content: @Composable (RowScope.() -> Unit),
) {
    Row(
        horizontalArrangement = Arrangement.Start,
        content = content,
        modifier = modifier
            .fillMaxWidth()
            .padding(10.dp)
    )
}

@Preview
@Composable
fun PokemonOverviewPreview() {
    var isFavorite by remember { mutableStateOf(false) }
    PokemonOverview(
        pokemonName = "Bulbasaur",
        pokemonTypes = listOf(PokemonType.GRASS, PokemonType.POISON),
        pokemonDescription = "Alguma coisa",
        pokemonId = "#0",
        isFavorite = isFavorite,
        onFavoriteButtonPressed = {
            isFavorite = !isFavorite
        }
    )
}
