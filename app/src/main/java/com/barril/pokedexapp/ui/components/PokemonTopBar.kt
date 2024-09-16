package com.barril.pokedexapp.ui.components

import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.Row
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.TextButton
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import com.barril.pokedexapp.R

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
