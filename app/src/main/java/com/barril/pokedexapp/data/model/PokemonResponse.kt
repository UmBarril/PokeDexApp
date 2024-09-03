package com.barril.pokedexapp.data.model

data class PokemonResponse(
    val id: Int,
    val name: String,
    val baseExperience: Int,
    val height: Int,
    val isDefault: Boolean,
    val order: Int,
    val weight: Int,
    val abilities: List<AbilityResponse>,
    val forms: List<FormResponse>,
    val gameIndices: List<GameIndexResponse>,
    val heldItems: List<HeldItemResponse>,
    val locationAreaEncounters: String,
    val moves: List<MoveResponse>,
    val species: Species,
    val sprites: Sprites,
    val cries: List<CriesResponse>,
    val stats: List<StatResponse>,
    val types: List<Type>
)

data class AbilityResponse(
    val isHidden: Boolean,
    val slot: Int,
    val ability: AbilityDetails
)

data class AbilityDetails(
    val name: String,
    val url: String
)

data class FormResponse(
    val name: String,
    val url: String
)

data class GameIndexResponse(
    val gameIndex: Int,
    val version: Version
)

data class Version(
    val name: String,
    val url: String
)

data class HeldItemResponse(
    val item: Item
)

data class Item(
    val name: String,
    val url: String
)

data class MoveResponse(
    val move: MoveDetails
)

data class MoveDetails(
    val name: String,
    val url: String
)

data class Species(
    val name: String,
    val url: String
)

data class CriesResponse(
    val latest: String,
    val legacy: String
)

data class StatResponse(
    val baseStat: Int,
    val effort: Int,
    val stat: StatDetails
)

data class StatDetails(
    val name: String,
    val url: String
)

data class Type(
    val slot: Int,
    val type: TypeDetails
)

data class TypeDetails(
    val name: String,
    val url: String
)

data class Generation(
    val name: String,
    val url: String
)

// sempre vem de: https://github.com/PokeAPI/sprites/blob/master/sprites/pokemon/*
data class Sprites (
    val back_default: String,
    val back_female: String,
    val back_shiny: String,
    val back_shiny_female: String,
    val front_default: String,
    val front_female: String,
    val front_shiny: String,
    val front_shiny_female: String,
)
