package com.barril.pokedexapp.data.local.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class PokemonTypeEntity(
    @PrimaryKey(autoGenerate = false)
    val typeName: String
)
