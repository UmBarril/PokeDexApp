package com.barril.pokedexapp.domain


data class Pokemon(
    /**
     * Id oficial do pokémon
     */
    val id: Int,

    /**
     * Nome do pokémon
     */
    val name: String,

    /**
     * Altura dada em decímetros (0.1m)
     */
    val height: Int,

    /**
     * Ordem de organização (API)
     */
    val order: Int,

    /**
     * Peso dado em hectogramas (0.1kg)
     */
    val weight: Int,

    /**
     * Se este pokémon foi selecionado como favorito
     */
    val isFavorite: Boolean,

    /**
     * Gênero do pokémon para ser mostrado por padrão. (Alguns pokémons tem sprites diferentes para diferentes g6eneros)
     */
    val selectedGender: PokemonGender = PokemonGender.MALE,

    /**
     * Habilidade de batalha do pokémon
     */
    val abilities: List<PokemonAbility>,

    val heldItems: List<NamedResource>,
    val moves: List<NamedResource>,
    val sprites: PokemonSpritesResource,
    val cries: PokemonCriesResource,
    val stats: List<PokemonStat>,
    val types: Set<PokemonType>,
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
