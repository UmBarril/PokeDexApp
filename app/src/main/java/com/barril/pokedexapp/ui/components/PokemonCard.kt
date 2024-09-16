package com.barril.pokedexapp.ui.components

import android.graphics.Bitmap
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
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
import androidx.compose.ui.draw.blur
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.asAndroidBitmap
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.DpOffset
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.barril.pokedexapp.R
import com.barril.pokedexapp.domain.Pokemon
import com.barril.pokedexapp.domain.PokemonGender
import com.barril.pokedexapp.domain.PokemonType
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import java.util.EnumSet

@Composable
fun PokemonCard(
    pokemon: Pokemon,
    onCardClick: () -> Unit,
    onFavoriteButtonPressed: () -> Unit,
    modifier: Modifier = Modifier,
) {
    var gender by remember { mutableStateOf(pokemon.selectedGender) }
    val targetSize = with(LocalDensity.current) {
        (pokemon.height * 2).dp
    }
    PokemonCard(
        pokemonId = pokemon.id,
        pokemonName = pokemon.name,
        pokemonType = pokemon.types,
        pokemonArt = {
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
        onFavoriteButtonPressed = onFavoriteButtonPressed,
        onCardClick = onCardClick,
        isFavorite = pokemon.isFavorite,
        modifier = modifier
    )
}

/**
 * Cartão de exibição de um Pokémon.
 * TODO: Colocar numeros mágicos em variáveis
 * TODO: Fazer tamanho ser responsivo
 */
@Composable
fun PokemonCard(
    pokemonId: Int,
    pokemonName: String,
    pokemonType: EnumSet<PokemonType>,
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

            // TODO: fazer isso clicavel para trocar a aparencia do pokemon
//            var isMale by remember { mutableStateOf(false) }
            Box(Modifier.clickable { onGenderButtonPressed() }) {
                if(pokemonGender == PokemonGender.MALE) {
                    MaleIcon(null)
                } else {
                    FemaleIcon(null)
                }
            }

            // usar IconButton faz o icone desaparecer por alguma razão
//            TextButton(onClick = onFavoriteButtonPressed) {
                Icon(
                    if (isFavorite)
                        Icons.Default.Favorite
                    else
                        Icons.Default.FavoriteBorder,
                    tint = MaterialTheme.colorScheme.onSurface,
                    contentDescription = stringResource(R.string.favorites_button_description),
                    modifier = Modifier
                        .padding(20.dp, 0.dp)
                        .size(20.dp)
                        .clickable { onFavoriteButtonPressed() }
                )
//            }
        }
    }
}

@Composable
fun MaleIcon(contentDescription: String?, modifier: Modifier = Modifier) {
    Icon(
        painter = painterResource(id = R.drawable.male_24px),
        contentDescription = contentDescription,
        tint = Color.Cyan, // TODO: por isso no xml
        modifier = modifier
    )
}

@Composable
fun FemaleIcon(contentDescription: String?, modifier: Modifier = Modifier) {
    Icon(
        painter = painterResource(id = R.drawable.female_24px),
        contentDescription = contentDescription,
        tint = Color.Magenta, // TODO: por isso no xml
        modifier = modifier
    )
}

@OptIn(ExperimentalGlideComposeApi::class)
@Composable
fun GlideImageWithShadow(
//    bitmap: ImageBitmap,
    model: Any?,
    modifier: Modifier = Modifier,
    contentDescription: String?,
    contentScale: ContentScale = ContentScale.Fit,
    shadowOffset: DpOffset = DpOffset(3.dp, 3.dp)
) {
    Box(modifier/*.height(70.dp)*/) {
        // sombra
        GlideImage(
//            bitmap = bitmap,
            model = model,
            contentDescription = contentDescription,
            contentScale = contentScale,
            colorFilter = ColorFilter.tint(Color.Black),
            alpha = 0.4f,
            modifier = modifier
                .offset(x =  shadowOffset.x, y = shadowOffset.y)
                .blur(radius = 1.dp)
        )
        // imagem
        GlideImage(
//            bitmap = bitmap,
            model = model,
            contentDescription = contentDescription,
            contentScale = contentScale,
//            filterQuality = FilterQuality.None,
            modifier = modifier
        )
    }
}

fun ImageBitmap.scaleImageBitMap(
    targetWidth: Int,
    targetHeight: Int
): ImageBitmap {
    return Bitmap.createScaledBitmap(this.asAndroidBitmap(), targetWidth, targetHeight, false).asImageBitmap()
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
