package com.barril.pokedexapp.ui.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.requiredHeightIn
import androidx.compose.foundation.layout.width
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.barril.pokedexapp.domain.PokemonType
import com.barril.pokedexapp.ui.components.PokemonTypeIcons.BugTypeIcon
import com.barril.pokedexapp.ui.components.PokemonTypeIcons.DarkTypeIcon
import com.barril.pokedexapp.ui.components.PokemonTypeIcons.DragonTypeIcon
import com.barril.pokedexapp.ui.components.PokemonTypeIcons.ElectricTypeIcon
import com.barril.pokedexapp.ui.components.PokemonTypeIcons.FairyTypeIcon
import com.barril.pokedexapp.ui.components.PokemonTypeIcons.FightingTypeIcon
import com.barril.pokedexapp.ui.components.PokemonTypeIcons.FireTypeIcon
import com.barril.pokedexapp.ui.components.PokemonTypeIcons.FlyingTypeIcon
import com.barril.pokedexapp.ui.components.PokemonTypeIcons.GhostTypeIcon
import com.barril.pokedexapp.ui.components.PokemonTypeIcons.GrassTypeIcon
import com.barril.pokedexapp.ui.components.PokemonTypeIcons.GroundTypeIcon
import com.barril.pokedexapp.ui.components.PokemonTypeIcons.IceTypeIcon
import com.barril.pokedexapp.ui.components.PokemonTypeIcons.NormalTypeIcon
import com.barril.pokedexapp.ui.components.PokemonTypeIcons.PoisonTypeIcon
import com.barril.pokedexapp.ui.components.PokemonTypeIcons.PsychicTypeIcon
import com.barril.pokedexapp.ui.components.PokemonTypeIcons.RockTypeIcon
import com.barril.pokedexapp.ui.components.PokemonTypeIcons.SteelTypeIcon
import com.barril.pokedexapp.ui.components.PokemonTypeIcons.WaterTypeIcon

@Composable
fun PokemonTypeIcon(
    typeName: String,
    backgroundColor: Color,
    fontSize: TextUnit = 5.sp,
    modifier: Modifier = Modifier
) {
    val textPadding = 4.dp

//    val borderSize = 1.5.dp
    val borderSize = with(LocalDensity.current) {
        fontSize.toDp() / 4
    }

//  outlineWidth = 3f,
    val outlineWidth = with(LocalDensity.current) {
        fontSize.toPx() / 6
    }

    // TODO: refatorar essa parte como: MultiOutlinedCard
    // primeira borda
    OutlinedCard(
        shape = MaterialTheme.shapes.medium,
        border = BorderStroke(borderSize / 3, Color.Black),
        modifier = modifier.requiredHeightIn(with(LocalDensity.current) {
            fontSize.toDp() + (borderSize * 2) + textPadding
        })
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
                    outlineWidth = outlineWidth,
                    outlineMiter = 20f,
                    fontWeight = FontWeight.W800,
                    modifier = modifier.padding(textPadding),
                    fontSize = fontSize
                )
            }
        }
    }
}

@Composable
fun PokemonTypeIcon(type: PokemonType, fontSize: TextUnit) {
    when(type) {
        PokemonType.GRASS -> GrassTypeIcon(fontSize)
        PokemonType.FIRE -> FireTypeIcon(fontSize)
        PokemonType.WATER -> WaterTypeIcon(fontSize)
        PokemonType.ELECTRIC -> ElectricTypeIcon(fontSize)
        PokemonType.ICE -> IceTypeIcon(fontSize)
        PokemonType.FIGHTING -> FightingTypeIcon(fontSize)
        PokemonType.POISON -> PoisonTypeIcon(fontSize)
        PokemonType.GROUND -> GroundTypeIcon(fontSize)
        PokemonType.FLYING -> FlyingTypeIcon(fontSize)
        PokemonType.PSYCHIC -> PsychicTypeIcon(fontSize)
        PokemonType.BUG -> BugTypeIcon(fontSize)
        PokemonType.ROCK -> RockTypeIcon(fontSize)
        PokemonType.GHOST -> GhostTypeIcon(fontSize)
        PokemonType.DRAGON -> DragonTypeIcon(fontSize)
        PokemonType.DARK -> DarkTypeIcon(fontSize)
        PokemonType.STEEL -> SteelTypeIcon(fontSize)
        PokemonType.FAIRY -> FairyTypeIcon(fontSize)
        PokemonType.NORMAL -> NormalTypeIcon(fontSize)
    }
}

object PokemonTypeIcons {

    @Composable
    fun GrassTypeIcon(fontSize: TextUnit) {
        PokemonTypeIcon(
            typeName = "GRASS",
            fontSize = fontSize,
            backgroundColor = Color(0xFF7ED956)
        )
    }

    @Composable
    fun FireTypeIcon(fontSize: TextUnit) {
        PokemonTypeIcon(
            typeName = "FIRE",
            fontSize = fontSize,
            backgroundColor = Color(0xFFF08030)
        )
    }

    @Composable
    fun WaterTypeIcon(fontSize: TextUnit) {
        PokemonTypeIcon(
            typeName = "WATER",
            fontSize = fontSize,
            backgroundColor = Color(0xFF6890F0)
        )
    }

