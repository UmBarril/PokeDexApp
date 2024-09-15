package com.barril.pokedexapp.data

import com.barril.pokedexapp.data.remote.PokemonApiDao
import com.barril.pokedexapp.data.remote.PokemonDto
import com.barril.pokedexapp.data.local.PokemonDatabase
import com.barril.pokedexapp.domain.Pokemon
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

//class PokemonRepository(
//    val api: PokemonApiDao,
//    val db: PokemonDatabase
//) {
//
//    suspend fun getPokemonByNameOrId(name: String): Pokemon? {
//        val response = api.getPokemon("bulbasaur")
//        if (response.isSuccessful && response.body() != null) {
//            return response.body()!!.toPokemon()
//        }
//        return null
//    }
//
//    suspend fun getPokemonPage(page: Int, limit: Int): Flow<Pokemon?> = flow {
//        val response = api.getPokemonPage(limit, limit * page)
//        if (response.isSuccessful && response.body() != null) {
//            response.body()!!.results.forEach {
//                emit(getPokemonByNameOrId(it.name))
//            }
//        }
//    }
//
//    fun getFavoritePokemons(amount: Int): List<PokemonDto> {
//        TODO()
//    }
//
//    fun searchFavoritePokemon(name: String): List<PokemonDto> {
//        TODO()
//    }
//}