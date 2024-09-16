package com.barril.pokedexapp.data.remote

import com.barril.pokedexapp.data.remote.dtos.PokemonDto
import com.barril.pokedexapp.data.remote.dtos.NamedApiResourceList
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
    ): NamedApiResourceList
//    ): Response<PokemonPageDto>

    @GET("type/?limit={limit}&offset={offset}")
    suspend fun getPokemonTypes(
        @Path("limit") limit: Int = 30,
        @Path("offset") offset: Int = 0
    ): NamedApiResourceList

}
