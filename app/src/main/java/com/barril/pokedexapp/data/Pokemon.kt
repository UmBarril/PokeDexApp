package com.barril.pokedexapp.data

data class Pokemon(
    val id: Int,
    val name: String,
    val height: Int,
    val order: Int,
    val weight: Int,
    val abilities: List<Ability>,
    val heldItems: List<HeldItem>,
    val moves: List<Move>,
    val sprites: Sprites,
    val cries: List<Cry>,
    val stats: List<Stat>,
    val types: List<PokemonType>
)

data class Ability(
    val name: String,
    val isHidden: Boolean,
    val url: String,
    val slot: Int
)

data class HeldItem(
    val name: String,
    val url: String
)

data class Move(
    val name: String,
    val url: String
)

data class Cry(
    val latest: String,
    val legacy: String
)

data class Stat(
    val name: String,
    val baseStat: Int,
    val effort: Int,
    val url: String
)

data class Sprites (
    val backDefaultUrl: String,
    val backFemaleUrl: String,
    val backShinyUrl: String,
    val backShinyFemaleUrl: String,
    val frontDefaultUrl: String,
    val frontFemaleUrl: String,
    val frontShinyUrl: String,
    val frontShinyFemaleUrl: String,
)
