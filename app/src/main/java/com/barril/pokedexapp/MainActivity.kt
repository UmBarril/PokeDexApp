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
import androidx.navigation.toRoute
import androidx.window.core.layout.WindowWidthSizeClass
import com.barril.pokedexapp.domain.Pokemon
import com.barril.pokedexapp.ui.screens.FavoritesScreen
import com.barril.pokedexapp.ui.screens.HomeScreen
import com.barril.pokedexapp.ui.screens.SearchScreen
import com.barril.pokedexapp.ui.settings.SettingsView
import com.barril.pokedexapp.ui.theme.PokeDexAppTheme
import com.barril.pokedexapp.viewmodels.FavoritesViewModel
import com.barril.pokedexapp.viewmodels.HomeViewModel
import com.barril.pokedexapp.viewmodels.SearchViewModel
import com.barril.pokedexapp.viewmodels.viewModelFactory

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val favoritesViewModel by viewModels<FavoritesViewModel>(
            factoryProducer = {
                viewModelFactory {
                    FavoritesViewModel(
                        PokeDexApplication.appModule.pokemonDatabase,
                        PokeDexApplication.appModule.pokemonApi
                    )
                }
            }
        )

        val searchViewModel by viewModels<SearchViewModel>(
            factoryProducer = {
                viewModelFactory {
                    SearchViewModel(
                        PokeDexApplication.appModule.pokemonDatabase
                    )
                }
            }
        )

        val homeViewModel by viewModels<HomeViewModel>(
            factoryProducer = {
                viewModelFactory {
                    HomeViewModel(
                        PokeDexApplication.appModule.pokemonDatabase,
                        PokeDexApplication.appModule.pokemonApi
                    )
                }
            }
        )

        enableEdgeToEdge()
        setContent {
            PokeDexAppTheme {
                MainApp(
                    favoritesViewModel = favoritesViewModel,
                    searchViewModel = searchViewModel,
                    homeViewModel = homeViewModel
                )
            }
        }
    }
}

@Composable
fun MainApp(
    homeViewModel: HomeViewModel,
    favoritesViewModel: FavoritesViewModel,
    searchViewModel: SearchViewModel,
    modifier: Modifier = Modifier
) {
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

    val onPokemonCardClick = { pokemon: Pokemon ->
        // TODO
    }
    val onFavoriteCardButtonClick = { pokemon: Pokemon ->
        favoritesViewModel.updatePokemonAsFavorite(
            pokemon = pokemon,
            favorite = !pokemon.isFavorite
        )
    }
    NavigationSuiteScaffold(
        navigationSuiteItems = {
            AppBarDestinations.entries.forEach {
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
                        if (it.destination == FavoritesDestination &&
                            favoritesViewModel.newFavorites.isNotEmpty()
                        ) {
                            Badge {
                                Text("${favoritesViewModel.newFavorites.size}")
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
            startDestination = AppBarDestinations.HOME.destination,
        ) {
            composable<HomeDestination> {
                HomeScreen(
                    viewModel = homeViewModel,
                    onPokemonCardClick = onPokemonCardClick,
                    onFavoriteCardButtonClick = onFavoriteCardButtonClick,
                    onSearchButtonClick = {
                        navController.navigate(
                            SearchDestination(false)
                        )
                    },
                    modifier = modifier
                )
            }
            composable<SearchDestination> { navBackStackEntry ->
                val search: SearchDestination = navBackStackEntry.toRoute()
                SearchScreen(
                    viewModel = searchViewModel,
                    searchForFavorites = search.searchForFavorites,
                    onPokemonCardClick = onPokemonCardClick,
                    onFavoriteCardButtonClick = onFavoriteCardButtonClick,
                    onClose = {
                        // nunca vai ser null numa situação comum...
                        navController.navigate(navController.previousBackStackEntry?.destination?.id!!)
                    }
                )
            }
            composable<FavoritesDestination> {
                favoritesViewModel.flushNewFavorites()
                FavoritesScreen(
                    viewModel = favoritesViewModel,
                    onPokemonCardClick = onPokemonCardClick,
                    onFavoriteCardButtonClick = onFavoriteCardButtonClick,
                    onSearchButtonClick = {
                        navController.navigate(
                            SearchDestination(true)
                        )
                    },
                    modifier = modifier
                )
            }
            composable<SettingsDestination> {
                SettingsView()
            }
        }
    }
}