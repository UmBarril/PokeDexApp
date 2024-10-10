package com.barril.pokedexapp.data.local.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class DatabaseMetadataEntity (
    @PrimaryKey(autoGenerate = false)
    val key: String,
    val value: String?
)