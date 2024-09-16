package com.barril.pokedexapp.data.local.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class NamedResourceEntity(
    @PrimaryKey(autoGenerate = false)
    val name: String,
    val url: String,
    val type: String // helditem, move
)
