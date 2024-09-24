package com.barril.pokedexapp.ui.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
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
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.paging.compose.collectAsLazyPagingItems
import com.barril.pokedexapp.R
import com.barril.pokedexapp.domain.Pokemon
import com.barril.pokedexapp.ui.components.FavoritesTopBar
import com.barril.pokedexapp.ui.components.PokemonColumnList
import com.barril.pokedexapp.viewmodels.FavoritesViewModel

@Composable
fun FavoritesScreen(
    viewModel: FavoritesViewModel,
    onPokemonCardClick: (Pokemon) -> Unit,
    onFavoriteCardButtonClick: (Pokemon) -> Unit,
    onSearchButtonClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(modifier = modifier) {
        FavoritesTopBar(
            title = {
                Row {
                    Text(stringResource(R.string.favorites_title))
                    Spacer(Modifier.padding(10.dp))
                    Icon(
                        Icons.Default.Star,
                        contentDescription = ""
                    )
                }
            },
            onSearchButtonClick = onSearchButtonClick,
        )

        PokemonColumnList(
            pokemonPagingItems = {
                viewModel.favoritePokemonPagingFlow.collectAsLazyPagingItems()
            },
            onCardClick = onPokemonCardClick,
            onFavoriteCardButtonClick = onFavoriteCardButtonClick
        )
    }
}