    @Composable
    fun ElectricTypeIcon(fontSize: TextUnit) {
        PokemonTypeIcon(
            typeName = "ELECTRIC",
            fontSize = fontSize,
            backgroundColor = Color(0xFFF8D030)
        )
    }

    @Composable
    fun IceTypeIcon(fontSize: TextUnit) {
        PokemonTypeIcon(
            typeName = "ICE",
            fontSize = fontSize,
            backgroundColor = Color(0xFF98D8D8)
        )
    }

    @Composable
    fun FightingTypeIcon(fontSize: TextUnit) {
        PokemonTypeIcon(
            typeName = "FIGHTING",
            fontSize = fontSize,
            backgroundColor = Color(0xFFC03028)
        )
    }

    @Composable
    fun PoisonTypeIcon(fontSize: TextUnit) {
        PokemonTypeIcon(
            typeName = "POISON",
            fontSize = fontSize,
            backgroundColor = Color(0xFFA040A0)
        )
    }

    @Composable
    fun GroundTypeIcon(fontSize: TextUnit) {
        PokemonTypeIcon(
            typeName = "GROUND",
            fontSize = fontSize,
            backgroundColor = Color(0xFFE0C068)
        )
    }

    @Composable
    fun FlyingTypeIcon(fontSize: TextUnit) {
        PokemonTypeIcon(
            typeName = "FLYING",
            fontSize = fontSize,
            backgroundColor = Color(0xFFA890F0)
        )
    }

    @Composable
    fun PsychicTypeIcon(fontSize: TextUnit) {
        PokemonTypeIcon(
            typeName = "PSYCHIC",
            fontSize = fontSize,
            backgroundColor = Color(0xFFF85888)
        )
    }

    @Composable
    fun BugTypeIcon(fontSize: TextUnit) {
        PokemonTypeIcon(
            typeName = "BUG",
            fontSize = fontSize,
            backgroundColor = Color(0xFFA8B820)
        )
    }

    @Composable
    fun RockTypeIcon(fontSize: TextUnit) {
        PokemonTypeIcon(
            typeName = "ROCK",
            fontSize = fontSize,
            backgroundColor = Color(0xFFB8A038)
        )
    }

    @Composable
    fun GhostTypeIcon(fontSize: TextUnit) {
        PokemonTypeIcon(
            typeName = "GHOST",
            fontSize = fontSize,
            backgroundColor = Color(0xFF705898)
        )
    }

    @Composable
    fun DragonTypeIcon(fontSize: TextUnit) {
        PokemonTypeIcon(
            typeName = "DRAGON",
            fontSize = fontSize,
            backgroundColor = Color(0xFF7038F8)
        )
    }

    @Composable
    fun DarkTypeIcon(fontSize: TextUnit) {
        PokemonTypeIcon(
            typeName = "DARK",
            fontSize = fontSize,
            backgroundColor = Color(0xFF705848)
        )
    }

    @Composable
    fun SteelTypeIcon(fontSize: TextUnit) {
        PokemonTypeIcon(
            typeName = "STEEL",
            fontSize = fontSize,
            backgroundColor = Color (0xFFB8B8D0)
        )
    }

    @Composable
    fun FairyTypeIcon(fontSize: TextUnit) {
        PokemonTypeIcon(
           typeName = "FAIRY",
           fontSize = fontSize,
           backgroundColor = Color (0xFFF0B6BC)
        )
    }

    @Composable
    fun NormalTypeIcon(fontSize: TextUnit) {
        PokemonTypeIcon(
            typeName = "NORMAL",
            fontSize = fontSize,
            backgroundColor = Color(0xFFA8A878)
        )
    }

}

@Preview
@Composable
fun TypesPreview() {
    val fontSize = 10.sp
    Column {
        PokemonTypeIcons.GrassTypeIcon(fontSize)
        PokemonTypeIcons.FireTypeIcon(fontSize)
        PokemonTypeIcons.WaterTypeIcon(fontSize)
        PokemonTypeIcons.ElectricTypeIcon(fontSize)
        PokemonTypeIcons.IceTypeIcon(fontSize)
        PokemonTypeIcons.FightingTypeIcon(fontSize)
        PokemonTypeIcons.PoisonTypeIcon(fontSize)
        PokemonTypeIcons.GroundTypeIcon(fontSize)
        PokemonTypeIcons.FlyingTypeIcon(fontSize)
        PokemonTypeIcons.PsychicTypeIcon(fontSize)
        PokemonTypeIcons.BugTypeIcon(fontSize)
        PokemonTypeIcons.RockTypeIcon(fontSize)
        PokemonTypeIcons.GhostTypeIcon(fontSize)
        PokemonTypeIcons.DragonTypeIcon(fontSize)
        PokemonTypeIcons.DarkTypeIcon(fontSize)
        PokemonTypeIcons.SteelTypeIcon(fontSize)
        PokemonTypeIcons.FairyTypeIcon(fontSize)
        PokemonTypeIcons.NormalTypeIcon(fontSize)
    }
}