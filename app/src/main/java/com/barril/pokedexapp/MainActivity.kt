package com.barril.pokedexapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.annotation.StringRes
import androidx.compose.animation.AnimatedContent
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.rounded.Home
import androidx.compose.material.icons.rounded.Settings
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.tooling.preview.Preview
import com.barril.pokedexapp.ui.AppNavigationBar
import com.barril.pokedexapp.ui.favorites.FavoritesView
import com.barril.pokedexapp.ui.home.HomeView
import com.barril.pokedexapp.ui.settings.SettingsView
import com.barril.pokedexapp.ui.theme.PokeDexAppTheme

enum class AppDestinations(
    @StringRes val label: Int,
    val icon: ImageVector,
    @StringRes val contentDescription: Int
) {
    HOME(R.string.home_destination, Icons.Rounded.Home, R.string.home_description),
    FAVORITES(R.string.favorites_destination, Icons.Default.Star, R.string.favorites_description),
    SETTINGS(R.string.settings_destination, Icons.Rounded.Settings, R.string.settings_description),
}

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

//        val viewModel by viewModels<DummyViewModel>()

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
            label = "View transition",
            /* transitionSpec = TODO */
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
