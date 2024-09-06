package com.barril.pokedexapp.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import com.barril.pokedexapp.PokemonType
import com.barril.pokedexapp.R
import com.barril.pokedexapp.ui.theme.PokeDexAppTheme
import java.util.EnumSet


@Composable
fun HomeView(modifier: Modifier = Modifier) {
    Column {
        PokemonSearchBar(modifier)
        PokemonColumnList(modifier)
    }
}

@Composable
fun PokemonSearchBar(modifier: Modifier = Modifier) {
    Row() {
        SearchBar()
        Button(onClick = { /*TODO*/ }) {
            Icon(painterResource(id = R.drawable.filter_icon), contentDescription = "Filtros")
        }
    }
}

@Composable
fun PokemonColumnList(modifier: Modifier = Modifier) {
    LazyColumn(
        modifier = modifier
    ) {
        item {
            val types = PokemonType.entries
            for(i in 1..2) {
                val random = (0..types.size).random()

                PokemonCard(
                    pokemonName = "Bulbasaur",
                    pokemonType = EnumSet.of(PokemonType.GRASS),
                )
            }
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
