package com.barril.pokedexapp.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.cachedIn
import androidx.paging.map
import com.barril.pokedexapp.data.local.PokemonEntity
import com.barril.pokedexapp.data.toPokemon
import kotlinx.coroutines.flow.map

class MainViewModel(
    pager: Pager<Int, PokemonEntity>
) : ViewModel() {

    val pokemonPagingFlow = pager
        .flow
        .map { pagingData ->
            pagingData.map { it.toPokemon() }
        }
        .cachedIn(viewModelScope)

}