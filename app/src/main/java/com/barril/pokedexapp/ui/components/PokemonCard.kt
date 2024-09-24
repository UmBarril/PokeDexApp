package com.barril.pokedexapp.ui.components

import android.widget.Toast
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.barril.pokedexapp.R
import com.barril.pokedexapp.domain.Pokemon
import com.barril.pokedexapp.domain.PokemonGender
import com.barril.pokedexapp.domain.PokemonType

/**
 * Cartão de exibição de um Pokémon.
 * TODO: Colocar números mágicos em variáveis
 * TODO: Fazer tamanho ser responsivo
 */
@Composable
fun PokemonCard(
    pokemon: Pokemon,
    onCardClick: (Pokemon) -> Unit,
    onFavoriteButtonPressed: (Pokemon) -> Unit,
    modifier: Modifier = Modifier,
) {
    var gender by remember { mutableStateOf(pokemon.selectedGender) }
    val targetSize = /*(pokemon.height * 2).dp*/ 100.dp
    PokemonCard(
        pokemonId = pokemon.id,
        pokemonName = pokemon.name,
        pokemonType = pokemon.types,
        pokemonArt = {
            val context = LocalContext.current
            LaunchedEffect(key1 = gender) {
                if(gender == PokemonGender.FEMALE &&
                    pokemon.sprites.frontFemaleUrl == null) {
                    Toast.makeText(
                        context,
                        "Esse pokémon não tem sprites femininos",
                        Toast.LENGTH_LONG
                    ).show()
                }
            }

            GlideImageWithShadow(
                model = if (gender == PokemonGender.FEMALE) {
                    pokemon.sprites.frontFemaleUrl ?: pokemon.sprites.frontDefaultUrl
                } else {
                    pokemon.sprites.frontDefaultUrl
                },
                contentDescription = "",
                modifier = Modifier.size(targetSize)
            )
        },
        pokemonGender = gender,
        onGenderButtonPressed = {
            gender = if (gender == PokemonGender.FEMALE) {
                PokemonGender.MALE
            } else {
                PokemonGender.FEMALE
            }
        },
        onFavoriteButtonPressed = { onFavoriteButtonPressed(pokemon) },
        onCardClick = { onCardClick(pokemon) },
        isFavorite = pokemon.isFavorite,
        modifier = modifier
    )
}

@Composable
private fun PokemonCard(
    pokemonId: Int,
    pokemonName: String,
    pokemonType: Set<PokemonType>,
    pokemonArt: @Composable () -> Unit,
    pokemonGender: PokemonGender,
    onCardClick: () -> Unit,
    onFavoriteButtonPressed: () -> Unit,
    onGenderButtonPressed: () -> Unit,
    isFavorite: Boolean,
    modifier: Modifier = Modifier,
) {
    Box(modifier.clickable { onCardClick() }) {
        Card(
            shape = MaterialTheme.shapes.large,
            modifier = Modifier
                .height(60.dp)
                .align(Alignment.Center)
                .padding(5.dp, 0.dp)
                .fillMaxWidth()
        ) { }

        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
        ) {
            Box(
                Modifier
                    .padding(start = 8.dp)
                    .size(80.dp)
                    .clip(RectangleShape)
                    .wrapContentSize(Alignment.Center)
            ) {
                pokemonArt()
            }
            Column(modifier = Modifier.padding(4.dp)) {
                Row {
                    Text(
                        text = "#$pokemonId",
                        color = Color(0xFFFFFF41),
                        fontSize = 16.sp,
                        fontFamily = FontFamily(
                            Font(
                                R.font.roboto_bold,
                            )
                        ),
                        modifier = Modifier.padding(end = 2.dp)
                    )
                    Text(
                        text = pokemonName.replaceFirstChar { c -> c.uppercase() },
                        color = MaterialTheme.colorScheme.onSurface,
                        fontSize = 16.sp,
                        fontFamily = FontFamily(
                            Font(
                                R.font.roboto_bold,
                            )
                        )
                    )
                }

                Spacer(Modifier.padding(1.dp))

                Row {
                    for (type in pokemonType) {
                        PokemonTypeIcon(type, fontSize = 12.sp)
                    }
                }
            }

            Spacer(Modifier.weight(1f))

            IconButton(onClick = onGenderButtonPressed) {
                if (pokemonGender == PokemonGender.MALE) {
                    MaleIcon(null)
                } else {
                    FemaleIcon(null)
                }
            }

            IconButton(
                onClick = onFavoriteButtonPressed,
                modifier = Modifier.padding(end = 10.dp)
            ) {
                Icon(
                    if (isFavorite)
                        Icons.Default.Favorite
                    else
                        Icons.Default.FavoriteBorder,
                    contentDescription = stringResource(R.string.favorites_button_description),
                )
            }
        }
    }
}

// FIXME: corrigir previews
//@Preview
//@Composable
//fun BigPokemonCardPreview() {
//    var pressed by remember { mutableStateOf(false) }
//    PokemonCard(
//        "Big Pokemon",
//        EnumSet.of(PokemonType.FIRE),
//        pokemonArt = {
//            val img = ImageBitmap.imageResource(R.drawable.big_pokemon)
//            val targetWidth = with(LocalDensity.current) {
//                100.dp.toPx().toInt()
//            }
//            val targetHeight = with(LocalDensity.current) {
//                100.dp.toPx().toInt()
//            }
//            ImageWithShadow(
//                model = img.scaleImageBitMap(targetWidth, targetHeight),
//                contentDescription = null,
//                contentScale = ContentScale.None
//            )
//        },
//        isFavorite = pressed,
//        onFavoriteButtonPressed = { pressed = !pressed },
//        onCardClick = {}
//    )
//}
//
//@Preview
//@Composable
//fun SmallPokemonCardPreview() {
//    var pressed by remember { mutableStateOf(false) }
//    PokemonCard(
//        "Small Pokemon",
//        EnumSet.of(PokemonType.GRASS),
//        isFavorite = pressed,
//        onFavoriteButtonPressed = { pressed = !pressed },
//        pokemonArt = {
//            val img = ImageBitmap.imageResource(R.drawable.balbasaur)
//            val targetWidth = with(LocalDensity.current) {
//                100.dp.toPx().toInt()
//            }
//            val targetHeight = with(LocalDensity.current) {
//                100.dp.toPx().toInt()
//            }
//            ImageWithShadow(
//                model = img.scaleImageBitMap(targetWidth, targetHeight),
//                contentDescription = null,
//                contentScale = ContentScale.None
//            )
//        },
//        onCardClick = {}
//    )
//}
