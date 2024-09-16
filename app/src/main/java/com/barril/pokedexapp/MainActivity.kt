package com.barril.pokedexapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.annotation.StringRes
import androidx.compose.animation.AnimatedContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.rounded.Home
import androidx.compose.material.icons.rounded.Settings
import androidx.compose.material3.Badge
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.adaptive.currentWindowAdaptiveInfo
import androidx.compose.material3.adaptive.navigationsuite.NavigationSuiteDefaults
import androidx.compose.material3.adaptive.navigationsuite.NavigationSuiteScaffold
import androidx.compose.material3.adaptive.navigationsuite.NavigationSuiteScaffoldDefaults
import androidx.compose.material3.adaptive.navigationsuite.NavigationSuiteType
import androidx.compose.runtime.Composable
import androidx.compose.runtime.asIntState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.window.core.layout.WindowWidthSizeClass
import com.barril.pokedexapp.ui.favorites.FavoritesView
import com.barril.pokedexapp.ui.home.HomeView
import com.barril.pokedexapp.ui.settings.SettingsView
import com.barril.pokedexapp.ui.theme.PokeDexAppTheme
import com.barril.pokedexapp.viewmodels.MainViewModel
import com.barril.pokedexapp.viewmodels.viewModelFactory
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import kotlinx.serialization.Serializable

enum class AppDestinations(
    @StringRes val label: Int,
    val icon: ImageVector,
    @StringRes val contentDescription: Int,
    val destination: Any
) {

    HOME(
        R.string.home_destination,
        Icons.Rounded.Home,
        R.string.home_description,
        HomeDestination
    ),
    FAVORITES(
        R.string.favorites_destination,
        Icons.Default.Star,
        R.string.favorites_description,
        FavoritesDestination
    ),
    SETTINGS(
        R.string.settings_destination,
        Icons.Rounded.Settings,
        R.string.settings_description,
        SettingsDestination
    );

    @Serializable
    object HomeDestination

    @Serializable
    object FavoritesDestination

    @Serializable
    object SettingsDestination
}

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val viewModel by viewModels<MainViewModel>(
            factoryProducer = {
                viewModelFactory {
                    MainViewModel(PokeDexApplication.appModule.pokemonPager)
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

    val newFavorites =

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
                HomeView(viewModel.pokemonPagingFlow.collectAsLazyPagingItems(), modifier)
            }
            composable<AppDestinations.FavoritesDestination> {
                FavoritesView()
            }
            composable<AppDestinations.SettingsDestination> {
                SettingsView()
            }
        }
    }
}

//@Preview
//@Composable
//fun MainAppPreview() {
//    PokeDexAppTheme {
//        MainApp()
//    }
//}