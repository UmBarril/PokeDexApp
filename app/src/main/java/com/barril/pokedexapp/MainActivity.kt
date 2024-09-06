package com.barril.pokedexapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.barril.pokedexapp.ui.HomeView
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
    HomeView(modifier)
}

@Preview
@Composable
fun MainAppPreview() {
    PokeDexAppTheme {
        MainApp()
    }
}
