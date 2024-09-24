package com.barril.pokedexapp.viewmodels

import androidx.lifecycle.ViewModel

class SettingsViewModel : ViewModel() {

//    init {
//        viewModelScope.launch {
//            val result = database.pokemonDbDao().getMetadataByKey("loadedAllPokemons")
//            hasLoadedAllPokemons = result?.value == "true"
//        }
//    }
//    var hasLoadedAllPokemons: Boolean = false
//        private set
//
//    fun loadAllPokemons() {
//        if (hasLoadedAllPokemons) {
//            throw AllPokemonsAlreadyLoadedException()
//        }
//        viewModelScope.launch {
//            val response = api.getPokemonPage(10000, 0)
//            val pokemonDtos = response.results.map {
//                api.getPokemon(it.name)
//            }
//
//            val pokemonInsertData = pokemonDtos.map {
//                it.toPokemonInsertData()
//            }
//            database.pokemonDbDao().insertPokemonData(pokemonInsertData)
//        }
//        database.pokemonDbDao().insertDatabaseMeta(
//            DatabaseMetadataEntity("loadedAllPokemons", "true")
//        )
//        hasLoadedAllPokemons = true
//    }

}