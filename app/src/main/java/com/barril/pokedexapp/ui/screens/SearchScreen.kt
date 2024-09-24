package com.barril.pokedexapp.ui.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.paging.compose.collectAsLazyPagingItems
import com.barril.pokedexapp.domain.Pokemon
import com.barril.pokedexapp.ui.components.PokemonColumnList
import com.barril.pokedexapp.ui.components.SearchTopBar
import com.barril.pokedexapp.viewmodels.SearchViewModel

@Composable
fun SearchScreen(
    viewModel: SearchViewModel,
    searchForFavorites: Boolean, // TODO: fazer algo com isso
    onPokemonCardClick: (Pokemon) -> Unit,
    onFavoriteCardButtonClick: (Pokemon) -> Unit,
    onClose: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(modifier = modifier) {
        SearchTopBar(
            onSearchValueChange = { str ->
                viewModel.updateSearchResultsFlow(str)
            },
            onSearchCloseButtonClick = onClose
        )
        val searchResultFlow = viewModel.searchResultFlow
        if (searchResultFlow != null) {
            PokemonColumnList(
                pokemonPagingItems = {
                    searchResultFlow.collectAsLazyPagingItems()
                },
                onCardClick = onPokemonCardClick,
                onFavoriteCardButtonClick = onFavoriteCardButtonClick
            )
        }
    }
}