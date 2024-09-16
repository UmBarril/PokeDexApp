package com.barril.pokedexapp.data.local.entities.insert

import com.barril.pokedexapp.data.local.entities.NamedResourceEntity
import com.barril.pokedexapp.data.local.entities.PokemonAbilityEntity
import com.barril.pokedexapp.data.local.entities.PokemonEntity
import com.barril.pokedexapp.data.local.entities.PokemonStatEntity
import com.barril.pokedexapp.data.local.entities.PokemonTypeEntity

data class PokemonInsertData (
    val pokemonEntity: PokemonEntity,
    val types: List<PokemonTypeEntity>,
    val abilities: List<PokemonAbilityEntity>,
    val heldItems: List<NamedResourceEntity>,
    val moves: List<NamedResourceEntity>,
    val stats: List<PokemonStatEntity>,
)
