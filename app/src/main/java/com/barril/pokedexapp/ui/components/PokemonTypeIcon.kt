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
import com.barril.pokedexapp.ui.theme.bugColor
import com.barril.pokedexapp.ui.theme.darkColor
import com.barril.pokedexapp.ui.theme.dragonColor
import com.barril.pokedexapp.ui.theme.electricColor
import com.barril.pokedexapp.ui.theme.fairyColor
import com.barril.pokedexapp.ui.theme.fightingColor
import com.barril.pokedexapp.ui.theme.fireColor
import com.barril.pokedexapp.ui.theme.flyingColor
import com.barril.pokedexapp.ui.theme.ghostColor
import com.barril.pokedexapp.ui.theme.grassColor
import com.barril.pokedexapp.ui.theme.groundColor
import com.barril.pokedexapp.ui.theme.iceColor
import com.barril.pokedexapp.ui.theme.normalColor
import com.barril.pokedexapp.ui.theme.poisonColor
import com.barril.pokedexapp.ui.theme.psychicColor
import com.barril.pokedexapp.ui.theme.rockColor
import com.barril.pokedexapp.ui.theme.steelColor
import com.barril.pokedexapp.ui.theme.waterColor

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
            // TODO: por essas cores em sua propria classe ou no res
            backgroundColor = grassColor,
            modifier = modifier
        )
    }

    @Composable
    fun FireTypeIcon(fontSize: TextUnit, modifier: Modifier = Modifier) {
        PokemonTypeIcon(
            typeName = "FIRE",
            fontSize = fontSize,
            backgroundColor = fireColor,
            modifier = modifier
        )
    }

    @Composable
    fun WaterTypeIcon(fontSize: TextUnit, modifier: Modifier = Modifier) {
        PokemonTypeIcon(
            typeName = "WATER",
            fontSize = fontSize,
            backgroundColor = waterColor,
            modifier = modifier
        )
    }

    @Composable
    fun ElectricTypeIcon(fontSize: TextUnit, modifier: Modifier = Modifier) {
        PokemonTypeIcon(
            typeName = "ELECTRIC",
            fontSize = fontSize,
            backgroundColor = electricColor,
            modifier = modifier
        )
    }

    @Composable
    fun IceTypeIcon(fontSize: TextUnit, modifier: Modifier = Modifier) {
        PokemonTypeIcon(
            typeName = "ICE",
            fontSize = fontSize,
            backgroundColor = iceColor,
            modifier = modifier
        )
    }

    @Composable
    fun FightingTypeIcon(fontSize: TextUnit, modifier: Modifier = Modifier) {
        PokemonTypeIcon(
            typeName = "FIGHTING",
            fontSize = fontSize,
            backgroundColor = fightingColor,
            modifier = modifier
        )
    }

    @Composable
    fun PoisonTypeIcon(fontSize: TextUnit, modifier: Modifier = Modifier) {
        PokemonTypeIcon(
            typeName = "POISON",
            fontSize = fontSize,
            backgroundColor = poisonColor,
            modifier = modifier
        )
    }

    @Composable
    fun GroundTypeIcon(fontSize: TextUnit, modifier: Modifier = Modifier) {
        PokemonTypeIcon(
            typeName = "GROUND",
            fontSize = fontSize,
            backgroundColor = groundColor,
            modifier = modifier
        )
    }

    @Composable
    fun FlyingTypeIcon(fontSize: TextUnit, modifier: Modifier = Modifier) {
        PokemonTypeIcon(
            typeName = "FLYING",
            fontSize = fontSize,
            backgroundColor = flyingColor,
            modifier = modifier
        )
    }

    @Composable
    fun PsychicTypeIcon(fontSize: TextUnit, modifier: Modifier = Modifier) {
        PokemonTypeIcon(
            typeName = "PSYCHIC",
            fontSize = fontSize,
            backgroundColor = psychicColor,
            modifier = modifier
        )
    }

    @Composable
    fun BugTypeIcon(fontSize: TextUnit, modifier: Modifier = Modifier) {
        PokemonTypeIcon(
            typeName = "BUG",
            fontSize = fontSize,
            backgroundColor = bugColor,
            modifier = modifier
        )
    }

    @Composable
    fun RockTypeIcon(fontSize: TextUnit, modifier: Modifier = Modifier) {
        PokemonTypeIcon(
            typeName = "ROCK",
            fontSize = fontSize,
            backgroundColor = rockColor,
            modifier = modifier
        )
    }

    @Composable
    fun GhostTypeIcon(fontSize: TextUnit, modifier: Modifier = Modifier) {
        PokemonTypeIcon(
            typeName = "GHOST",
            fontSize = fontSize,
            backgroundColor = ghostColor,
            modifier = modifier
        )
    }

    @Composable
    fun DragonTypeIcon(fontSize: TextUnit, modifier: Modifier = Modifier) {
        PokemonTypeIcon(
            typeName = "DRAGON",
            fontSize = fontSize,
            backgroundColor = dragonColor,
            modifier = modifier
        )
    }

    @Composable
    fun DarkTypeIcon(fontSize: TextUnit, modifier: Modifier = Modifier) {
        PokemonTypeIcon(
            typeName = "DARK",
            fontSize = fontSize,
            backgroundColor = darkColor,
            modifier = modifier
        )
    }

    @Composable
    fun SteelTypeIcon(fontSize: TextUnit, modifier: Modifier = Modifier) {
        PokemonTypeIcon(
            typeName = "STEEL",
            fontSize = fontSize,
            backgroundColor = steelColor,
            modifier = modifier
        )
    }

    @Composable
    fun FairyTypeIcon(fontSize: TextUnit, modifier: Modifier = Modifier) {
        PokemonTypeIcon(
           typeName = "FAIRY",
           fontSize = fontSize,
           backgroundColor = fairyColor,
           modifier = modifier
        )
    }

    @Composable
    fun NormalTypeIcon(fontSize: TextUnit, modifier: Modifier = Modifier) {
        PokemonTypeIcon(
            typeName = "NORMAL",
            fontSize = fontSize,
            backgroundColor = normalColor,
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