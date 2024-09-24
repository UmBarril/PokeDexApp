package com.barril.pokedexapp.ui.components

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.TextButton
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.barril.pokedexapp.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FavoritesTopBar(
    title: @Composable () -> Unit,
    onSearchButtonClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    TopAppBar(
        modifier = modifier,
        title = {
            title()
        },
        actions = {
            // TODO: coocar descricao no botao
            TextButton(
                onClick = onSearchButtonClick,
            ) {
                Icon(
                    Icons.Default.Search,
                    contentDescription = null
                )
            }
        }
    )
}