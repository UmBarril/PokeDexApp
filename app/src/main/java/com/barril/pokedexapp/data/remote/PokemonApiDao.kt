package com.barril.pokedexapp.data.remote

import com.barril.pokedexapp.data.remote.dtos.PokemonDto
import com.barril.pokedexapp.data.remote.dtos.NamedApiResourceList
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface PokemonApiDao {

    @GET("pokemon/{id}")
    suspend fun getPokemon(
        @Path("id") pokemonId: String
    ): PokemonDto

    @GET("pokemon")
    suspend fun getPokemonPage(
        @Query("limit") limit: Int,
        @Query("offset") offset: Int
    ): NamedApiResourceList
//    ): Response<PokemonPageDto>

    @GET("type")
    suspend fun getPokemonTypes(
        @Query("limit") limit: Int = 30,
        @Query("offset") offset: Int = 0
    ): NamedApiResourceList

}
