package com.barril.pokedexapp.data.remote

data class PokemonPageDto(
    val count: Int,
    val next: String?,
    val previous: String?,
    val results: List<NamedApiResourceDto> // name: pokemon name, url: pokemon url
)