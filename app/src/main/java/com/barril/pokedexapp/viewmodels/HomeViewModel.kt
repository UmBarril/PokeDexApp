package com.barril.pokedexapp.viewmodels

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.ExperimentalPagingApi
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.cachedIn
import androidx.paging.filter
import androidx.paging.map
import androidx.room.Index
import com.barril.pokedexapp.data.local.PokemonDatabase
import com.barril.pokedexapp.data.local.entities.relations.PokemonWithRelations
import com.barril.pokedexapp.data.paging.PokemonPagingMediator
import com.barril.pokedexapp.data.remote.PokemonApiDao
import com.barril.pokedexapp.data.toPokemon
import com.barril.pokedexapp.di.AppModuleImpl.Companion.POKEAPI_PAGE_SIZE
import com.barril.pokedexapp.domain.Pokemon
import com.barril.pokedexapp.domain.PokemonOrderingColumn
import com.barril.pokedexapp.domain.PokemonType
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch

class HomeViewModel(
    private val database: PokemonDatabase,
    private val api: PokemonApiDao
) : ViewModel() {
    // TODO: transformar isso num MutableFlowState
    var orderingBy by mutableStateOf(PokemonOrderingColumn.POKEMON_ID)
    var currentPokemonOrderingDirection by mutableStateOf(Index.Order.ASC)
    var selectedFilterType by mutableStateOf<PokemonType?>(null)

    private lateinit var _pokemonPager: Pager<Int, PokemonWithRelations>
    private lateinit var pokemonPagingFlow: Flow<PagingData<Pokemon>>

    private fun getPokemonPager(): Pager<Int, PokemonWithRelations> {
        if (!::_pokemonPager.isInitialized) {
            refreshLazyColumn()
        }
        return _pokemonPager
    }

    fun getPokemonPagingFlow(): Flow<PagingData<Pokemon>> {
        if (::pokemonPagingFlow.isInitialized) {
            return pokemonPagingFlow
        }
        updatePagingFlow()
        return pokemonPagingFlow
    }

    fun refreshLazyColumn() {
        updatePokemonPager()
        updatePagingFlow()
    }

    fun clearCache() {
        viewModelScope.launch {
            database.pokemonDbDao().clearAllPokemonsExceptFavorites()
        }
    }

    private fun updatePagingFlow() {
        val pagerMapped = getPokemonPager()
            .flow
            .map { pagingData ->
                pagingData.map { it.toPokemon() }
            }

        pokemonPagingFlow = if (selectedFilterType != null) {
            // filtra qualquer pokémon que tenha o tipo especificado
            pagerMapped.map { pagingData ->
                pagingData.filter { !it.types.contains(selectedFilterType) }
            }.cachedIn(viewModelScope)
        } else {
            pagerMapped.cachedIn(viewModelScope)
        }
    }

    @OptIn(ExperimentalPagingApi::class)
    private fun updatePokemonPager() {
        _pokemonPager = Pager(
            config = PagingConfig(pageSize = POKEAPI_PAGE_SIZE),
            remoteMediator = PokemonPagingMediator(
                database = database,
                api = api
            ),
            pagingSourceFactory = {
                val isAsc = currentPokemonOrderingDirection == Index.Order.ASC
                when (orderingBy) {
                    PokemonOrderingColumn.POKEMON_ID -> {
                        database.pokemonDbDao().getAllPokemonsByPokemonId(
                            isAsc = isAsc
                        )
                    }
                    PokemonOrderingColumn.TYPE -> {
                        database.pokemonDbDao().getAllPokemonsByType(
                            isAsc = isAsc
                        )
                    }
                    PokemonOrderingColumn.NAME -> {
                        database.pokemonDbDao().getAllPokemonsByName(
                            isAsc = isAsc
                        )
                    }
                }
            }
        )

    }
}