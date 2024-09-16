package com.barril.pokedexapp.ui.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.room.Index
import com.barril.pokedexapp.domain.Pokemon
import com.barril.pokedexapp.ui.components.FilterBottomSheet
import com.barril.pokedexapp.ui.components.PokemonColumnList
import com.barril.pokedexapp.ui.components.PokemonColumnListDetails
import com.barril.pokedexapp.ui.components.PokemonTopBar
import com.barril.pokedexapp.ui.components.PokemonTopBarDetails
import com.barril.pokedexapp.viewmodels.MainViewModel
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(viewModel: MainViewModel, modifier: Modifier = Modifier) {
    var isSearchExpanded by remember { mutableStateOf(false) }
    var isMoreExpanded by remember { mutableStateOf(false) }

    val sheetState = rememberModalBottomSheetState()
    var isFilterExpanded by remember { mutableStateOf(false) }

    val scope = rememberCoroutineScope()

    Column(modifier = modifier) {
        PokemonTopBar(
            PokemonTopBarDetails (
                title = { Text("Principal") },
                isSearchExpanded = isSearchExpanded,
                isMoreExpanded = isMoreExpanded,
                onSearchCloseButtonClick = { isSearchExpanded = false },
                onSearchButtonClick = { isSearchExpanded = true },
                onFilterButtonClick = {
                    scope.launch { sheetState.expand() }.invokeOnCompletion {
                        if (sheetState.isVisible) {
                            isFilterExpanded = true
                        }
                    }
                },
                onMoreButtonClick = { isMoreExpanded = !isMoreExpanded },
                onSearchValueChange = {

                },
                onDismissMoreDropMenu = { isMoreExpanded = false },
                dropdownMenuScope = {
                    Row {
                        TextButton(onClick = {
                            viewModel.reloadCache()
                        }) {
                            Text("Limpar cache.")
                        }
                    }
                }
            )
        )
        val onPokemonColumnCardClick = { pokemon: Pokemon ->
            // TODO
        }
        val onFavoriteCardButtonClick = { pokemon: Pokemon ->
            viewModel.updatePokemonAsFavorite(
                pokemon = pokemon,
                favorite = pokemon.isFavorite
            )
        }
        if (isSearchExpanded) {
            if (!viewModel.hasLoadedAllPokemons) {

            }
            PokemonColumnList(
                PokemonColumnListDetails(
                    pokemonPagingItems = {
                        viewModel.pokemonPagingFlow.collectAsLazyPagingItems()
                    },
                    onCardClick = { pokemon ->
                        onPokemonColumnCardClick(pokemon)
                    },
                    onFavoriteCardButtonClick = { pokemon ->
                        onFavoriteCardButtonClick(pokemon)
                    }
                )
            )
        } else {
            PokemonColumnList(
                PokemonColumnListDetails(
                    pokemonPagingItems = {
                        viewModel.pokemonPagingFlow.collectAsLazyPagingItems()
                    },
                    onCardClick = onPokemonColumnCardClick,
                    onFavoriteCardButtonClick = onFavoriteCardButtonClick
                )
            )
        }

        if (isFilterExpanded) {
            FilterBottomSheet(
                onDismissRequest = { isFilterExpanded = false },
                sheetState = sheetState,
                selectedFilters = viewModel.selectedFilterTypes,
                onFilterClicked = { pokemonType ->
                    if (viewModel.selectedFilterTypes.contains(pokemonType)) {
                        viewModel.selectedFilterTypes.remove(pokemonType)
                    } else {
                        viewModel.selectedFilterTypes.add(pokemonType)
                    }
                },
                currentOrderingDirection = viewModel.currentPokemonOrderingDirection,
                currentOrderingColumn = viewModel.orderingBy,
                onOrderingClicked = { column ->
                    if (viewModel.orderingBy == column) {
                        viewModel.currentPokemonOrderingDirection =
                            if (viewModel.currentPokemonOrderingDirection == Index.Order.ASC) {
                                Index.Order.DESC
                            } else {
                                Index.Order.ASC
                            }
                    }
                },
            )
        }
    }
}
