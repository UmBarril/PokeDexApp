package com.barril.pokedexapp.data.local.entities.relations

import androidx.room.Entity

@Entity(primaryKeys = ["pokemonId", "name"])
data class PokemonWithHelditemCrossRef(
    val pokemonId: Int,
    val name: String
)
