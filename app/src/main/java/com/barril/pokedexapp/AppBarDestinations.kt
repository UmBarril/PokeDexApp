package com.barril.pokedexapp

import androidx.annotation.StringRes
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.outlined.Favorite
import androidx.compose.material.icons.outlined.FavoriteBorder
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.Settings
import androidx.compose.material.icons.outlined.Star
import androidx.compose.material.icons.rounded.Home
import androidx.compose.material.icons.rounded.Settings
import androidx.compose.ui.graphics.vector.ImageVector
import kotlinx.serialization.Serializable

enum class AppBarDestinations(
    @StringRes val label: Int,
    val unselectedIcon: ImageVector,
    val selectedicon: ImageVector,
    @StringRes val contentDescription: Int,
    val destination: Any
) {

    HOME(
        R.string.home_destination,
        Icons.Outlined.Home,
        Icons.Filled.Home,
        R.string.home_description,
        HomeDestination
    ),
    FAVORITES(
        R.string.favorites_destination,
        Icons.Outlined.FavoriteBorder,
        Icons.Filled.Favorite,
        R.string.favorites_description,
        FavoritesDestination
    ),
    SETTINGS(
        R.string.settings_destination,
        Icons.Outlined.Settings,
        Icons.Filled.Settings,
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
