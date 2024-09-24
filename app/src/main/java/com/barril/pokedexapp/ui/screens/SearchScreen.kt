package com.barril.pokedexapp.ui.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.paging.compose.collectAsLazyPagingItems
import com.barril.pokedexapp.R
import com.barril.pokedexapp.domain.Pokemon
import com.barril.pokedexapp.ui.components.PokemonColumnList
import com.barril.pokedexapp.ui.components.SearchTopBar
import com.barril.pokedexapp.viewmodels.SearchViewModel

@Composable
fun SearchScreen(
    viewModel: SearchViewModel,
    searchForFavorites: Boolean,
    onPokemonCardClick: (Pokemon) -> Unit,
    onFavoriteCardButtonClick: (Pokemon) -> Unit,
    onClose: () -> Unit,
    modifier: Modifier = Modifier
) {
    // TODO: mover isso para o ViewModel
    var currentText by remember { mutableStateOf("") }

    Column(modifier = modifier) {
        SearchTopBar(
            placeHolder =  {
                if (searchForFavorites) {
                    Text(stringResource(R.string.search_favorites_placeholder))
                } else {
                    Text(stringResource(R.string.search_placeholder))
                }
            },
            value = currentText,
            onSearchValueChange = { str ->
                currentText = str
                viewModel.updateSearchResultsFlow(currentText, searchForFavorites)
            },
            onSearchCloseButtonClick = onClose
        )
        PokemonColumnList(
            pokemonPagingItems = {
                viewModel.searchResultFlow.collectAsLazyPagingItems()
            },
            onCardClick = onPokemonCardClick,
            onFavoriteCardButtonClick = onFavoriteCardButtonClick
        )
    }
}