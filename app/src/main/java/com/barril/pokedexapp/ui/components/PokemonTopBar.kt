package com.barril.pokedexapp.ui.components

import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.TextButton
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import com.barril.pokedexapp.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeTopBar(
    title: @Composable () -> Unit,
    filterIconColor: Color,
    isMoreExpanded: Boolean = false,
    onSearchButtonClick: () -> Unit = {},
    onFilterButtonClick: () -> Unit = {},
    onMoreButtonClick: () -> Unit = {},
    onDismissMoreDropMenu: () -> Unit = {},
    dropdownMenuScope: @Composable() (ColumnScope.() -> Unit),
    modifier: Modifier = Modifier,
) {
    TopAppBar(
        modifier = modifier,
        title = {
            title()
        },
        actions = {
            // TODO: por descrições no botoes e não nos ícones
            TextButton(
                onClick = onSearchButtonClick,
            ) {
                Icon(
                    Icons.Default.Search,
                    contentDescription = null
                )
            }
            TextButton(
                onClick = onFilterButtonClick,
            ) {
                Icon(
                    painterResource(id = R.drawable.filter_icon),
                    contentDescription = stringResource(R.string.filter_icon_description),
                    tint = filterIconColor
                )
            }
            TextButton(
                onClick = onMoreButtonClick,
            ) {
                Icon(
                    Icons.Default.MoreVert,
                    contentDescription = stringResource(R.string.more_options_icon_description)
                )
                DropdownMenu(
                    expanded = isMoreExpanded,
                    onDismissRequest = onDismissMoreDropMenu,
                    content = dropdownMenuScope
                )
            }
        }
    )
}
