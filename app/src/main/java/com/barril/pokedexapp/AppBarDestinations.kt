package com.barril.pokedexapp

import androidx.annotation.StringRes
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.rounded.Home
import androidx.compose.material.icons.rounded.Settings
import androidx.compose.ui.graphics.vector.ImageVector
import kotlinx.serialization.Serializable

enum class AppBarDestinations(
    @StringRes val label: Int,
    val icon: ImageVector,
    @StringRes val contentDescription: Int,
    val destination: Any
) {

    HOME(
        R.string.home_destination,
        Icons.Rounded.Home,
        R.string.home_description,
        HomeDestination
    ),
    FAVORITES(
        R.string.favorites_destination,
        Icons.Default.Star,
        R.string.favorites_description,
        FavoritesDestination
    ),
    SETTINGS(
        R.string.settings_destination,
        Icons.Rounded.Settings,
        R.string.settings_description,
        SettingsDestination
    );

}

@Serializable
object HomeDestination

@Serializable
object FavoritesDestination

@Serializable
data class SearchDestination(val searchForFavorites: Boolean)

@Serializable
object SettingsDestination
