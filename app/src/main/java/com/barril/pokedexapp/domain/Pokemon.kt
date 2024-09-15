package com.barril.pokedexapp.domain

import java.util.EnumSet

data class Pokemon(
    val id: Int,
    val name: String,
    val height: Int,
    val order: Int,
    val weight: Int,
    val isFavorite: Boolean,
    val abilities: List<PokemonAbility>,
    val heldItems: List<NamedResource>,
    val moves: List<NamedResource>,
    val sprites: PokemonSpritesResource,
    val cries: PokemonCriesResource,
    val stats: List<PokemonStat>,
    val types: EnumSet<PokemonType>,
)

data class PokemonStat(
    val name: String,
    val baseStat: Int,
    val effort: Int,
    val url: String
)

data class PokemonAbility(
    val name: String,
    val isHidden: Boolean,
    val url: String,
    val slot: Int
)

data class NamedResource(
    val name: String,
    val url: String
)

data class PokemonCriesResource(
    val latestUrl: String,
    val legacyUrl: String
)
