package com.barril.pokedexapp.data.local.entities

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.Relation
import com.barril.pokedexapp.data.local.entities.embeded.PokemonCriesResourceEmbeded
import com.barril.pokedexapp.data.local.entities.embeded.PokemonSpritesResourceEmbeded
import com.barril.pokedexapp.domain.PokemonType
import java.util.EnumSet

@Entity
data class PokemonEntity(
    @PrimaryKey(autoGenerate = false)
    val pokemonId: Int,

    val name: String,
    val height: Int,
    val order: Int,
    val weight: Int,
    val isFavorite: Boolean?,

    @Embedded
    val sprites: PokemonSpritesResourceEmbeded,
    @Embedded
    val cries: PokemonCriesResourceEmbeded
)

