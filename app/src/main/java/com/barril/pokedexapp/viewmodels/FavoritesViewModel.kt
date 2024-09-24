package com.barril.pokedexapp.viewmodels

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.ExperimentalPagingApi
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.cachedIn
import androidx.paging.map
import com.barril.pokedexapp.data.local.PokemonDatabase
import com.barril.pokedexapp.data.local.entities.partial_entities.PokemonEntityUpdateFavorite
import com.barril.pokedexapp.data.local.entities.relations.PokemonWithRelations
import com.barril.pokedexapp.data.paging.PokemonPagingMediator
import com.barril.pokedexapp.data.remote.PokemonApiDao
import com.barril.pokedexapp.data.toPokemon
import com.barril.pokedexapp.di.AppModuleImpl.Companion.POKEAPI_PAGE_SIZE
import com.barril.pokedexapp.domain.Pokemon
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

class FavoritesViewModel(
    private val database: PokemonDatabase,
    private val api: PokemonApiDao
) : ViewModel() {

    @OptIn(ExperimentalPagingApi::class)
    private val favoritePokemonPager: Pager<Int, PokemonWithRelations> by lazy {
        Pager(
            config = PagingConfig(pageSize = POKEAPI_PAGE_SIZE),
            remoteMediator = PokemonPagingMediator(
                database = database,
                api = api
            ),
            pagingSourceFactory = {
                runBlocking { // TODO: ver solução para não usar runBlocking
                    database.pokemonDbDao().getAllFavoritePokemons()
                }
            }
        )
    }

    val favoritePokemonPagingFlow by lazy {
        favoritePokemonPager
            .flow
            .map { pagingData ->
                pagingData.map { it.toPokemon() }
            }
            .cachedIn(viewModelScope)
    }

    fun updatePokemonAsFavorite(pokemon: Pokemon, favorite: Boolean) {
        viewModelScope.launch {
            database.pokemonDbDao().updatePokemonFavoriteStatus(
                PokemonEntityUpdateFavorite(
                    pokemonId = pokemon.id,
                    isFavorite = favorite
                )
            )
        }
        if (favorite) {
            newFavorites++
        }
    }

    var newFavorites by mutableIntStateOf(0)
        private set

    fun flushNewFavorites() {
        newFavorites = 0
    }

}