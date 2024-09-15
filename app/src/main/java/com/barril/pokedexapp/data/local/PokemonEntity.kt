package com.barril.pokedexapp.data.local

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.Relation

@Entity
data class PokemonEntity(
    @PrimaryKey
    val id: Int,

    val name: String,
    val height: Int,
    val order: Int,
    val weight: Int,

    @Relation(parentColumn = "id", entityColumn = "id", entity = PokemonAbilityEntity::class)
    val abilities: List<PokemonAbilityEntity>,

    @Relation(parentColumn = "id", entityColumn = "id", entity = NamedResourceEntity::class)
    val heldItems: List<NamedResourceEntity>,

    @Relation(parentColumn = "id", entityColumn = "id", entity = NamedResourceEntity::class)
    val moves: List<NamedResourceEntity>,

    @Relation(parentColumn = "id", entityColumn = "id", entity = PokemonStatEntity::class)
    val stats: List<PokemonStatEntity>,

    @Embedded
    val sprites: PokemonSpritesResourceEntity,
    @Embedded
    val cries: PokemonCriesResourceEntity,

    val types: List<String>,
)

data class PokemonAbilityEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,

    val name: String,
    val isHidden: Boolean,
    val url: String,
    val slot: Int
)

data class NamedResourceEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,

    val name: String,
    val url: String
)

data class PokemonStatEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,

    val name: String,
    val baseStat: Int,
    val effort: Int,
    val url: String
)

data class PokemonCriesResourceEntity(
    val latestUrl: String,
    val legacyUrl: String
)

data class PokemonSpritesResourceEntity(
    val backDefaultUrl: String? = null,
    val backFemaleUrl: String? = null,
    val backShinyUrl: String? = null,
    val backShinyFemaleUrl: String? = null,
    val frontDefaultUrl: String? = null,
    val frontFemaleUrl: String? = null,
    val frontShinyUrl: String? = null,
    val frontShinyFemaleUrl: String? = null,
)
