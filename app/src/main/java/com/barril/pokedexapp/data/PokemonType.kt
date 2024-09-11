package com.barril.pokedexapp.data

import java.security.InvalidParameterException

enum class PokemonType {
    FIRE,
    WATER,
    GRASS,
    ELECTRIC,
    ICE,
    FIGHTING,
    POISON,
    GROUND,
    FLYING,
    PSYCHIC,
    BUG,
    ROCK,
    GHOST,
    DRAGON,
    DARK,
    STEEL,
    FAIRY,
    NORMAL;

    companion object {
        fun fromString(str: String): PokemonType {
            return when (str.uppercase()) {
                "FIRE" -> PokemonType.FIRE
                "WATER" -> PokemonType.WATER
                "GRASS" -> PokemonType.GRASS
                "ELECTRIC" -> PokemonType.ELECTRIC
                "ICE" -> PokemonType.ICE
                "FIGHTING" -> PokemonType.FIGHTING
                "POISON" -> PokemonType.POISON
                "GROUND" -> PokemonType.GROUND
                "FLYING" -> PokemonType.FLYING
                "PSYCHIC" -> PokemonType.PSYCHIC
                "BUG" -> PokemonType.BUG
                "ROCK" -> PokemonType.ROCK
                "GHOST" -> PokemonType.GHOST
                "DRAGON" -> PokemonType.DRAGON
                "DARK" -> PokemonType.DARK
                "STEEL" -> PokemonType.STEEL
                "FAIRY" -> PokemonType.FAIRY
                "NORMAL" -> PokemonType.NORMAL
                else -> { throw IllegalArgumentException() }
            }
        }
    }
}
