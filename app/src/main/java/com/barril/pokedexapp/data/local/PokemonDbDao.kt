package com.barril.pokedexapp.data.local

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Query
import androidx.room.Transaction
import androidx.room.Update
import androidx.room.Upsert
import com.barril.pokedexapp.data.local.entities.DatabaseMetadataEntity
import com.barril.pokedexapp.data.local.entities.NamedResourceEntity
import com.barril.pokedexapp.data.local.entities.PokemonAbilityEntity
import com.barril.pokedexapp.data.local.entities.PokemonEntity
import com.barril.pokedexapp.data.local.entities.insert.PokemonInsertData
import com.barril.pokedexapp.data.local.entities.PokemonStatEntity
import com.barril.pokedexapp.data.local.entities.PokemonTypeEntity
import com.barril.pokedexapp.data.local.entities.partial_entities.PokemonEntityUpdateFavorite
import com.barril.pokedexapp.data.local.entities.relations.PokemonWithHelditemCrossRef
import com.barril.pokedexapp.data.local.entities.relations.PokemonWithMovesCrossRef
import com.barril.pokedexapp.data.local.entities.relations.PokemonWithRelations
import com.barril.pokedexapp.data.local.entities.relations.PokemonWithTypeCrossRef

// TODO: ler https://developer.android.com/training/data-storage/room/async-queries

//
@Dao
interface PokemonDbDao {

    /**
     * ATUALIZAÇÕES:
     */
    @Update(PokemonEntity::class)
    suspend fun updatePokemonFavoriteStatus(pokemonUpdate: PokemonEntityUpdateFavorite)

    /**
     * CONSULTAS:
     */
    @Query("SELECT * FROM databasemetadataentity WHERE " +
            "`key` = :key " +
            "LIMIT 1")
    suspend fun getMetadataByKey(key: String): DatabaseMetadataEntity?

    @Query("SELECT EXISTS(SELECT * FROM pokemonentity WHERE pokemonId = :pokemonId)")
    suspend fun isPokemonFavorite(pokemonId: Int): Boolean

    @Transaction
    @Query("SELECT * FROM pokemonentity WHERE " +
            "pokemonId = :id")
    fun getPokemonById(id: Int): PagingSource<Int, PokemonWithRelations>

    @Transaction
    @Query("SELECT * FROM pokemonentity ORDER BY " +
            "CASE WHEN :isAsc = 1 THEN pokemonId END ASC," +
            "CASE WHEN :isAsc = 0 THEN pokemonId END DESC")
    fun getAllPokemonsByPokemonId(isAsc: Boolean = true)
    : PagingSource<Int, PokemonWithRelations>

    @Transaction
    @Query("SELECT * FROM pokemonentity ORDER BY " +
            "CASE WHEN :isAsc = 1 THEN name END ASC," +
            "CASE WHEN :isAsc = 0 THEN name END DESC")
    fun getAllPokemonsByName(isAsc: Boolean = true)
            : PagingSource<Int, PokemonWithRelations>

    // futuro
//    @Transaction
//    @Query("SELECT * FROM pokemonentity pe " +
//            "INNER JOIN pokemonwithtypecrossref ptcr ON ptcr.pokemonId = pe.pokemonId " +
//            "INNER JOIN pokemontypeentity pte ON pte.typeName = ptcr.typeName " +
//            "WHERE typeName LIKE :queryString " +
//            "ORDER BY " +
//            "CASE WHEN :isAsc = 1 THEN pte.typeName END ASC," +
//            "CASE WHEN :isAsc = 0 THEN pte.typeName END DESC")
//    fun getPokemonsByType(isAsc: Boolean = true, queryString: String): PagingSource<Int, PokemonWithTypeCrossRef>

    @Transaction
    @Query("SELECT * FROM pokemonentity pe " +
            "INNER JOIN pokemonwithtypecrossref ptcr ON ptcr.pokemonId = pe.pokemonId " +
            "INNER JOIN pokemontypeentity pte ON pte.typeName = ptcr.typeName " +
            "ORDER BY " +
            "CASE WHEN :isAsc = 1 THEN pte.typeName END ASC," +
            "CASE WHEN :isAsc = 0 THEN pte.typeName END DESC")
    fun getPokemonsByType(isAsc: Boolean = true, ): PagingSource<Int, PokemonWithRelations>

    @Transaction
    @Query("SELECT * FROM pokemonentity pe " +
            "INNER JOIN pokemonwithtypecrossref ptcr ON ptcr.pokemonId = pe.pokemonId " +
            "INNER JOIN pokemontypeentity pte ON pte.typeName = ptcr.typeName " +
            "ORDER BY " +
            "CASE WHEN :isAsc = 1 THEN pte.typeName END ASC," +
            "CASE WHEN :isAsc = 0 THEN pte.typeName END DESC")
    fun getAllPokemonsByType(isAsc: Boolean = true): PagingSource<Int, PokemonWithRelations>

    @Transaction
    @Query("SELECT * FROM pokemonentity WHERE " +
            "name LIKE :queryString AND " +
            "isFavorite = 1 ")
    fun getFavoritePokemonsByName(queryString: String): PagingSource<Int, PokemonWithRelations>

    @Transaction
    @Query("SELECT * FROM pokemonentity WHERE " +
            "name LIKE :queryString")
    fun getPokemonsByName(queryString: String): PagingSource<Int, PokemonWithRelations>

    @Transaction
    @Query("SELECT * FROM pokemonentity " +
            "WHERE isFavorite = 1 " +
            "ORDER BY pokemonId DESC")
    fun getAllFavoritePokemons(): PagingSource<Int, PokemonWithRelations>

    /**
     * REMOÇÕES:
     */
    // FIXME: até o meu conhecimento isso aqui não faz muita coisa (falta limpar as outras tabelas e o cache de imagens do glide)
    // aparentemente tem que colocar ForeignKey em tudo para isso funcionar
    @Query("DELETE FROM pokemonentity WHERE isFavorite = NULL OR isFavorite = 0")
    suspend fun clearAllPokemonsExceptFavorites()

    /**
     * INSERÇÕES:
     */
    @Transaction
    suspend fun insertPokemonData(pokemonInsertData: List<PokemonInsertData>) {
        for (pokemon in pokemonInsertData) {
            // Se o pokemon é favorito e existe, não atualizar ele
            // Caso não, pokémons favoritos podem ter o isFavorite sobrescrito
            if (isPokemonFavorite(pokemon.pokemonEntity.pokemonId)) {
                continue
            }
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