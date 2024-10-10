package com.barril.pokedexapp.ui.settings

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.barril.pokedexapp.R
import com.barril.pokedexapp.viewmodels.SettingsViewModel

@Composable
fun SettingsScreen(viewModel: SettingsViewModel, modifier: Modifier = Modifier) {
    Column(
        modifier = modifier.background(MaterialTheme.colorScheme.background)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Center
        ) {
            CoolEasterEgg(
                modifier = modifier
                    .size(200.dp)
            )
        }
        HorizontalDivider()
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            SettingsRowItem(
                heading = "Mudar tema",
                subHeading = null,
                icon = {
                    Icon(
                        painter = painterResource(R.drawable.palette_24px),
                        contentDescription = null
                    )
                },
                onClick = {

                }
            )
            SettingsRowItem(
                heading = "Limpar cache",
                subHeading = null,
                icon = {
                    Icon(
                        painter = painterResource(R.drawable.palette_24px),
                        contentDescription = null
                    )
                },
                onClick = {
                }
            )
            SettingsRowItem(
                heading = stringResource(R.string.app_name),
                subHeading = "v1.0 (setembro 2024)", // ver como conseguir string de versao
                onClick = { }
            )
        }
    }
}
