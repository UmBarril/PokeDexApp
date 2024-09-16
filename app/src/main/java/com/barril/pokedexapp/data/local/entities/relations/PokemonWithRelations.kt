package com.barril.pokedexapp.data.local.entities.relations

import androidx.room.Embedded
import androidx.room.Junction
import androidx.room.Relation
import com.barril.pokedexapp.data.local.entities.NamedResourceEntity
import com.barril.pokedexapp.data.local.entities.PokemonAbilityEntity
import com.barril.pokedexapp.data.local.entities.PokemonEntity
import com.barril.pokedexapp.data.local.entities.PokemonStatEntity
import com.barril.pokedexapp.data.local.entities.PokemonTypeEntity

/**
 * Pokémon com todas as Relations EXCETO pelo favorito.
 */
data class PokemonWithRelations (
    @Embedded val pokemonEntity: PokemonEntity,

    @Relation(
        parentColumn = "pokemonId",
        entityColumn = "typeName",
        associateBy = Junction(PokemonWithTypeCrossRef::class)
    )
    val types: List<PokemonTypeEntity>,

    @Relation(
        parentColumn = "pokemonId",
        entityColumn = "name",
        associateBy = Junction(PokemonWithHelditemCrossRef::class)
    )
    val heldItems: List<NamedResourceEntity>,

    @Relation(
        parentColumn = "pokemonId",
        entityColumn = "name",
        associateBy = Junction(PokemonWithMovesCrossRef::class)
    )
    val moves: List<NamedResourceEntity>,

    @Relation(parentColumn = "pokemonId", entityColumn = "pokemonId")
    val abilities: List<PokemonAbilityEntity>,

    @Relation(parentColumn = "pokemonId", entityColumn = "pokemonId")
    val stats: List<PokemonStatEntity>,
)