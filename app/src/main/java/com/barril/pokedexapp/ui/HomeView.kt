package com.barril.pokedexapp.ui

import androidx.compose.animation.AnimatedContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.TextButton
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.res.imageResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.barril.pokedexapp.PokemonType
import com.barril.pokedexapp.R
import com.barril.pokedexapp.ui.theme.PokeDexAppTheme
import java.util.EnumSet


@Composable
fun HomeView(modifier: Modifier = Modifier) {
    Column {
        PokemonSearchBar(modifier)
        PokemonColumnList(modifier.weight(1f))
    }
}

@Composable
fun PokemonSearchBar(modifier: Modifier = Modifier) {
    var isSearchingNow by remember { mutableStateOf(false) }
    Surface {
        Row(
            horizontalArrangement = Arrangement.End,
            modifier = modifier
                .fillMaxWidth()
        ) {
            // TODO: adicionar transição
            if (isSearchingNow) {
                TextButton(onClick = { isSearchingNow = false }) {
                    Icon(
                        Icons.AutoMirrored.Filled.ArrowBack,
                        contentDescription = ""
                    )
                }
                SearchBar(
                    onValueChange = { /* TODO */ },
                    modifier = Modifier.weight(1f)
                )
            } else {
                TextButton(
                    onClick = { isSearchingNow = true },
                ) {
                    Icon(
                        Icons.Default.Search,
                        contentDescription = stringResource(R.string.search_icon_description)
                    )
                }
            }
            TextButton(
                onClick = { /*TODO*/ },
            ) {
                Icon(
                    painterResource(id = R.drawable.filter_icon),
                    contentDescription = stringResource(R.string.filter_icon_description)
                )
            }
            TextButton(
                onClick = { /*TODO*/ },
            ) {
                Icon(
                    Icons.Default.MoreVert,
                    contentDescription = stringResource(R.string.more_options_icon_description)
                )
            }
        }
    }
}

@Composable
fun PokemonColumnList(modifier: Modifier = Modifier) {
    LazyColumn(
        modifier = modifier
            .fillMaxWidth()
            .background(MaterialTheme.colorScheme.background)
    ) {
        item(3) {

            PokemonCard(
                pokemonName = "Bulbasaur",
                pokemonType = EnumSet.of(PokemonType.GRASS),
                pokemonArt = ImageBitmap.imageResource(R.drawable.balbasaur),
                isFavorite = false,
                onFavoriteButtonPressed = { /* TODO */ }
            )
        }
    }
}

@Preview
@Composable
fun HomePreview(modifier: Modifier = Modifier) {
    PokeDexAppTheme {
        HomeView(modifier)
    }
}
