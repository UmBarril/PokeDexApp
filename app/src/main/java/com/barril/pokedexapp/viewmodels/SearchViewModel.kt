package com.barril.pokedexapp.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.cachedIn
import androidx.paging.map
import com.barril.pokedexapp.data.local.PokemonDatabase
import com.barril.pokedexapp.data.local.entities.relations.PokemonWithRelations
import com.barril.pokedexapp.data.toPokemon
import com.barril.pokedexapp.di.AppModuleImpl.Companion.POKEAPI_PAGE_SIZE
import com.barril.pokedexapp.domain.Pokemon
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.runBlocking

/**
 * SearchViewModel para ser usado na SearchScreen.
 */
class SearchViewModel(
    val database: PokemonDatabase
): ViewModel() {

    private var lastQuery = ""

    private lateinit var searchPager: Pager<Int, PokemonWithRelations>

    var searchResultFlow: Flow<PagingData<Pokemon>> = searchPokemonPager("", false)
                                                        .flow
                                                        .map { pagingData ->
                                                            pagingData.map { it.toPokemon() }
                                                        }
                                                        .cachedIn(viewModelScope)
        private set

    fun updateSearchResultsFlow(query: String, onlyFavorites: Boolean = false) {
        searchResultFlow = searchPokemonPager(query, onlyFavorites)
            .flow
            .map { pagingData ->
                pagingData.map { it.toPokemon() }
            }
            .cachedIn(viewModelScope)
    }

    private fun searchPokemonPager(
        query: String,
        onlyFavorites: Boolean
    ): Pager<Int, PokemonWithRelations> {
        if (query == lastQuery && ::searchPager.isInitialized) {
            return searchPager
        }
        lastQuery = query
        searchPager = Pager(
            config = PagingConfig(pageSize = POKEAPI_PAGE_SIZE),
            pagingSourceFactory = {
                if (onlyFavorites) {
                    database.pokemonDbDao().getFavoritePokemonsByName("%$query%")
                } else {
                    database.pokemonDbDao().getPokemonsByName("%$query%")
                }
            }
        )
        return searchPager
    }
}