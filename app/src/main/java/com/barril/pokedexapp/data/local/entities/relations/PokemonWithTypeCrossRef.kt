package com.barril.pokedexapp.data.local.entities.relations

import androidx.room.Entity

@Entity(primaryKeys = ["pokemonId", "typeName"])
data class PokemonWithTypeCrossRef(
    val pokemonId: Int,
    val typeName: String
)
