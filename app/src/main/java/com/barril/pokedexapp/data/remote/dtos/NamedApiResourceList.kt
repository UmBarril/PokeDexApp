package com.barril.pokedexapp.data.remote.dtos

/**
 * Uma página da API contendo uma lista de Pokémons.
 */
data class NamedApiResourceList(
    val count: Int,
    val next: String?,
    val previous: String?,
    val results: List<NamedApiResourceDto> // name: pokemon name, url: pokemon url
)