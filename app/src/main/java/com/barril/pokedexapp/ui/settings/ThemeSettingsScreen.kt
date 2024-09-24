package com.barril.pokedexapp.ui.settings

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

// TODO: usar profiles para fazer o aplicativo ir mais rapido
// TODO: fazer splash screen
// TODO: fazer aquela coisa que o Philipp fez para manter o preview mesmo que o app seja destruido
// TODO: talvez usar um pokemon preto + um branco para representar cada tema?
@OptIn(ExperimentalMaterial3Api::class)
@Preview
@Composable
fun ThemeSettingsScreen(modifier: Modifier = Modifier) {
    Column(
        Modifier.background(
            color = MaterialTheme.colorScheme.background
        )
    ) {
        TopAppBar(
            title = {
                Text(
                    text = "Mudar Tema",
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier
                        .align(Alignment.CenterHorizontally)
                )
            },
            navigationIcon = {
                Icon(
                    Icons.AutoMirrored.Default.ArrowBack,
                    contentDescription = "",
                    modifier = Modifier
                        .padding(5.dp)
                )
            },
            actions = { }
        )
    }

    // arrumar uma maneira de fazer um carrossel que nem o mihon
    // HorizontalUncontainedCarousel NAO FUNCIONA SE NAO FOR IMAGEM
}
