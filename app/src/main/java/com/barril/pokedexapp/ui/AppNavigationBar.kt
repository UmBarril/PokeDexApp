package com.barril.pokedexapp.ui

import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.barril.pokedexapp.AppDestinations

@Composable
fun AppNavigationBar(
    onDestinationClicked: (AppDestinations) -> Unit,
    selectedDestination: AppDestinations,
    modifier: Modifier = Modifier
) {
    NavigationBar(
        modifier = modifier,
        containerColor = MaterialTheme.colorScheme.background
    ) {
        NavigationBarItem(
            icon = {
                Icon(AppDestinations.HOME.icon, contentDescription = stringResource(AppDestinations.HOME.contentDescription))
            },
            label = {
                Text(stringResource(AppDestinations.HOME.label))
            },
            selected = selectedDestination == AppDestinations.HOME,
            onClick = { onDestinationClicked(AppDestinations.HOME) }
        )
        NavigationBarItem(
            icon = {
                Icon(
                    AppDestinations.FAVORITES.icon, contentDescription = stringResource(
                        AppDestinations.FAVORITES.contentDescription)
                )
            },
            label = {
                Text(stringResource(AppDestinations.FAVORITES.label))
            },
            selected = selectedDestination == AppDestinations.FAVORITES,
            onClick = { onDestinationClicked(AppDestinations.FAVORITES) }
        )
        NavigationBarItem(
            icon = {
                Icon(
                    AppDestinations.SETTINGS.icon, contentDescription = stringResource(
                        AppDestinations.SETTINGS.contentDescription)
                )
            },
            label = {
                Text(stringResource(AppDestinations.SETTINGS.label))
            },
            selected = selectedDestination == AppDestinations.SETTINGS,
            onClick = { onDestinationClicked(AppDestinations.SETTINGS) }
        )
    }
}
