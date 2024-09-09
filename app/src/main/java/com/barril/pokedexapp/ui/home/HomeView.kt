package com.barril.pokedexapp.ui.home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.BottomSheetDefaults
import androidx.compose.material3.Checkbox
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.SecondaryTabRow
import androidx.compose.material3.Tab
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.ui.Alignment
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.imageResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.barril.pokedexapp.R
import com.barril.pokedexapp.domain.PokemonType
import com.barril.pokedexapp.ui.components.ImageWithShadow
import com.barril.pokedexapp.ui.components.PokemonCard
import com.barril.pokedexapp.ui.components.PokemonTypeIcon
import com.barril.pokedexapp.ui.components.SearchBar
import com.barril.pokedexapp.ui.components.scaleImageBitMap
import com.barril.pokedexapp.ui.theme.PokeDexAppTheme
import kotlinx.coroutines.launch
import java.util.EnumSet

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeView(modifier: Modifier = Modifier) {
    Column(modifier) {
        var isSearchExpanded by remember { mutableStateOf(false) }
        var isMoreExpanded by remember { mutableStateOf(false) }

        val sheetState = rememberModalBottomSheetState()
        var isFilterExpanded by remember { mutableStateOf(false) }

        val scope = rememberCoroutineScope()

        PokemonTopBar(
            isSearchExpanded = isSearchExpanded,
            isMoreExpanded = isMoreExpanded,
            onSearchCloseButtonClick = { isSearchExpanded = false },
            onSearchButtonClick = { isSearchExpanded = true },
            onFilterButtonClick = {
                scope.launch { sheetState.expand() }.invokeOnCompletion {
                    if (sheetState.isVisible) {
                        isFilterExpanded = true
                    }
                }
            },
            onMoreButtonClick = { isMoreExpanded = !isMoreExpanded },
            onSearchValueChange = { /* TODO */ },
            onDismissMoreDropMenu = { isMoreExpanded = false }
        )

        PokemonColumnList()

        if (isFilterExpanded) {
            FilterBottomSheet(
                onDismissRequest = { isFilterExpanded = false },
                sheetState = sheetState
            )
        }
    }
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PokemonTopBar(
    isSearchExpanded: Boolean = false,
    // isMoreExpanded: Boolean, // nada implementado para usar isso ainda
    isMoreExpanded: Boolean = false,
    onSearchButtonClick: () -> Unit = {},
    onSearchCloseButtonClick: () -> Unit = {},
    onFilterButtonClick: () -> Unit = {},
    onMoreButtonClick: () -> Unit = {},
    onSearchValueChange: (String) -> Unit = {},
    onDismissMoreDropMenu: () -> Unit = {},
) {
    TopAppBar(
        title = {
            if(isSearchExpanded) {
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
                Text("Principal")
            }
        },
        actions = {
            if(!isSearchExpanded) {
                TextButton(
                    onClick = onSearchButtonClick,
                ) {
                    Icon(
                        Icons.Default.Search,
                        contentDescription = stringResource(R.string.search_icon_description)
                    )
                }
            }
            TextButton(
                onClick = onFilterButtonClick,
            ) {
                Icon(
                    painterResource(id = R.drawable.filter_icon),
                    contentDescription = stringResource(R.string.filter_icon_description)
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
                    isMoreExpanded,
                    onDismissRequest = onDismissMoreDropMenu
                ) {
                    Text("teste")
                    /* TODO: por funções aqui */
                }
            }
        }
    )
}

@Composable
fun PokemonColumnList(modifier: Modifier = Modifier) {
    var loading by remember { mutableStateOf(false) }
    var isFavorite by remember { mutableStateOf(false) }

    LazyColumn(
        modifier = Modifier
            .fillMaxWidth()
            .background(MaterialTheme.colorScheme.background)
    ) {
        item {
            PokemonCard(
                pokemonName = "Bulbasaur",
                pokemonType = EnumSet.of(PokemonType.GRASS, PokemonType.ELECTRIC),
                pokemonArt = {
                    val img = ImageBitmap.imageResource(R.drawable.balbasaur)
                    val targetWidth = with(LocalDensity.current) {
                        100.dp.toPx().toInt()
                    }
                    val targetHeight = with(LocalDensity.current) {
                        100.dp.toPx().toInt()
                    }
                    ImageWithShadow(
                        bitmap = img.scaleImageBitMap(targetWidth, targetHeight),
                        contentDescription = null,
                        contentScale = ContentScale.None
                    )
                },
                isFavorite = isFavorite,
                onFavoriteButtonPressed = { isFavorite = !isFavorite },
                onCardClick = { /* TODO */ }
            )
        }
    }

    if (!loading) return

    CircularProgressIndicator(
        modifier = Modifier.width(64.dp),
        color = MaterialTheme.colorScheme.secondary,
        trackColor = MaterialTheme.colorScheme.surfaceVariant,
    )
}

@Preview
@Composable
fun HomePreview(modifier: Modifier = Modifier) {
    PokeDexAppTheme {
        HomeView(modifier)
    }
}
