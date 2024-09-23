package com.barril.pokedexapp.ui.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.paging.compose.collectAsLazyPagingItems
import com.barril.pokedexapp.ui.components.FavoritesTopBar
import com.barril.pokedexapp.ui.components.PokemonColumnList
import com.barril.pokedexapp.ui.components.PokemonColumnListDetails
import com.barril.pokedexapp.viewmodels.HomeViewModel

@Composable
fun FavoritesScreen(viewModel: HomeViewModel, modifier: Modifier = Modifier) {
    var isSearchExpanded by remember { mutableStateOf(false) }

    Column(modifier = modifier) {
        FavoritesTopBar(
            title = {
                Row {
                    Text("Favoritos")
                    Icon(
                        Icons.Default.Star,
                        contentDescription = ""
                    )
                }
            },
            isSearchExpanded = isSearchExpanded,
            onSearchCloseButtonClick = { isSearchExpanded = false },
            onSearchButtonClick = { isSearchExpanded = true },
            onSearchValueChange = { string ->
                viewModel.updateSearchResultsFlow(string)
            }
        )

        if (isSearchExpanded ) {
            val pagingItems = viewModel.searchResultFlow?.collectAsLazyPagingItems()
            if (pagingItems != null) {
                PokemonColumnList(
                    PokemonColumnListDetails(
                        pokemonPagingItems = {
                            pagingItems
                        },
                        onCardClick = { pokemon ->
                            // TODO
                        },
                        onFavoriteCardButtonClick = { pokemon ->
                            viewModel.updatePokemonAsFavorite(pokemon, !pokemon.isFavorite)
                        }
                    )
                )
            }
        } else {
            PokemonColumnList(
                PokemonColumnListDetails(
                    pokemonPagingItems = {
                        viewModel.favoritePokemonPagingFlow.collectAsLazyPagingItems()
                    },
                    onCardClick = { pokemon ->
                        // TODO
                    },
                    onFavoriteCardButtonClick = { pokemon ->
                        viewModel.updatePokemonAsFavorite(pokemon, !pokemon.isFavorite)
                    }
                )
            )
        }
    }
}
