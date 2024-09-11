package com.barril.pokedexapp.data

import com.barril.pokedexapp.data.api.PokemonDto
import com.barril.pokedexapp.data.api.RetrofitInstance

class PokemonRepository {
//    fun getPokemons(amount: Int): Flow<List<PokemonDto>> {
//
//    }

    suspend fun searchPokemon(name: String): Pokemon? {
        val response = RetrofitInstance.apiImpl.getPokemon("bulbasaur")
        if (response.isSuccessful && response.body() != null) {
            return response.body()!!.toPokemon()
        }
        return null
    }

    fun getFavoritePokemons(amount: Int): List<PokemonDto> {
        TODO()
    }

    fun searchFavoritePokemon(name: String): List<PokemonDto> {
        TODO()
    }
}