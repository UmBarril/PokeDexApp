package com.barril.pokedexapp.ui.screens

import androidx.compose.foundation.layout.Row
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.paging.compose.collectAsLazyPagingItems
import com.barril.pokedexapp.ui.components.GenericPokemonListScreen
import com.barril.pokedexapp.ui.components.GenericPokemonListScreenDetails
import com.barril.pokedexapp.viewmodels.MainViewModel

@Composable
fun HomeView(viewModel: MainViewModel, modifier: Modifier = Modifier) {
    GenericPokemonListScreen(
        GenericPokemonListScreenDetails(
            topBarTitle = { Text("Principal") },
            topBarMoreButtonDropDown = {
                Row {
                    TextButton(onClick = {
                        viewModel.reloadCache()
                    }) {
                        Text("Limpar cache.")
                    }
                }
            },
            onTopBarSearchValueChange = { value ->

            },
            pokemonColumnPagingItems = {
                viewModel.pokemonPagingFlow.collectAsLazyPagingItems()
            },
            onPokemonColumnCardClick = { pokemon ->
                /* TODO */
            },
            onPokemonCardFavoriteButtonClick = { pokemon ->
                viewModel.updatePokemonAsFavorite(
                    pokemon = pokemon,
                    favorite = pokemon.isFavorite
                )
            }
        ),
        modifier = modifier
    )
}
