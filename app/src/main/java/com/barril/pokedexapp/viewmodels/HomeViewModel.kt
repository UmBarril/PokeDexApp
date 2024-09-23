package com.barril.pokedexapp.viewmodels

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.ExperimentalPagingApi
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.cachedIn
import androidx.paging.map
import androidx.room.Index
import com.barril.pokedexapp.data.local.PokemonDatabase
import com.barril.pokedexapp.data.local.entities.DatabaseMetadataEntity
import com.barril.pokedexapp.data.local.entities.partial_entities.PokemonEntityUpdateFavorite
import com.barril.pokedexapp.data.local.entities.relations.PokemonWithRelations
import com.barril.pokedexapp.data.paging.PokemonPagingMediator
import com.barril.pokedexapp.data.remote.PokemonApiDao
import com.barril.pokedexapp.data.toPokemon
import com.barril.pokedexapp.data.toPokemonInsertData
import com.barril.pokedexapp.di.AppModuleImpl.Companion.POKEAPI_PAGE_SIZE
import com.barril.pokedexapp.domain.Pokemon
import com.barril.pokedexapp.domain.PokemonType
import kotlinx.coroutines.flow.Flow
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

    var searchResultFlow: Flow<PagingData<Pokemon>>? = null
        private set

    private var lastQuery = ""
    private var searchPager: Pager<Int, PokemonWithRelations>? = null

    private fun searchPokemonPager(query: String): Pager<Int, PokemonWithRelations>? {
        if (query == lastQuery) {
            return searchPager
        }
        lastQuery = query
        return Pager(
            config = PagingConfig(pageSize = POKEAPI_PAGE_SIZE),
            pagingSourceFactory = {
                runBlocking { // TODO: ver solução para não usar runblocking
                    database.pokemonDbDao().getPokemonsByName(query)
                }
            }
        )
    }

    fun updateSearchResultsFlow(query: String) {
        searchResultFlow = searchPokemonPager(query)
            ?.flow
            ?.map { pagingData ->
                pagingData.map { it.toPokemon() }
            }
            ?.cachedIn(viewModelScope)
    }

    val pokemonPagingFlow by lazy {
        pokemonPager
            .flow
            .map { pagingData ->
                pagingData.map { it.toPokemon() }
            }
            .cachedIn(viewModelScope)
    }

    var newFavorites by mutableIntStateOf(0)
        private set

    var hasLoadedAllPokemons: Boolean = false
        private set

    fun flushNewFavorites() {
        newFavorites = 0
    }

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

    // TODO: usar api para fazer reload arrastando para baixo
    fun clearCache() {
        viewModelScope.launch {
            database.pokemonDbDao().clearAllPokemonsExceptFavorites()
        }
    }
}