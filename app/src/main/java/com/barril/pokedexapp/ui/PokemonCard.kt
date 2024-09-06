package com.barril.pokedexapp.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Rect
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Outline
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Density
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp
import com.barril.pokedexapp.PokemonType
import com.barril.pokedexapp.R
import java.util.EnumSet

@Composable
fun PokemonCard(pokemonName: String, pokemonType: EnumSet<PokemonType>, modifier: Modifier = Modifier) {
    Card(
        shape = MaterialTheme.shapes.medium,
        modifier = modifier
            .padding(10.dp, 4.dp)
            .fillMaxWidth(),
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(
                painter = painterResource(id = R.drawable.balbasaur), // placeholder
                contentDescription = null,
//                contentScale = ContentScale.Crop,
                modifier = modifier
                    .size(150.dp)
//                    .clip()
            ) // aqui vai ter a imagem do pokemon
            Column {
                Text(
                    text = pokemonName,
                    color = Color.Black,
                    fontFamily = FontFamily(
                        Font(
                            R.font.roboto_bold
                        )
                    )
                )
                when {
                    pokemonType.contains(PokemonType.FIRE) -> {
                        Icon(Icons.Default.Clear, "")
                    }
                    pokemonType.contains(PokemonType.WATER) -> {
                        Icon(Icons.Default.Clear, "")
                    }
                    pokemonType.contains(PokemonType.GRASS) -> {
                        PokemonTypeIcons.GrassTypeIcon()
                    }
                    pokemonType.contains(PokemonType.ELECTRIC) -> {
                        Icon(Icons.Default.Clear, "")
                    }
                }
            }
            // TODO: colocar propriedades de acessibilidade
            Icon(
                imageVector = Icons.Default.Star,
                contentDescription = "Favoritos",
                tint = Color.Yellow,
                modifier = modifier
                    .padding(10.dp, 0.dp)
                    .clickable {  },
            )
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
