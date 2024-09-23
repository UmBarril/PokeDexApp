package com.barril.pokedexapp.ui.components

import androidx.compose.foundation.layout.Row
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
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
    onSearchValueChange: (String) -> Unit,
    onSearchCloseButtonClick: () -> Unit,
    onSearchButtonClick: () -> Unit,
    isSearchExpanded: Boolean,
    modifier: Modifier = Modifier
) {
    TopAppBar(
        modifier = modifier,
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
                title()
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
//            TextButton(
//                onClick = onMoreButtonClick,
//            ) {
//                Icon(
//                    Icons.Default.MoreVert,
//                    contentDescription = stringResource(R.string.more_options_icon_description)
//                )
//                DropdownMenu(
//                    isMoreExpanded,
//                    onDismissRequest = onDismissMoreDropMenu
//                ) {
//                    dropdownMenuScope
//                }
//            }
        }
    )
}