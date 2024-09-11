package com.barril.pokedexapp.data.api

data class PokemonDto(
    val id: Int,
    val name: String,
    val baseExperience: Int,
    val height: Int,
    val isDefault: Boolean,
    val order: Int,
    val weight: Int,
    val abilities: List<AbilityDto>,
    val forms: List<FormDto>,
    val gameIndices: List<GameIndexDto>,
    val heldItems: List<HeldItemDto>,
    val locationAreaEncounters: String,
    val moves: List<MoveDto>,
    val species: SpeciesDto,
    val sprites: SpritesDto,
    val cries: List<CriesDto>,
    val stats: List<StatDto>,
    val types: List<TypeDto>
)

data class AbilityDto(
    val isHidden: Boolean,
    val slot: Int,
    val ability: AbilityDetailsDto
)

data class AbilityDetailsDto(
    val name: String,
    val url: String
)

data class FormDto(
    val name: String,
    val url: String
)

data class GameIndexDto(
    val gameIndex: Int,
    val version: VersionDto
)

data class VersionDto(
    val name: String,
    val url: String
)

data class HeldItemDto(
    val item: ItemDto
)

data class ItemDto(
    val name: String,
    val url: String
)

data class MoveDto(
    val move: MoveDetailsDto
)

data class MoveDetailsDto(
    val name: String,
    val url: String
)

data class SpeciesDto(
    val name: String,
    val url: String
)

data class CriesDto(
    val latest: String,
    val legacy: String
)

data class StatDto(
    val baseStat: Int,
    val effort: Int,
    val stat: StatDetailsDto
)

data class StatDetailsDto(
    val name: String,
    val url: String
)

data class TypeDto(
    val slot: Int,
    val type: TypeDetailsDto
)

data class TypeDetailsDto(
    val name: String,
    val url: String
)

data class GenerationDto(
    val name: String,
    val url: String
)

// sempre vem de: https://github.com/PokeAPI/sprites/blob/master/sprites/pokemon/*
data class SpritesDto (
    val back_default: String,
    val back_female: String,
    val back_shiny: String,
    val back_shiny_female: String,
    val front_default: String,
    val front_female: String,
    val front_shiny: String,
    val front_shiny_female: String,
)
