package com.barril.pokedexapp.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.requiredSize
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Rect
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.FilterQuality
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.Outline
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.layout.Layout
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.imageResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Constraints
import androidx.compose.ui.unit.Constraints.Companion.Infinity
import androidx.compose.ui.unit.Density
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.zIndex
import com.barril.pokedexapp.PokemonType
import com.barril.pokedexapp.R
import java.util.EnumSet


/**
 * Maneira bem tosca de burlar o sistema de constrainsts do Compose.
 * Mas funciona.
 */
@Composable
fun IgnoreConstraints(
    modifier: Modifier = Modifier,
    offSetX: Int = 0,
    offSetY: Int = 0,
    maxHeightConstraint: Int = Infinity,
    maxWidthConstraint: Int = Infinity,
    content: @Composable () -> Unit
) {
    Layout(
        modifier = modifier,
        measurePolicy =  { measurables, constraints ->
            val placeables = measurables.map {
//                it.measure(Constraints(0, maxWidthConstraint, 0, maxHeightConstraint))
                it.measure(constraints)
            }

            layout(
                width = offSetY,
                height = offSetX
            ) {
                placeables.forEach {
                    it.place(
                        x = offSetX,
                        y = offSetY
                    )
                }
            }
        },
        content = content
    )
}

@Composable
fun PokemonCard(
    pokemonName: String,
    pokemonType: EnumSet<PokemonType>,
    pokemonArt: ImageBitmap,
    onFavoriteButtonPressed: () -> Unit,
    isFavorite: Boolean = false,
    modifier: Modifier = Modifier
) {
    var isExpanded by remember { mutableStateOf(true) }

    val paddingModifier = Modifier.padding(10.dp, 4.dp)
    Box(
        Modifier.padding(0.dp, 8.dp, 0.dp, 0.dp)
    ) {
        val imageWidth = with(LocalDensity.current) {
            pokemonArt.width.toDp()
        }
        val scaleFactor = with(LocalDensity.current) {
            1.dp.toPx()
        }

//        val a = getResources().getDisplayMetrics();
        IgnoreConstraints(
            modifier = paddingModifier
//                .background(Color.Black)
                .zIndex(2f)
        ) {
            Image(
                bitmap = pokemonArt,
                contentDescription = null,
                filterQuality = FilterQuality.None,
                modifier = Modifier.requiredSize(200.dp)
            )
        }
//        Box(Modifier.zIndex(2f)) {
//            val scaleX = scaleFactor * 1.8f
//            val scaleY = scaleFactor * 1.8f
//            // sombra
//            Image(
//                bitmap = pokemonArt,
//                contentDescription = null,
//                colorFilter = ColorFilter.tint(Color.Black),
//                alpha = 0.4f,
//                modifier = paddingModifier
//                    .offset(x = 28.dp + 2.dp, y = 4.dp + 2.dp)
//                    .graphicsLayer {
//                        this.scaleX = scaleX
//                        this.scaleY = scaleY
//                    }
//                    .blur(radius = 1.dp)
//            )
//            // imagem
//            Image(
//                bitmap = pokemonArt,
//                contentDescription = null,
//                filterQuality = FilterQuality.None,
//                modifier = paddingModifier
//                    .offset(x = 28.dp, y = 4.dp)
////                    .graphicsLayer {
////                        this.scaleX = scaleX
////                        this.scaleY = scaleY
////                    }
//            )
//        }
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
                        text = "$scaleFactor",
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
                TextButton(onClick = { /*TODO*/ }) {
                    Icon(
                        // talvez usar uma estrela em vez de coração..
                        imageVector = if (isFavorite) Icons.Default.Favorite else Icons.Default.FavoriteBorder,
                        contentDescription = stringResource(R.string.favorites_button_description),
                        modifier = Modifier
                            .padding(20.dp, 0.dp)
                            .size(20.dp)
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
    var pressed by remember { mutableStateOf(false) }
    PokemonCard(
        "Bulbasaur",
        EnumSet.of(PokemonType.GRASS),
        pokemonArt = ImageBitmap.imageResource(R.drawable.balbasaur),
        isFavorite = pressed,
        onFavoriteButtonPressed = { pressed = !pressed }
    )
}
