package com.barril.pokedexapp.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.cachedIn
import androidx.paging.map
import com.barril.pokedexapp.data.local.entities.PokemonEntity
import com.barril.pokedexapp.data.local.entities.relations.PokemonWithRelations
import com.barril.pokedexapp.data.toPokemon
import kotlinx.coroutines.flow.map

class MainViewModel(
    pager: Pager<Int, PokemonWithRelations>
) : ViewModel() {

    val pokemonPagingFlow = pager
        .flow
        .map { pagingData ->
            pagingData.map { it.toPokemon() }
        }
        .cachedIn(viewModelScope)

}