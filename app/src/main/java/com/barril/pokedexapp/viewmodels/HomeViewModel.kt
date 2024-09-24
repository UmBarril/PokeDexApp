package com.barril.pokedexapp.viewmodels

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.ExperimentalPagingApi
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.cachedIn
import androidx.paging.map
import androidx.room.Index
import com.barril.pokedexapp.data.local.PokemonDatabase
import com.barril.pokedexapp.data.local.entities.DatabaseMetadataEntity
import com.barril.pokedexapp.data.local.entities.relations.PokemonWithRelations
import com.barril.pokedexapp.data.paging.PokemonPagingMediator
import com.barril.pokedexapp.data.remote.PokemonApiDao
import com.barril.pokedexapp.data.toPokemon
import com.barril.pokedexapp.data.toPokemonInsertData
import com.barril.pokedexapp.di.AppModuleImpl.Companion.POKEAPI_PAGE_SIZE
import com.barril.pokedexapp.domain.PokemonOrderingColumn
import com.barril.pokedexapp.domain.PokemonType
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

class HomeViewModel(
    private val database: PokemonDatabase,
    private val api: PokemonApiDao
) : ViewModel() {
    init {
        viewModelScope.launch {
            val result = database.pokemonDbDao().getMetadataByKey("loadedAllPokemons")
            hasLoadedAllPokemons = result?.value == "true"
        }
    }

    // TODO: transformar isso num MutableFlowState
    var orderingBy by mutableStateOf(PokemonOrderingColumn.POKEMON_ID)
    var currentPokemonOrderingDirection by mutableStateOf(Index.Order.ASC)
    val selectedFilterTypes = mutableStateListOf<PokemonType>()

    @OptIn(ExperimentalPagingApi::class)
    private val pokemonPager: Pager<Int, PokemonWithRelations> by lazy {
        Pager(
            config = PagingConfig(pageSize = POKEAPI_PAGE_SIZE),
            remoteMediator = PokemonPagingMediator(
                database = database,
                api = api
            ),
            pagingSourceFactory = {
                runBlocking { // TODO: ver solução para não usar runBlocking
                    database.pokemonDbDao().getAllPokemons()
                }
            }
        )
    }

    val pokemonPagingFlow by lazy {
        pokemonPager
            .flow
            .map { pagingData ->
                pagingData.map { it.toPokemon() }
            }
            .cachedIn(viewModelScope)
    }

    var hasLoadedAllPokemons: Boolean = false
        private set

    fun loadAllPokemons() {
        if (hasLoadedAllPokemons) {
            throw AllPokemonsAlreadyLoadedException()
        }
        viewModelScope.launch {
            val response = api.getPokemonPage(10000, 0)
            val pokemonDtos = response.results.map {
                api.getPokemon(it.name)
            }

            val pokemonInsertData = pokemonDtos.map {
                it.toPokemonInsertData()
            }
            database.pokemonDbDao().insertPokemonData(pokemonInsertData)
        }
        database.pokemonDbDao().insertDatabaseMeta(
            DatabaseMetadataEntity("loadedAllPokemons", "true")
        )
        hasLoadedAllPokemons = true
    }

    // TODO: usar api para fazer reload arrastando para baixo
    fun clearCache() {
        viewModelScope.launch {
            database.pokemonDbDao().clearAllPokemonsExceptFavorites()
        }
    }
}