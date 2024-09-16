package com.barril.pokedexapp.data.local.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class PokemonAbilityEntity(
    @PrimaryKey(autoGenerate = false)
    val pokemonId: Int,

    val name: String,
    val isHidden: Boolean,
    val url: String,
    val slot: Int
)
