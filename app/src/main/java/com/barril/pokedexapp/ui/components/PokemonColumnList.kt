package com.barril.pokedexapp.ui.components

import android.widget.Toast
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import com.barril.pokedexapp.domain.Pokemon

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun PokemonColumnList(
    pokemonPagingItems: @Composable () -> LazyPagingItems<Pokemon>,
    onCardClick: (Pokemon) -> Unit,
    onFavoriteCardButtonClick: (Pokemon) -> Unit,
    modifier: Modifier = Modifier
) {
    val pokemons = pokemonPagingItems()
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
                items(count = pokemons.itemCount, key = { it }) { index ->
                    val pokemon = pokemons[index]
                    if (pokemon != null) {
                        PokemonCard(
                            pokemon = pokemon,
                            onFavoriteButtonPressed = onFavoriteCardButtonClick,
                            onCardClick = onCardClick,
                            modifier = Modifier.
                                animateItem(
                                    fadeInSpec = null,
                                    fadeOutSpec = null
                                )
                        )
                    }
                }
                item(key = -1) {
                    if(pokemons.loadState.append is LoadState.Loading) {
                        Row(
                            horizontalArrangement = Arrangement.Center,
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(64.dp)
                        ) {
                            CircularProgressIndicator(
                                modifier = Modifier
                                    .width(64.dp)
                                    .animateItem(
                                        fadeInSpec = null,
                                        fadeOutSpec = null
                                    ),
                                color = MaterialTheme.colorScheme.secondary,
                                trackColor = MaterialTheme.colorScheme.surfaceVariant,
                            )
                        }
                    }
                }
            }
        }
    }
}
