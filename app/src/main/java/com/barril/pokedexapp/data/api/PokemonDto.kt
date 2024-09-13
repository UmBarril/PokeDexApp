package com.barril.pokedexapp.data.api

data class PokemonDto(
    val abilities: List<PokemonAbilityDto>,
    val base_experience: Int,
    val cries: PokemonCriesDto,
    val forms: List<NamedApiResourceDto>,
    val game_indices: List<VersionGameIndexDto>,
    val height: Int,
    val held_items: List<PokemonHeldItemDto>,
    val id: Int,
    val is_default: Boolean,
    val location_area_encounters: String,
    val moves: List<PokemonMoveDto>,
    val name: String,
    val order: Int,

    @Transient
    val past_abilities: List<Any?>,

    val past_types: List<PokemonTypePastDto>,
    val species: NamedApiResourceDto,
    val sprites: PokemonSpritesDto,
    val stats: List<PokemonStatDto>,
    val types: List<PokemonTypeDto>,
    val weight: Int
)

data class PokemonAbilityDto(
    val ability: NamedApiResourceDto,
    val is_hidden: Boolean,
    val slot: Int
)

data class PokemonCriesDto(
    val latest: String,
    val legacy: String
)

data class VersionGameIndexDto(
    val game_index: Int,
    val version: NamedApiResourceDto
)

data class PokemonHeldItemDto(
    val item: NamedApiResourceDto,
    val version_details: List<VersionDetailDto>
)

data class PokemonMoveDto(
    val move: NamedApiResourceDto,
    val version_group_details: List<VersionGroupDetailDto>
)

data class PokemonTypePastDto(
    val generation: NamedApiResourceDto,
    val types: List<PokemonTypeDto>
)

data class PokemonSpritesDto(
    val back_default: String?,
    val back_female: String?,
    val back_shiny: String?,
    val back_shiny_female: String?,
    val front_default: String?,
    val front_female: String?,
    val front_shiny: String?,
    val front_shiny_female: String?,

    @Transient
    val other: List<PokemonSpritesDto>,
    @Transient
    val versions: Any?
)

data class PokemonStatDto(
    val base_stat: Int,
    val effort: Int,
    val stat: NamedApiResourceDto
)

data class PokemonTypeDto(
    val slot: Int,
    val type: NamedApiResourceDto
)

data class VersionDetailDto(
    val rarity: Int,
    val version: NamedApiResourceDto
)

data class VersionGroupDetailDto(
    val level_learned_at: Int,
    val move_learn_method: NamedApiResourceDto,
    val version_group: NamedApiResourceDto
)

data class NamedApiResourceDto(
    val name: String,
    val url: String
)