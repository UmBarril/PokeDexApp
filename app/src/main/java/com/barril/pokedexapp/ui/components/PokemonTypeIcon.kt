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
    modifier: Modifier = Modifier,
    fontSize: TextUnit = 5.sp
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
            modifier = Modifier
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
                    modifier = Modifier.padding(textPadding),
                    fontSize = fontSize
                )
            }
        }
    }
}

@Composable
fun PokemonTypeIcon(
    type: PokemonType,
    modifier: Modifier = Modifier,
    fontSize: TextUnit = 12.sp
) {
    when(type) {
        PokemonType.GRASS -> GrassTypeIcon(fontSize, modifier)
        PokemonType.FIRE -> FireTypeIcon(fontSize, modifier)
        PokemonType.WATER -> WaterTypeIcon(fontSize, modifier)
        PokemonType.ELECTRIC -> ElectricTypeIcon(fontSize, modifier)
        PokemonType.ICE -> IceTypeIcon(fontSize, modifier)
        PokemonType.FIGHTING -> FightingTypeIcon(fontSize, modifier)
        PokemonType.POISON -> PoisonTypeIcon(fontSize, modifier)
        PokemonType.GROUND -> GroundTypeIcon(fontSize, modifier)
        PokemonType.FLYING -> FlyingTypeIcon(fontSize, modifier)
        PokemonType.PSYCHIC -> PsychicTypeIcon(fontSize, modifier)
        PokemonType.BUG -> BugTypeIcon(fontSize, modifier)
        PokemonType.ROCK -> RockTypeIcon(fontSize, modifier)
        PokemonType.GHOST -> GhostTypeIcon(fontSize, modifier)
        PokemonType.DRAGON -> DragonTypeIcon(fontSize, modifier)
        PokemonType.DARK -> DarkTypeIcon(fontSize, modifier)
        PokemonType.STEEL -> SteelTypeIcon(fontSize, modifier)
        PokemonType.FAIRY -> FairyTypeIcon(fontSize, modifier)
        PokemonType.NORMAL -> NormalTypeIcon(fontSize, modifier)
    }
}

object PokemonTypeIcons {

    @Composable
    fun GrassTypeIcon(fontSize: TextUnit, modifier: Modifier = Modifier) {
        PokemonTypeIcon(
            typeName = "GRASS",
            fontSize = fontSize,
            backgroundColor = Color(0xFF7ED956),
            modifier = modifier
        )
    }

    @Composable
    fun FireTypeIcon(fontSize: TextUnit, modifier: Modifier = Modifier) {
        PokemonTypeIcon(
            typeName = "FIRE",
            fontSize = fontSize,
            backgroundColor = Color(0xFFF08030),
            modifier = modifier
        )
    }

    @Composable
    fun WaterTypeIcon(fontSize: TextUnit, modifier: Modifier = Modifier) {
        PokemonTypeIcon(
            typeName = "WATER",
            fontSize = fontSize,
            backgroundColor = Color(0xFF6890F0),
            modifier = modifier
        )
    }

    @Composable
    fun ElectricTypeIcon(fontSize: TextUnit, modifier: Modifier = Modifier) {
        PokemonTypeIcon(
            typeName = "ELECTRIC",
            fontSize = fontSize,
            backgroundColor = Color(0xFFF8D030),
            modifier = modifier
        )
    }

    @Composable
    fun IceTypeIcon(fontSize: TextUnit, modifier: Modifier = Modifier) {
        PokemonTypeIcon(
            typeName = "ICE",
            fontSize = fontSize,
            backgroundColor = Color(0xFF98D8D8),
            modifier = modifier
        )
    }

    @Composable
    fun FightingTypeIcon(fontSize: TextUnit, modifier: Modifier = Modifier) {
        PokemonTypeIcon(
            typeName = "FIGHTING",
            fontSize = fontSize,
            backgroundColor = Color(0xFFC03028),
            modifier = modifier
        )
    }

    @Composable
    fun PoisonTypeIcon(fontSize: TextUnit, modifier: Modifier = Modifier) {
        PokemonTypeIcon(
            typeName = "POISON",
            fontSize = fontSize,
            backgroundColor = Color(0xFFA040A0),
            modifier = modifier
        )
    }

