package com.barril.pokedexapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.animation.AnimatedContent
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.barril.pokedexapp.ui.AppNavigationBar
import com.barril.pokedexapp.ui.FavoritesView
import com.barril.pokedexapp.ui.HomeView
import com.barril.pokedexapp.ui.SettingsView
import com.barril.pokedexapp.ui.theme.PokeDexAppTheme
import com.barril.pokedexapp.viewmodels.DummyViewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val viewModel by viewModels<DummyViewModel>()

        enableEdgeToEdge()
        setContent {
            PokeDexAppTheme {
                MainApp(/*viewModel = viewModel*/)
            }
        }
    }
}

@Composable
fun MainApp(modifier: Modifier = Modifier) {
    var currentDestination by remember { mutableStateOf(AppDestinations.HOME) }

    Scaffold(
        modifier = modifier,
        topBar = { },
        bottomBar = {
            AppNavigationBar(
                onDestinationClicked = { newDestination ->
                    currentDestination = newDestination
                },
                selectedDestination = currentDestination
            )
        },
    ) { innerPadding ->
        AnimatedContent(
            targetState = currentDestination,
            /* transitinSpec = TODO */
        ) { targetState ->
            when (targetState) {
                AppDestinations.HOME -> HomeView(modifier.padding(innerPadding))
                AppDestinations.FAVORITES -> FavoritesView()
                AppDestinations.SETTINGS -> SettingsView()
            }
        }
    }
}

@Preview
@Composable
fun MainAppPreview() {
    PokeDexAppTheme {
        MainApp()
    }
}
