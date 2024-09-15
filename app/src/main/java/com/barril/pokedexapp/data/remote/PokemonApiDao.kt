package com.barril.pokedexapp.data.remote

import retrofit2.http.GET
import retrofit2.http.Path

interface PokemonApiDao {

    @GET("pokemon/{id}")
    suspend fun getPokemon(
        @Path("id") pokemonId: String
    ): PokemonDto

    @GET("pokemon/?limit={limit}&offset={offset}")
    suspend fun getPokemonPage(
        @Path("limit") limit: Int,
        @Path("offset") offset: Int
    ): PokemonPageDto
//    ): Response<PokemonPageDto>



}