    @Composable
    fun GroundTypeIcon(fontSize: TextUnit, modifier: Modifier = Modifier) {
        PokemonTypeIcon(
            typeName = "GROUND",
            fontSize = fontSize,
            backgroundColor = Color(0xFFE0C068),
            modifier = modifier
        )
    }

    @Composable
    fun FlyingTypeIcon(fontSize: TextUnit, modifier: Modifier = Modifier) {
        PokemonTypeIcon(
            typeName = "FLYING",
            fontSize = fontSize,
            backgroundColor = Color(0xFFA890F0),
            modifier = modifier
        )
    }

    @Composable
    fun PsychicTypeIcon(fontSize: TextUnit, modifier: Modifier = Modifier) {
        PokemonTypeIcon(
            typeName = "PSYCHIC",
            fontSize = fontSize,
            backgroundColor = Color(0xFFF85888),
            modifier = modifier
        )
    }

    @Composable
    fun BugTypeIcon(fontSize: TextUnit, modifier: Modifier = Modifier) {
        PokemonTypeIcon(
            typeName = "BUG",
            fontSize = fontSize,
            backgroundColor = Color(0xFFA8B820),
            modifier = modifier
        )
    }

    @Composable
    fun RockTypeIcon(fontSize: TextUnit, modifier: Modifier = Modifier) {
        PokemonTypeIcon(
            typeName = "ROCK",
            fontSize = fontSize,
            backgroundColor = Color(0xFFB8A038),
            modifier = modifier
        )
    }

    @Composable
    fun GhostTypeIcon(fontSize: TextUnit, modifier: Modifier = Modifier) {
        PokemonTypeIcon(
            typeName = "GHOST",
            fontSize = fontSize,
            backgroundColor = Color(0xFF705898),
            modifier = modifier
        )
    }

    @Composable
    fun DragonTypeIcon(fontSize: TextUnit, modifier: Modifier = Modifier) {
        PokemonTypeIcon(
            typeName = "DRAGON",
            fontSize = fontSize,
            backgroundColor = Color(0xFF7038F8),
            modifier = modifier
        )
    }

    @Composable
    fun DarkTypeIcon(fontSize: TextUnit, modifier: Modifier = Modifier) {
        PokemonTypeIcon(
            typeName = "DARK",
            fontSize = fontSize,
            backgroundColor = Color(0xFF705848),
            modifier = modifier
        )
    }

    @Composable
    fun SteelTypeIcon(fontSize: TextUnit, modifier: Modifier = Modifier) {
        PokemonTypeIcon(
            typeName = "STEEL",
            fontSize = fontSize,
            backgroundColor = Color (0xFFB8B8D0),
            modifier = modifier
        )
    }

    @Composable
    fun FairyTypeIcon(fontSize: TextUnit, modifier: Modifier = Modifier) {
        PokemonTypeIcon(
           typeName = "FAIRY",
           fontSize = fontSize,
           backgroundColor = Color (0xFFF0B6BC),
           modifier = modifier
        )
    }

    @Composable
    fun NormalTypeIcon(fontSize: TextUnit, modifier: Modifier = Modifier) {
        PokemonTypeIcon(
            typeName = "NORMAL",
            fontSize = fontSize,
            backgroundColor = Color(0xFFA8A878),
            modifier = modifier
        )
    }

}

@Preview
@Composable
fun TypesPreview() {
    val fontSize = 10.sp
    Column {
        GrassTypeIcon(fontSize)
        FireTypeIcon(fontSize)
        WaterTypeIcon(fontSize)
        ElectricTypeIcon(fontSize)
        IceTypeIcon(fontSize)
        FightingTypeIcon(fontSize)
        PoisonTypeIcon(fontSize)
        GroundTypeIcon(fontSize)
        FlyingTypeIcon(fontSize)
        PsychicTypeIcon(fontSize)
        BugTypeIcon(fontSize)
        RockTypeIcon(fontSize)
        GhostTypeIcon(fontSize)
        DragonTypeIcon(fontSize)
        DarkTypeIcon(fontSize)
        SteelTypeIcon(fontSize)
        FairyTypeIcon(fontSize)
        NormalTypeIcon(fontSize)
    }
}