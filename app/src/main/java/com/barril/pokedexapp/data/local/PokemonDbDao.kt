package com.barril.pokedexapp.data.local

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface PokemonDbDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertPokemons(pokemons: List<PokemonEntity>)

    @Query("SELECT * FROM pokemonentity WHERE " +
            "name LIKE :queryString " +
            "ORDER BY name DESC")
    fun pokemonsByName(queryString: String): PagingSource<Int, PokemonEntity>

    @Query("SELECT * FROM pokemonentity " +
           "ORDER BY id DESC")
    fun allPokemons(): PagingSource<Int, PokemonEntity>

    @Query("SELECT * FROM pokemonentity " +
//            "WHERE isFavorite = 1 " +
            "ORDER BY id DESC")
    fun allFavoritePokemons(): PagingSource<Int, PokemonEntity>

//    @Query("DELETE FROM pokemonentity")
//    suspend fun clearAllPokemons()

    @Query("DELETE FROM pokemonentity") // WHERE isFavorite = 0
    suspend fun clearAllPokemonsExceptFavorites()

}