package com.barril.pokedexapp.data.local.entities.partial_entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class PokemonEntityUpdateFavorite(
    @PrimaryKey(autoGenerate = false)
    val pokemonId: Int,
    val isFavorite: Boolean?
)
