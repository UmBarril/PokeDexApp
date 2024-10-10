package com.barril.pokedexapp.data.local.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class PokemonStatEntity(
    @PrimaryKey(autoGenerate = false)
    val pokemonId: Int,

    val name: String,
    val baseStat: Int,
    val effort: Int,
    val url: String
)
