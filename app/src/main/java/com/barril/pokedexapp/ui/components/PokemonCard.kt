package com.barril.pokedexapp.ui.components

import android.graphics.Bitmap
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ContextualFlowRow
import androidx.compose.foundation.layout.ContextualFlowRowOverflow
import androidx.compose.foundation.layout.ContextualFlowRowOverflowScope
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawingPadding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.blur
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.clipToBounds
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.FilterQuality
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.asAndroidBitmap
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.imageResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.DpOffset
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.zIndex
import com.barril.pokedexapp.domain.PokemonType
import com.barril.pokedexapp.R
import java.util.EnumSet

/**
 * Cartão de exibição de um Pokémon.
 * TODO: Colocar numeros mágicos em variáveis
 * TODO: Fazer tamanho ser responsivo
 */
@OptIn(ExperimentalLayoutApi::class)
@Composable
fun PokemonCard(
    pokemonName: String,
    pokemonType: EnumSet<PokemonType>,
    pokemonArt: @Composable () -> Unit,
    modifier: Modifier = Modifier,
    onFavoriteButtonPressed: () -> Unit,
    isFavorite: Boolean = false
) {
    Box {
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
                        text = pokemonName,
                        color = MaterialTheme.colorScheme.onSurface,
                        fontSize = 16.sp,
                        fontFamily = FontFamily(
                            Font(
                                R.font.roboto_bold,
                            )
                        )
                    )

                    // TODO: fazer isso clicavel para trocar a aparencia do pokemon
                    FemaleIcon(null)
                    // MaleIcon
                }

                Spacer(Modifier.padding(1.dp))

                Row {
                    for (type in pokemonType) {
                        PokemonTypeIcon(type, 12.sp)
                    }
                }

//                // https://developer.android.com/develop/ui/compose/layouts/flow
//                var maxLines by remember { mutableIntStateOf(1) }
//
//                val moreOrCollapseIndicator = @Composable { scope: ContextualFlowRowOverflowScope ->
//                    val remainingItems = pokemonType.size - scope.shownItemCount
//
//                    TextButton(onClick = {
//                        if (remainingItems == 0) {
//                            maxLines = 1
//                        } else {
//                            maxLines += 4
//                        }
//                    }) {
//                        Text(if (remainingItems == 0) "Less" else "+$remainingItems",)
//                    }
//                }
//                ContextualFlowRow(
//                    modifier = Modifier
//                        .safeDrawingPadding()
//                        .fillMaxWidth(1f)
////                        .padding(16.dp)
//                        .wrapContentHeight(align = Alignment.Top)
//                        .verticalScroll(rememberScrollState()),
//                    verticalArrangement = Arrangement.spacedBy(4.dp),
//                    horizontalArrangement = Arrangement.spacedBy(8.dp),
////                    maxLines = maxLines,
//                    overflow = ContextualFlowRowOverflow.expandOrCollapseIndicator(
//                        minRowsToShowCollapse = 2,
//                        expandIndicator = moreOrCollapseIndicator,
//                        collapseIndicator = moreOrCollapseIndicator
//                    ),
//                    itemCount = pokemonType.size
//                ) { index ->
//                    for (type in pokemonType) {
//                        PokemonTypeIcon(type, 12.sp)
//                    }
//                }
            }

            Spacer(Modifier.weight(1f))

            // usar IconButton faz o icone desaparecer por alguma razão
            TextButton(onClick = onFavoriteButtonPressed) {
                Icon(
                    imageVector = if (isFavorite) Icons.Default.Favorite else Icons.Default.FavoriteBorder,
                    tint = Color.Black,
                    contentDescription = stringResource(R.string.favorites_button_description),
                    modifier = Modifier
                        .padding(20.dp, 0.dp)
                        .size(20.dp)
                )
            }
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

@Composable
fun ImageWithShadow(
    bitmap: ImageBitmap,
    modifier: Modifier = Modifier,
    contentDescription: String?,
    contentScale: ContentScale = ContentScale.Fit,
    shadowOffset: DpOffset = DpOffset(3.dp, 3.dp)
) {
    Box(modifier/*.height(70.dp)*/) {
        // sombra
        Image(
            bitmap = bitmap,
            contentDescription = contentDescription,
            contentScale = contentScale,
            colorFilter = ColorFilter.tint(Color.Black),
            alpha = 0.4f,
            modifier = modifier
                .offset(x =  shadowOffset.x, y = shadowOffset.y)
                .blur(radius = 1.dp)
        )
        // imagem
        Image(
            bitmap = bitmap,
            contentDescription = contentDescription,
            contentScale = contentScale,
            filterQuality = FilterQuality.None,
            modifier = modifier
        )
    }
}

fun scaleImageBitMap(
    img: ImageBitmap,
    targetWidth: Int,
    targetHeight: Int
): ImageBitmap {
    return Bitmap.createScaledBitmap(img.asAndroidBitmap(), targetWidth, targetHeight, false).asImageBitmap()
}

@Preview
@Composable
fun BigPokemonCardPreview() {
    var pressed by remember { mutableStateOf(false) }
    PokemonCard(
        "Big Pokemon",
        EnumSet.of(PokemonType.FIRE),
        pokemonArt = {
            val img = ImageBitmap.imageResource(R.drawable.big_pokemon)
            val targetWidth = with(LocalDensity.current) {
                100.dp.toPx().toInt()
            }
            val targetHeight = with(LocalDensity.current) {
                100.dp.toPx().toInt()
            }
            ImageWithShadow(
                bitmap = scaleImageBitMap(img, targetWidth, targetHeight),
                contentDescription = null,
                contentScale = ContentScale.None
            )
        },
        isFavorite = pressed,
        onFavoriteButtonPressed = { pressed = !pressed }
    )
}

@Preview
@Composable
fun SmallPokemonCardPreview() {
    var pressed by remember { mutableStateOf(false) }
    PokemonCard(
        "Small Pokemon",
        EnumSet.of(PokemonType.GRASS),
        isFavorite = pressed,
        onFavoriteButtonPressed = { pressed = !pressed },
        pokemonArt = {
            val img = ImageBitmap.imageResource(R.drawable.balbasaur)
            val targetWidth = with(LocalDensity.current) {
                100.dp.toPx().toInt()
            }
            val targetHeight = with(LocalDensity.current) {
                100.dp.toPx().toInt()
            }
            ImageWithShadow(
                bitmap = scaleImageBitMap(img, targetWidth, targetHeight),
                contentDescription = null,
                contentScale = ContentScale.None
            )
        }
    )
}
