package com.barril.pokedexapp.ui.theme

import androidx.compose.ui.graphics.Color
import com.barril.pokedexapp.domain.PokemonType

val grassColor = Color(0xFF7ED956)
val fireColor = Color(0xFFF08030)
val waterColor = Color(0xFF6890F0)
val electricColor = Color(0xFFF8D030)
val iceColor = Color(0xFF98D8D8)
val fightingColor = Color(0xFFC03028)
val poisonColor = Color(0xFFA040A0)
val groundColor = Color(0xFFE0C068)
val flyingColor = Color(0xFFA890F0)
val psychicColor = Color(0xFFF85888)
val bugColor = Color(0xFFA8B820)
val rockColor = Color(0xFFB8A038)
val ghostColor = Color(0xFF705898)
val dragonColor = Color(0xFF7038F8)
val darkColor = Color(0xFF705848)
val steelColor = Color(0xFFB8B8D0)
val fairyColor = Color(0xFFF0B6BC)
val normalColor = Color(0xFFA8A878)

fun PokemonType.toColor(): Color {
    return when (this) {
        PokemonType.FIRE -> fireColor
        PokemonType.WATER -> waterColor
        PokemonType.GRASS -> grassColor
        PokemonType.ELECTRIC -> electricColor
        PokemonType.ICE -> iceColor
        PokemonType.FIGHTING -> fightingColor
        PokemonType.POISON -> poisonColor
        PokemonType.GROUND -> groundColor
        PokemonType.FLYING -> flyingColor
        PokemonType.PSYCHIC -> psychicColor
        PokemonType.BUG -> bugColor
        PokemonType.ROCK -> rockColor
        PokemonType.GHOST -> ghostColor
        PokemonType.DRAGON -> dragonColor
        PokemonType.DARK -> darkColor
        PokemonType.STEEL -> steelColor
        PokemonType.FAIRY -> fairyColor
        PokemonType.NORMAL -> normalColor
    }
}