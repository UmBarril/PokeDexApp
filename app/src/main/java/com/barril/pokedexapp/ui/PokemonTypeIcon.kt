package com.barril.pokedexapp.ui

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

object PokemonTypeIcons {
    @Composable
    fun GrassTypeIcon() {
        PokemonTypeIcon(
            typeName = "GRASS",
            backgroundColor = Color(0xFF7ED956)
        )
    }
}

@Composable
fun PokemonTypeIcon(
    typeName: String,
    backgroundColor: Color,
    modifier: Modifier = Modifier
) {
    val borderSize = 1.5.dp

    // TODO: refatorar essa parte como: MultiOutlinedCard
    // primeira borda
    OutlinedCard(
        shape = MaterialTheme.shapes.medium,
        border = BorderStroke(borderSize / 4, Color.Black),
        colors = CardDefaults.cardColors(
            containerColor = backgroundColor,
        ),
        modifier = modifier
    ) {
        // segunda borda
        Surface(
            border = BorderStroke(borderSize, Color.White),
            shape = MaterialTheme.shapes.medium,
            color = backgroundColor,
            modifier = modifier
                .width(IntrinsicSize.Max)
                .height(IntrinsicSize.Max)
        ) {
            Box(
                contentAlignment = Alignment.Center,
            ) {
                OutlinedText(
                    text = typeName,
                    color = Color.White,
                    outlineColor = Color.Black,
                    outlineWidth = 3f,
                    outlineMiter = 3f,
                    fontWeight = FontWeight.W800,
                    modifier = modifier.padding(4.dp),
                    fontSize = 5.sp
                )
            }
        }
    }
}
