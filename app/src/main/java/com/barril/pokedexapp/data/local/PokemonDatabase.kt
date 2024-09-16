package com.barril.pokedexapp.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.barril.pokedexapp.data.local.entities.DatabaseMetadataEntity
import com.barril.pokedexapp.data.local.entities.NamedResourceEntity
import com.barril.pokedexapp.data.local.entities.PokemonAbilityEntity
import com.barril.pokedexapp.data.local.entities.PokemonEntity
import com.barril.pokedexapp.data.local.entities.PokemonStatEntity
import com.barril.pokedexapp.data.local.entities.PokemonTypeEntity
import com.barril.pokedexapp.data.local.entities.relations.PokemonWithHelditemCrossRef
import com.barril.pokedexapp.data.local.entities.relations.PokemonWithMovesCrossRef
import com.barril.pokedexapp.data.local.entities.relations.PokemonWithTypeCrossRef

@Database(
    entities = [
        PokemonEntity::class,
        PokemonStatEntity::class,
        PokemonTypeEntity::class,
        PokemonAbilityEntity::class,
        NamedResourceEntity::class,
        DatabaseMetadataEntity::class,
        PokemonWithTypeCrossRef::class,
        PokemonWithMovesCrossRef::class,
        PokemonWithHelditemCrossRef::class
    ],
    version = 1,
    exportSchema = false
)
abstract class PokemonDatabase : RoomDatabase() {

    abstract fun pokemonDbDao(): PokemonDbDao

}
