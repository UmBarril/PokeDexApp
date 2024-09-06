package com.barril.pokedexapp.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun SettingsView(modifier: Modifier = Modifier) {
    Column {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Center
        ) {
            Text("TODO")
//            Image {
//                // TODO: por logo aqui
//            }
        }
        HorizontalDivider(modifier.padding(0.dp, 24.dp))
        Row(
            modifier = Modifier.padding(10.dp),
            horizontalArrangement = Arrangement.Start
        ) {
            Column {
                Text("PokéDexApp v1.0")
                Text("PokéDexApp v1.0")
            }
        }
    }
}

