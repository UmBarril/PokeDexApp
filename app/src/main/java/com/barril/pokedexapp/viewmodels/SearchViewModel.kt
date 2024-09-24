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

class SearchViewModel(
    val database: PokemonDatabase
): ViewModel() {

    private var lastQuery = ""

    private var searchPager: Pager<Int, PokemonWithRelations>? = null

    var searchResultFlow: Flow<PagingData<Pokemon>>? = null
        private set

//    if (!viewModel.hasLoadedAllPokemons) {
//        BasicAlertDialog(
//            onDismissRequest = { isSearchExpanded = false },
//        ) {
//            Text(
//                text = "Para pesquisar, é necessário carregar todos os pokemóns. Você deseja fazer isso?",
//                modifier = Modifier.padding(16.dp),
//            )
//            Row(
//                modifier = Modifier
//                    .fillMaxWidth(),
//                horizontalArrangement = Arrangement.Center,
//            ) {
//                TextButton(
//                    onClick = { isMoreExpanded = false },
//                    modifier = Modifier.padding(8.dp),
//                ) {
//                    Text("Não agora.")
//                }
//                TextButton(
//                    onClick = {
//                        viewModel.loadAllPokemons()
//                    },
//                    modifier = Modifier.padding(8.dp),
//                ) {
//                    Text("Prossiga.")
//                }
//            }
//        }
//    }
    fun updateSearchResultsFlow(query: String) {
        searchResultFlow = searchPokemonPager(query)
            ?.flow
            ?.map { pagingData ->
                pagingData.map { it.toPokemon() }
            }
            ?.cachedIn(viewModelScope)
    }

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
}