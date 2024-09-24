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
import androidx.compose.ui.res.stringResource
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.room.Index
import com.barril.pokedexapp.R
import com.barril.pokedexapp.domain.Pokemon
import com.barril.pokedexapp.ui.components.FilterAndOrderBottomSheet
import com.barril.pokedexapp.ui.components.HomeTopBar
import com.barril.pokedexapp.ui.components.PokemonColumnList
import com.barril.pokedexapp.viewmodels.HomeViewModel
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    viewModel: HomeViewModel,
    onPokemonCardClick: (Pokemon) -> Unit,
    onFavoriteCardButtonClick: (Pokemon) -> Unit,
    onSearchButtonClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    var isMoreExpanded by remember { mutableStateOf(false) }

    val sheetState = rememberModalBottomSheetState()
    var isFilterExpanded by remember { mutableStateOf(false) }

    val scope = rememberCoroutineScope()

    Column(modifier = modifier) {
        HomeTopBar(
            title = { Text(stringResource(R.string.home_title)) },
            isMoreExpanded = isMoreExpanded,
            onSearchButtonClick = onSearchButtonClick,
            onFilterButtonClick = {
                scope.launch { sheetState.expand() }.invokeOnCompletion {
                    if (sheetState.isVisible) {
                        isFilterExpanded = true
                    }
                }
            },
            onMoreButtonClick = { isMoreExpanded = !isMoreExpanded },
            onDismissMoreDropMenu = { isMoreExpanded = false },
            dropdownMenuScope = {
                Row {
                    TextButton(onClick = {
                        viewModel.clearCache()
                    }) {
                        Text("Limpar cache.")
                    }
                }
            }
        )

        PokemonColumnList(
            pokemonPagingItems = {
                viewModel.getPokemonPagingFlow().collectAsLazyPagingItems()
            },
            onCardClick = onPokemonCardClick,
            onFavoriteCardButtonClick = onFavoriteCardButtonClick
        )

        if (isFilterExpanded) {
            FilterAndOrderBottomSheet(
                onDismissRequest = {
                    isFilterExpanded = false
                    viewModel.refreshLazyColumn()
                },
                sheetState = sheetState,
                selectedFilter = viewModel.selectedFilterType,
                onFilterClicked = { pokemonType ->
                    if (viewModel.selectedFilterType == pokemonType) {
                        viewModel.selectedFilterType = null
                    } else {
                        viewModel.selectedFilterType = pokemonType
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
                    } else {
                        viewModel.orderingBy = column
                        viewModel.currentPokemonOrderingDirection = Index.Order.ASC
                    }
                },
            )
        }
    }
}
