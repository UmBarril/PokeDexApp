package com.barril.pokedexapp.ui.components

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
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
