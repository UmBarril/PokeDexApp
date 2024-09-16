package com.barril.pokedexapp.data.local

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Query
import androidx.room.Transaction
import androidx.room.Upsert
import com.barril.pokedexapp.data.local.entities.DatabaseMetadataEntity
import com.barril.pokedexapp.data.local.entities.NamedResourceEntity
import com.barril.pokedexapp.data.local.entities.PokemonAbilityEntity
import com.barril.pokedexapp.data.local.entities.PokemonEntity
import com.barril.pokedexapp.data.local.entities.insert.PokemonInsertData
import com.barril.pokedexapp.data.local.entities.PokemonStatEntity
import com.barril.pokedexapp.data.local.entities.PokemonTypeEntity
import com.barril.pokedexapp.data.local.entities.relations.PokemonWithHelditemCrossRef
import com.barril.pokedexapp.data.local.entities.relations.PokemonWithMovesCrossRef
import com.barril.pokedexapp.data.local.entities.relations.PokemonWithRelations
import com.barril.pokedexapp.data.local.entities.relations.PokemonWithTypeCrossRef

@Dao
interface PokemonDbDao {

    /**
     * CONSULTAS:
     */

    @Query("SELECT * FROM pokemonentity WHERE " +
            "name LIKE :queryString " +
            "ORDER BY name DESC")
    fun pokemonsByName(queryString: String): PagingSource<Int, PokemonWithRelations>

    @Query("SELECT * FROM pokemonentity " +
            "ORDER BY pokemonId DESC")
    fun allPokemons(): PagingSource<Int, PokemonWithRelations>

    @Query("SELECT * FROM pokemonentity " +
//            "WHERE isFavorite = 1 " +
            "ORDER BY pokemonId DESC")
    fun allFavoritePokemons(): PagingSource<Int, PokemonWithRelations>

//    @Query("DELETE FROM pokemonentity")
//    suspend fun clearAllPokemons()

    @Query("DELETE FROM pokemonentity WHERE isFavorite = NULL OR isFavorite = 0")
    suspend fun clearAllPokemonsExceptFavorites()

    /**
     * INSERÇÕES:
     */

    @Transaction
    suspend fun insertPokemonData(pokemonInsertData: List<PokemonInsertData>) {
        pokemonInsertData.forEach { pokemon ->
            insertPokemonEntity(pokemon.pokemonEntity)
            insertPokemonMoves(pokemon.moves)
            insertPokemonHeldItems(pokemon.heldItems)
            insertPokemonStats(pokemon.stats)
            insertPokemonTypes(pokemon.types)
            insertPokemonAbilities(pokemon.abilities)

            pokemon.types.forEach {
                insertPokemonWithTypeCrossRef(
                    PokemonWithTypeCrossRef(
                        pokemonId = pokemon.pokemonEntity.pokemonId,
                        typeName = it.typeName
                    )
                )
            }
            pokemon.moves.forEach {
                insertPokemonWithMovesCrossRef(
                    PokemonWithMovesCrossRef (
                        pokemonId = pokemon.pokemonEntity.pokemonId,
                        name = it.name
                    )
                )
            }
            pokemon.heldItems.forEach {
                insertPokemonWithHelditemCrossRef(
                    PokemonWithHelditemCrossRef (
                        pokemonId = pokemon.pokemonEntity.pokemonId,
                        name = it.name
                    )
                )
            }
        }
    }

    /**
     * Insere os dados dos Pokémons nas tabelas e atualiza seus valores quando já existem.
     * Se um dos campos inseridos for NULL, isto fára com que o campo não seja atualizado.
     */
    @Upsert
    suspend fun insertPokemonEntity(pokemonEntity: PokemonEntity)

    @Upsert
    suspend fun insertPokemonMoves(pokemonMoves: List<NamedResourceEntity>)

    @Upsert
    suspend fun insertPokemonHeldItems(pokemonHeldItems: List<NamedResourceEntity>)

    @Upsert
    suspend fun insertPokemonStats(pokemonStatEntity: List<PokemonStatEntity>)

    @Upsert
    suspend fun insertPokemonTypes(pokemonType: List<PokemonTypeEntity>)

    @Upsert
    suspend fun insertPokemonAbilities(pokemonAbilityEntity: List<PokemonAbilityEntity>)

    @Upsert
    suspend fun insertPokemonWithTypeCrossRef(typeCrossRef: PokemonWithTypeCrossRef)

    @Upsert
    suspend fun insertPokemonWithHelditemCrossRef(helditemCrossRef: PokemonWithHelditemCrossRef)

    @Upsert
    suspend fun insertPokemonWithMovesCrossRef(movesCrossRef: PokemonWithMovesCrossRef)

    @Upsert
    fun insertDatabaseMeta(meta: DatabaseMetadataEntity)

}