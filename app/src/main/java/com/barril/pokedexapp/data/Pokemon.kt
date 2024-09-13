package com.barril.pokedexapp.data

import java.util.EnumSet

data class Pokemon(
    val id: Int,
    val name: String,
    val height: Int,
    val order: Int,
    val weight: Int,
    val abilities: List<PokemonAbility>,
    val heldItems: List<PokemonHeldItemResource>,
    val moves: List<PokemonMoveResource>,
    val sprites: PokemonSpritesResource,
    val cries: PokemonCriesResource,
    val stats: List<PokemonStat>,
    val types: EnumSet<PokemonType>
)

data class PokemonAbility(
    val name: String,
    val isHidden: Boolean,
    val url: String,
    val slot: Int
)

data class PokemonHeldItemResource(
    val name: String,
    val url: String
)

data class PokemonMoveResource(
    val name: String,
    val url: String
)

data class PokemonCriesResource(
    val latestUrl: String,
    val legacyUrl: String
)

data class PokemonStat(
    val name: String,
    val baseStat: Int,
    val effort: Int,
    val url: String
)

data class PokemonSpritesResource (
    val backDefaultUrl: String?,
    val backFemaleUrl: String?,
    val backShinyUrl: String?,
    val backShinyFemaleUrl: String?,
    val frontDefaultUrl: String?,
    val frontFemaleUrl: String?,
    val frontShinyUrl: String?,
    val frontShinyFemaleUrl: String?,
)
