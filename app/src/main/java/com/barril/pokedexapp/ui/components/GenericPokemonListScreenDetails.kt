package com.barril.pokedexapp.ui.components

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import com.barril.pokedexapp.R
import com.barril.pokedexapp.domain.Pokemon
import kotlinx.coroutines.launch

data class GenericPokemonListScreenDetails (
    val topBarTitle: @Composable () -> Unit,
    val topBarMoreButtonDropDown: @Composable ColumnScope.() -> Unit,
    val onTopBarSearchValueChange: (String) -> Unit,
    val pokemonColumnPagingItems: @Composable () -> LazyPagingItems<Pokemon>,
    val onPokemonColumnCardClick: (Pokemon) -> Unit,
    val onPokemonCardFavoriteButtonClick: (Pokemon) -> Unit,
)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun GenericPokemonListScreen(
    details: GenericPokemonListScreenDetails,
    modifier: Modifier = Modifier
) {
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
                onSearchValueChange = details.onTopBarSearchValueChange,
                onDismissMoreDropMenu = { isMoreExpanded = false },
                dropdownMenuScope = details.topBarMoreButtonDropDown
            )
        )

        PokemonColumnList(
            PokemonColumnListDetails(
                pokemonPagingItems = details.pokemonColumnPagingItems,
                onCardClick = details.onPokemonColumnCardClick,
                onFavoriteCardButtonClick = details.onPokemonCardFavoriteButtonClick,
            )
        )

        if (isFilterExpanded) {
            FilterBottomSheet(
                onDismissRequest = { isFilterExpanded = false },
                sheetState = sheetState
            )
        }
    }
}

data class PokemonTopBarDetails (
    val title: @Composable () -> Unit,
    val isSearchExpanded: Boolean = false,
    val isMoreExpanded: Boolean = false,
    val onSearchButtonClick: () -> Unit = {},
    val onSearchCloseButtonClick: () -> Unit = {},
    val onFilterButtonClick: () -> Unit = {},
    val onMoreButtonClick: () -> Unit = {},
    val onSearchValueChange: (String) -> Unit = {},
    val onDismissMoreDropMenu: () -> Unit = {},
    val dropdownMenuScope: @Composable ColumnScope.() -> Unit
)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PokemonTopBar(
    details: PokemonTopBarDetails,
    modifier: Modifier = Modifier
) {
    TopAppBar(
        modifier = modifier,
        title = {
            if (details.isSearchExpanded) {
                Row {
                    IconButton(onClick = details.onSearchCloseButtonClick) {
                        Icon(
                            Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = ""
                        )
                    }
                    SearchBar(
                        onValueChange = details.onSearchValueChange,
                    )
                }
            } else {
                details.title()
            }
        },
        actions = {
            if (!details.isSearchExpanded) {
                TextButton(
                    onClick = details.onSearchButtonClick,
                ) {
                    Icon(
                        Icons.Default.Search,
                        contentDescription = stringResource(R.string.search_icon_description)
                    )
                }
            }
            TextButton(
                onClick = details.onFilterButtonClick,
            ) {
                Icon(
                    painterResource(id = R.drawable.filter_icon),
                    contentDescription = stringResource(R.string.filter_icon_description)
                )
            }
            TextButton(
                onClick = details.onMoreButtonClick,
            ) {
                Icon(
                    Icons.Default.MoreVert,
                    contentDescription = stringResource(R.string.more_options_icon_description)
                )
                DropdownMenu(
                    details.isMoreExpanded,
                    onDismissRequest = details.onDismissMoreDropMenu
                ) {
                    details.dropdownMenuScope
                }
            }
        }
    )
}

data class PokemonColumnListDetails (
    val pokemonPagingItems: @Composable () -> LazyPagingItems<Pokemon>,
    val onCardClick: (Pokemon) -> Unit,
    val onFavoriteCardButtonClick: (Pokemon) -> Unit
)

@Composable
fun PokemonColumnList(
    details: PokemonColumnListDetails,
    modifier: Modifier = Modifier
) {
    val pokemons = details.pokemonPagingItems()
    val context = LocalContext.current
    LaunchedEffect(key1 = pokemons.loadState) {
        if(pokemons.loadState.refresh is LoadState.Error) {
            Toast.makeText(
                context,
                "Error: " + (pokemons.loadState.refresh as LoadState.Error).error.message,
                Toast.LENGTH_LONG
            ).show()
        }
    }

    Box(modifier = Modifier.fillMaxSize()) {
        if(pokemons.loadState.refresh is LoadState.Loading) {
            CircularProgressIndicator(
                modifier = Modifier.align(Alignment.Center)
            )
        } else {
            LazyColumn(
                modifier = modifier
                    .fillMaxWidth()
                    .background(MaterialTheme.colorScheme.background),
                verticalArrangement = Arrangement.spacedBy(
                    space = 4.dp
                )
            ) {
                items(count = pokemons.itemCount) { index ->
                    val pokemon = pokemons[index]
                    if (pokemon != null) {
                        PokemonCard(
                            pokemon = pokemon,
                            onFavoriteButtonPressed = details.onFavoriteCardButtonClick,
                            onCardClick = details.onCardClick
                        )
                    }
                }
                item {
                    if(pokemons.loadState.append is LoadState.Loading) {
                        CircularProgressIndicator(
                            modifier = Modifier.width(64.dp).align(Alignment.Center),
                            color = MaterialTheme.colorScheme.secondary,
                            trackColor = MaterialTheme.colorScheme.surfaceVariant,
                        )
                    }
                }
            }
        }
    }
}
