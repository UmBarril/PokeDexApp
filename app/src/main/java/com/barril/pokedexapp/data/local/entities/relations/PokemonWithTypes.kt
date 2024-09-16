package com.barril.pokedexapp.data.local.entities.relations

import androidx.room.Embedded
import androidx.room.Junction
import androidx.room.Relation
import com.barril.pokedexapp.data.local.entities.PokemonEntity
import com.barril.pokedexapp.data.local.entities.PokemonTypeEntity

data class PokemonWithTypes (
    @Embedded
    val pokemonEntity: PokemonEntity,

    @Relation(
        parentColumn = "pokemonId",
        entityColumn = "typeName",
        associateBy = Junction(PokemonWithTypeCrossRef::class)
    )
    val types: List<PokemonTypeEntity>
)