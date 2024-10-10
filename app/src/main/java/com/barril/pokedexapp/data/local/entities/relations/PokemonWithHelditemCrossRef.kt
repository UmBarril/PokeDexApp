package com.barril.pokedexapp.data.local.entities.relations

import androidx.room.ColumnInfo
import androidx.room.Entity

@Entity(primaryKeys = ["pokemonId", "name"])
data class PokemonWithHelditemCrossRef(
    @ColumnInfo(index = true)
    val pokemonId: Int,
    @ColumnInfo(index = true)
    val name: String
)
