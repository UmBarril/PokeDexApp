package com.barril.pokedexapp.ui

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeJoin
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.sp
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.ui.res.painterResource
import com.barril.pokedexapp.PokemonType
import com.barril.pokedexapp.R
import java.util.EnumSet

@Composable
fun HomeView(modifier: Modifier = Modifier) {
    Scaffold(
        modifier = modifier,
        topBar = {
            PokemonSearchBar()
        },
        bottomBar = {
            AppNavigationBar()
        },
    ) { innerPadding ->
        PokemonColumnList(Modifier.padding(innerPadding))
    }
}

@Composable
fun PokemonSearchBar(modifier: Modifier = Modifier) {
    Row {
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
