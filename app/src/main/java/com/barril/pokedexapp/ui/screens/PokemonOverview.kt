package com.barril.pokedexapp.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.VerticalDivider
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.AbsoluteAlignment
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.barril.pokedexapp.R
import com.barril.pokedexapp.domain.Pokemon
import com.barril.pokedexapp.domain.PokemonGender
import com.barril.pokedexapp.ui.components.FemaleIconWithBackground
import com.barril.pokedexapp.ui.components.GlideImageWithShadow
import com.barril.pokedexapp.ui.components.MaleIconWithBackground
import com.barril.pokedexapp.ui.components.PokemonTypeIcon
import com.barril.pokedexapp.ui.theme.toColor
import com.barril.pokedexapp.ui.util.offsetGradientBackground

/**
 * Mostra um overview dos pokémons
 */
@Composable
fun PokemonOverviewScreen(
    pokemon: Pokemon,
    onFavoriteButtonPressed: (Pokemon) -> Unit,
    modifier: Modifier = Modifier,
) {
    var gender by remember { mutableStateOf(pokemon.selectedGender) }
    var isFavorite by remember { mutableStateOf(pokemon.isFavorite) }

    Column(
        modifier = modifier
            .background(MaterialTheme.colorScheme.background)
            .fillMaxSize()
    ) {
        SuperiorPokemonOverView(
            pokemon = pokemon, // isso está horrível com essa repetição de campos... melhorar depois
            gender = gender,
            isFavorite = isFavorite,
            onFavoriteButtonPressed = { pokemon ->
                onFavoriteButtonPressed(pokemon)
                isFavorite = !isFavorite
            },
            onGenderButtonClicked = {
                gender = if (gender == PokemonGender.MALE) {
                    PokemonGender.FEMALE
                } else {
                    PokemonGender.MALE
                }
            }
        )

        HorizontalDivider(modifier.padding(top = 24.dp))

        StatsOverview(pokemon)
    }
}

@Composable
fun SuperiorPokemonOverView(
    pokemon: Pokemon,
    gender: PokemonGender, // ignorando o genero do pokémon e usando apenas esse
    isFavorite: Boolean,
    onFavoriteButtonPressed: (Pokemon) -> Unit,
    onGenderButtonClicked: (Pokemon) ->Unit,
    modifier: Modifier = Modifier
) {
        Box(
            modifier = modifier
                .fillMaxWidth()
                .height(350.dp)
        ) {
            // Background
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(200.dp)
                    .offsetGradientBackground(
                        colors = if (pokemon.types.size == 1) {
                            listOf(
                                pokemon.types.first().toColor(),
                                Color.White
                            )
                        } else {
                            pokemon.types.toList().map {
                                it.toColor()
                            }
                        },
                        { 1000f }
                    )
            )

            // 1st row
            IconButton(
                onClick = {
                    onFavoriteButtonPressed(pokemon)
                },
                modifier = Modifier
                    .statusBarsPadding().navigationBarsPadding()
                    .padding(20.dp)
                    .size(60.dp)
                    .align(Alignment.TopEnd)
            ) {
                Icon(
                    if (isFavorite)
                        Icons.Default.Favorite
                    else
                        Icons.Default.FavoriteBorder,
                    tint = Color.Red,
                    contentDescription = "Botão de favorito", // TODO por isso no res
                    modifier = Modifier.size(60.dp)
                )
            }

            // Pokemon Type Icons 1st row
            Column(
                modifier = Modifier
                    .statusBarsPadding().navigationBarsPadding()
                    .padding(10.dp, 10.dp)
            ) {
                for (type in pokemon.types) {
                    PokemonTypeIcon(
                        type = type,
                        fontSize = 16.sp,
                        modifier = Modifier.padding(2.dp)
                    )
                }
            }

            // Pokemon Sprite (2 row)
            GlideImageWithShadow(
                model = if (gender == PokemonGender.MALE) {
                    pokemon.sprites.frontDefaultUrl
                } else {
                    pokemon.sprites.frontFemaleUrl ?: pokemon.sprites.frontDefaultUrl
                },
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .size(300.dp)
                    .align(Alignment.BottomCenter)
            )

            // 2nd row
            IconButton(
                onClick = { onGenderButtonClicked(pokemon) },
                modifier = Modifier
                    .padding(20.dp)
                    .offset(
                        x = 0.dp,
                        y = 60.dp
                    )
                    .size(35.dp)
                    .align(AbsoluteAlignment.CenterRight)
            ) {
                if (gender == PokemonGender.MALE) {
                    MaleIconWithBackground()
                } else {
                    FemaleIconWithBackground()
                }
            }

            // Pokémon ID (2nd row)
            Text(
                text = "#${pokemon.id}",
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

            // Pokémon Name (3 row)
            Text(
                text = pokemon.name.uppercase(),
                fontSize = 30.sp,
                color = MaterialTheme.colorScheme.onSurface,
                modifier = Modifier
                    .align(Alignment.BottomCenter)
                    .statusBarsPadding()
                    .navigationBarsPadding(),
                fontFamily = FontFamily(
                    Font(
                        R.font.roboto_bold,
                    )
                )
            )
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
                    // altura em hectogramas para kilogramas
                    text = "${pokemon.weight} kg",
                    fontWeight = FontWeight.Bold,
                    fontSize = 20.sp,
                )
                Text(
                    text = stringResource(R.string.pokemon_overview_weight_label),
                    fontSize = 15.sp,
                )
            }
            VerticalDivider(Modifier.padding(20.dp))

            Column(
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                TextButton(
                    onClick = {
                        // TODO
                    }
                ) {
                    Icon(
                        painter = painterResource(R.drawable.play_circle_24px),
                        contentDescription = stringResource(R.string.pokemon_overview_play_label), // TODO: adicionar ao res
                        modifier = Modifier
                            .size(30.dp)
                        //                tint = MaterialTheme.colorScheme.surfaceTint,
                    )
                }
                Text(
                    text = stringResource(R.string.pokemon_overview_sound_label),
                    fontSize = 15.sp,
                )
            }
            VerticalDivider(Modifier.padding(20.dp))
            Column(
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    // altura em decimetros para metro
                    text = "${pokemon.height * 10} m",
                    fontWeight = FontWeight.Bold,
                    fontSize = 20.sp,
                )
                Text(
                    text = "Altura",
                    fontSize = 15.sp,
                )
            }
    }
}

@Composable
fun StatsOverview(pokemon: Pokemon, modifier: Modifier = Modifier) {
    Column(
        Modifier.padding(5.dp)
    ) {
        for (stat in pokemon.stats) {
            PokemonOverviewRow {
                Text(
                    text = "${stat.name.uppercase()}: ${stat.baseStat}",
                    fontSize = 24.sp
                )
            }
            HorizontalDivider(modifier
                .offsetGradientBackground(
                    listOf(
                        Color.Blue,
                        Color.Transparent
                    ),
                    { 10000f }
                )
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
