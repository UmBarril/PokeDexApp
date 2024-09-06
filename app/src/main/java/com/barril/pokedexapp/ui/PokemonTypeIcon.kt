package com.barril.pokedexapp.ui

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

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

object PokemonTypeIcons {
    @Composable
    fun GrassTypeIcon() {
        PokemonTypeIcon(
            typeName = "GRASS",
            backgroundColor = Color(0xFF7ED956)
        )
    }

    @Composable
    fun FireTypeIcon() {
        PokemonTypeIcon(
            typeName = "FIRE",
            backgroundColor = Color(0xFFF08030)
        )
    }

    @Composable
    fun WaterTypeIcon() {
        PokemonTypeIcon(
            typeName = "WATER",
            backgroundColor = Color(0xFF6890F0)
        )
    }

    @Composable
    fun ElectricTypeIcon() {
        PokemonTypeIcon(
            typeName = "ELECTRIC",
            backgroundColor = Color(0xFFF8D030)
        )
    }

    @Composable
    fun IceTypeIcon() {
        PokemonTypeIcon(
            typeName = "ICE",
            backgroundColor = Color(0xFF98D8D8)
        )
    }

    @Composable
    fun FightingTypeIcon() {
        PokemonTypeIcon(
            typeName = "FIGHTING",
            backgroundColor = Color(0xFFC03028)
        )
    }

    @Composable
    fun PoisonTypeIcon() {
        PokemonTypeIcon(
            typeName = "POISON",
            backgroundColor = Color(0xFFA040A0)
        )
    }

    @Composable
    fun GroundTypeIcon() {
        PokemonTypeIcon(
            typeName = "GROUND",
            backgroundColor = Color(0xFFE0C068)
        )
    }

    @Composable
    fun FlyingTypeIcon() {
        PokemonTypeIcon(
            typeName = "FLYING",
            backgroundColor = Color(0xFFA890F0)
        )
    }

    @Composable
    fun PsychicTypeIcon() {
        PokemonTypeIcon(
            typeName = "PSYCHIC",
            backgroundColor = Color(0xFFF85888)
        )
    }

    @Composable
    fun BugTypeIcon() {
        PokemonTypeIcon(
            typeName = "BUG",
            backgroundColor = Color(0xFFA8B820)
        )
    }

    @Composable
    fun RockTypeIcon() {
        PokemonTypeIcon(
            typeName = "ROCK",
            backgroundColor = Color(0xFFB8A038)
        )
    }

    @Composable
    fun GhostTypeIcon() {
        PokemonTypeIcon(
            typeName = "GHOST",
            backgroundColor = Color(0xFF705898)
        )
    }

    @Composable
    fun DragonTypeIcon() {
        PokemonTypeIcon(
            typeName = "DRAGON",
            backgroundColor = Color(0xFF7038F8)
        )
    }

    @Composable
    fun DarkTypeIcon() {
        PokemonTypeIcon(
            typeName = "DARK",
            backgroundColor = Color(0xFF705848)
        )
    }

    @Composable
    fun SteelTypeIcon() {
        PokemonTypeIcon(
            typeName = "STEEL",
            backgroundColor = Color (0xFFB8B8D0)
        )
    }

    @Composable
    fun FairyTypeIcon() {
        PokemonTypeIcon(
           typeName = "FAIRY",
           backgroundColor = Color (0xFFF0B6BC)
        )
    }

    @Composable
    fun NormalTypeIcon() {
        PokemonTypeIcon(
            typeName = "NORMAL",
            backgroundColor = Color(0xFFA8A878)
        )
    }
}

@Preview
@Composable
fun TypesPreview() {
    Column {
        PokemonTypeIcons.GrassTypeIcon()
        PokemonTypeIcons.FireTypeIcon()
        PokemonTypeIcons.WaterTypeIcon()
        PokemonTypeIcons.ElectricTypeIcon()
        PokemonTypeIcons.IceTypeIcon()
        PokemonTypeIcons.FightingTypeIcon()
        PokemonTypeIcons.PoisonTypeIcon()
        PokemonTypeIcons.GroundTypeIcon()
        PokemonTypeIcons.FlyingTypeIcon()
        PokemonTypeIcons.PsychicTypeIcon()
        PokemonTypeIcons.BugTypeIcon()
        PokemonTypeIcons.RockTypeIcon()
        PokemonTypeIcons.GhostTypeIcon()
        PokemonTypeIcons.DragonTypeIcon()
        PokemonTypeIcons.DarkTypeIcon()
        PokemonTypeIcons.SteelTypeIcon()
        PokemonTypeIcons.FairyTypeIcon()
        PokemonTypeIcons.NormalTypeIcon()
    }
}