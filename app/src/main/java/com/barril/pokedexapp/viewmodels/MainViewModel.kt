package com.barril.pokedexapp.viewmodels

import androidx.annotation.StringRes
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateMapOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.res.stringResource
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.paging.ExperimentalPagingApi
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.cachedIn
import androidx.paging.map
import androidx.room.Index
import com.barril.pokedexapp.R
import com.barril.pokedexapp.data.PokemonPagingMediator
import com.barril.pokedexapp.data.local.PokemonDatabase
import com.barril.pokedexapp.data.local.entities.DatabaseMetadataEntity
import com.barril.pokedexapp.data.local.entities.partial_entities.PokemonEntityUpdateFavorite
import com.barril.pokedexapp.data.local.entities.relations.PokemonWithRelations
import com.barril.pokedexapp.data.remote.PokemonApiDao
import com.barril.pokedexapp.data.toPokemon
import com.barril.pokedexapp.data.toPokemonInsertData
import com.barril.pokedexapp.di.AppModuleImpl.Companion.POKEAPI_PAGE_SIZE
import com.barril.pokedexapp.domain.Pokemon
import com.barril.pokedexapp.domain.PokemonType
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import java.util.EnumSet

enum class PokemonOrderingColumn(
    val databaseName: String,
    @StringRes val description: Int
) {
    POKEMON_ID("pokemonId", R.string.order_by_ids),
//    GENERATION("", stringResource(R.string.order_by_gen)),
    TYPE("typeName", R.string.order_by_type),
    NAME("name", R.string.order_by_name)
}

class MainViewModel(
    private val database: PokemonDatabase,
    private val api: PokemonApiDao
) : ViewModel() {

    init {
        viewModelScope.launch {
            val result = database.pokemonDbDao().metadataByKey("loadedAllPokemons")
            hasLoadedAllPokemons = result?.value == "true"
        }
    }

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
                database.pokemonDbDao().allPokemons()
            }
        )
    }

    val pokemonPagingFlow = pokemonPager
        .flow
        .map { pagingData ->
            pagingData.map { it.toPokemon() }
        }
        .cachedIn(viewModelScope)

    var newFavorites by mutableIntStateOf(0)
        private set


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
        var hasLoadedAllPokemons = true
    }

//    fun searchPokemonByName(pokemonName: String): List<Pokemon> {
////        database.pokemonDbDao().pokemonsByName(pokemonName)
//    }

    fun updatePokemonAsFavorite(pokemon: Pokemon, favorite: Boolean) {
        database.pokemonDbDao().updatePokemonFavoriteStatus(
            PokemonEntityUpdateFavorite(
                pokemonId = pokemon.id,
                isFavorite = favorite
            )
        )
        if (favorite) {
            newFavorites++
        }
    }

    fun reloadCache() {
        viewModelScope.launch {
            database.pokemonDbDao().clearAllPokemonsExceptFavorites()
        }
    }
}