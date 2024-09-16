package com.barril.pokedexapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.material3.Badge
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.adaptive.currentWindowAdaptiveInfo
import androidx.compose.material3.adaptive.navigationsuite.NavigationSuiteDefaults
import androidx.compose.material3.adaptive.navigationsuite.NavigationSuiteScaffold
import androidx.compose.material3.adaptive.navigationsuite.NavigationSuiteScaffoldDefaults
import androidx.compose.material3.adaptive.navigationsuite.NavigationSuiteType
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.window.core.layout.WindowWidthSizeClass
import com.barril.pokedexapp.ui.screens.FavoritesView
import com.barril.pokedexapp.ui.screens.HomeView
import com.barril.pokedexapp.ui.settings.SettingsView
import com.barril.pokedexapp.ui.theme.PokeDexAppTheme
import com.barril.pokedexapp.viewmodels.MainViewModel
import com.barril.pokedexapp.viewmodels.viewModelFactory

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val viewModel by viewModels<MainViewModel>(
            factoryProducer = {
                viewModelFactory {
                    MainViewModel(
                        PokeDexApplication.appModule.pokemonDatabase,
                        PokeDexApplication.appModule.pokemonApi
                    )
                }
            }
        )

        enableEdgeToEdge()
        setContent {
            PokeDexAppTheme {
                MainApp(viewModel = viewModel)
            }
        }
    }
}

@Composable
fun MainApp(viewModel: MainViewModel, modifier: Modifier = Modifier) {
    val navController = rememberNavController()
    val adaptiveInfo = currentWindowAdaptiveInfo()

    val myNavigationSuiteItemColors = NavigationSuiteDefaults.itemColors(
        navigationBarItemColors = NavigationBarItemDefaults.colors(
            indicatorColor = MaterialTheme.colorScheme.primaryContainer,
            selectedIconColor = MaterialTheme.colorScheme.onPrimaryContainer
        ),
    )
    val customNavSuiteType = with(adaptiveInfo) {
        if (windowSizeClass.windowWidthSizeClass == WindowWidthSizeClass.EXPANDED) {
            NavigationSuiteType.NavigationDrawer
        } else {
            NavigationSuiteScaffoldDefaults.calculateFromAdaptiveInfo(adaptiveInfo)
        }
    }

    NavigationSuiteScaffold(
        navigationSuiteItems = {
            AppDestinations.entries.forEach {
                item(
                    icon = {
                        Icon(
                            it.icon,
                            contentDescription = stringResource(it.contentDescription)
                        )
                    },
                    label = { Text(stringResource(it.label)) },
                    selected = it.destination == navController.currentDestination,
                    onClick = { navController.navigate(it.destination) },
                    colors = myNavigationSuiteItemColors,
                    badge = {
                        if (it.destination == AppDestinations.FavoritesDestination
                            && viewModel.newFavorites > 0) {
                            Badge {
                                Text("${viewModel.newFavorites}")
                            }
                        }
                    }
                )
            }
        },
        layoutType = customNavSuiteType,
    ) {
        NavHost(
            navController = navController,
            startDestination = AppDestinations.HOME.destination,
        ) {
            composable<AppDestinations.HomeDestination> {
                HomeView(viewModel, modifier)
            }
            composable<AppDestinations.FavoritesDestination> {
                FavoritesView(viewModel, modifier)
            }
            composable<AppDestinations.SettingsDestination> {
                SettingsView()
            }
        }
    }
}