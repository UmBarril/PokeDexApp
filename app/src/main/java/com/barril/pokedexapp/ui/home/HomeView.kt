package com.barril.pokedexapp.ui.home

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
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
import androidx.paging.compose.collectAsLazyPagingItems
import com.barril.pokedexapp.R
import com.barril.pokedexapp.data.toPokemon
import com.barril.pokedexapp.domain.Pokemon
import com.barril.pokedexapp.domain.PokemonGender
import com.barril.pokedexapp.ui.components.PokemonCard
import com.barril.pokedexapp.ui.components.SearchBar
import com.barril.pokedexapp.viewmodels.MainViewModel
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeView(viewModel: MainViewModel, modifier: Modifier = Modifier) {
    var isSearchExpanded by remember { mutableStateOf(false) }
    var isMoreExpanded by remember { mutableStateOf(false) }

    val sheetState = rememberModalBottomSheetState()
    var isFilterExpanded by remember { mutableStateOf(false) }

    val scope = rememberCoroutineScope()

    Column {
        PokemonTopBar(
            viewModel = viewModel,
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
            onSearchValueChange = { /* TODO */ },
            onDismissMoreDropMenu = { isMoreExpanded = false }
        )
        PokemonColumnList(viewModel)

        if (isFilterExpanded) {
            FilterBottomSheet(
                onDismissRequest = { isFilterExpanded = false },
                sheetState = sheetState
            )
        }
    }
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PokemonTopBar(
    viewModel: MainViewModel,
    isSearchExpanded: Boolean = false,
    // isMoreExpanded: Boolean, // nada implementado para usar isso ainda
    isMoreExpanded: Boolean = false,
    onSearchButtonClick: () -> Unit = {},
    onSearchCloseButtonClick: () -> Unit = {},
    onFilterButtonClick: () -> Unit = {},
    onMoreButtonClick: () -> Unit = {},
    onSearchValueChange: (String) -> Unit = {},
    onDismissMoreDropMenu: () -> Unit = {},
) {
    TopAppBar(
        title = {
            if (isSearchExpanded) {
                Row {
                    IconButton(onClick = onSearchCloseButtonClick) {
                        Icon(
                            Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = ""
                        )
                    }
                    SearchBar(
                        onValueChange = onSearchValueChange,
                    )
                }
            } else {
                Text("Principal")
            }
        },
        actions = {
            if (!isSearchExpanded) {
                TextButton(
                    onClick = onSearchButtonClick,
                ) {
                    Icon(
                        Icons.Default.Search,
                        contentDescription = stringResource(R.string.search_icon_description)
                    )
                }
            }
            TextButton(
                onClick = onFilterButtonClick,
            ) {
                Icon(
                    painterResource(id = R.drawable.filter_icon),
                    contentDescription = stringResource(R.string.filter_icon_description)
                )
            }
            TextButton(
                onClick = onMoreButtonClick,
            ) {
                Icon(
                    Icons.Default.MoreVert,
                    contentDescription = stringResource(R.string.more_options_icon_description)
                )
                DropdownMenu(
                    isMoreExpanded,
                    onDismissRequest = onDismissMoreDropMenu
                ) {
                   Row {
                       TextButton(onClick = {
                           viewModel.reloadCache()
                       }) {
                           Text("Limpar cache.")
                       }
                   }
                }
            }
        }
    )
}

@Composable
fun PokemonColumnList(viewModel: MainViewModel, modifier: Modifier = Modifier) {
    val pokemons = viewModel.pokemonPager.flow.collectAsLazyPagingItems()
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
                modifier = Modifier
                    .fillMaxWidth()
                    .background(MaterialTheme.colorScheme.background),
                verticalArrangement = Arrangement.spacedBy(
                    space = 4.dp
                )
            ) {
                items(count = pokemons.itemCount) { index ->
                    val pokemon = pokemons[index]?.toPokemon()
                    if (pokemon != null) {
                        PokemonCard(
                            pokemon = pokemon,
                            onFavoriteButtonPressed = {
                                viewModel.updatePokemonAsFavorite(
                                    pokemon = pokemon,
                                    favorite = pokemon.isFavorite
                                )
                            },
                            onCardClick = {
                                // TODO
                            },
                        )
                    }
                }
                item {
                    if(pokemons.loadState.append is LoadState.Loading) {
                        CircularProgressIndicator(
                            modifier = Modifier.width(64.dp),
                            color = MaterialTheme.colorScheme.secondary,
                            trackColor = MaterialTheme.colorScheme.surfaceVariant,
                        )
                    }
                }
            }
        }
    }
}

//@Preview
//@Composable
//fun HomePreview(modifier: Modifier = Modifier) {
//    PokeDexAppTheme {
//        HomeView(modifier)
//    }
//}
