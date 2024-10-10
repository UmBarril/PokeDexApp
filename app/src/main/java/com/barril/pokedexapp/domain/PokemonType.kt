package com.barril.pokedexapp.domain

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

    // ainda tem outros tipos especiais que não pus: BIRD, ???, SHADOW, STELLAR
    // verificar se esses tipos estão na api

    companion object {
        fun fromString(str: String): PokemonType {
            return when (str.uppercase()) {
                "FIRE" -> FIRE
                "WATER" -> WATER
                "GRASS" -> GRASS
                "ELECTRIC" -> ELECTRIC
                "ICE" -> ICE
                "FIGHTING" -> FIGHTING
                "POISON" -> POISON
                "GROUND" -> GROUND
                "FLYING" -> FLYING
                "PSYCHIC" -> PSYCHIC
                "BUG" -> BUG
                "ROCK" -> ROCK
                "GHOST" -> GHOST
                "DRAGON" -> DRAGON
                "DARK" -> DARK
                "STEEL" -> STEEL
                "FAIRY" -> FAIRY
                "NORMAL" -> NORMAL
                else -> { throw IllegalArgumentException() }
            }
        }
    }
}
