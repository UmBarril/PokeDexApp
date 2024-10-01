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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.barril.pokedexapp.R

// TODOs:
// usar isso androidx.compose.material3.pulltorefresh
// usar maps coisas do adaptive layout
// https://developer.android.com/reference/kotlin/androidx/compose/material/ripple/package-summary
// usar gestos para mover entre telas
//    - https://www.youtube.com/watch?v=1tkVjBxdGrk&pp=ygUtZ2VzdHVyZXMgbW92ZSBiZXR3ZWVuIHNjcmVlbnMgamV0cGFjayBjb21wb3Nl
//    - https://www.youtube.com/watch?v=h61Wqy3qcKg&pp=ygUtZ2VzdHVyZXMgbW92ZSBiZXR3ZWVuIHNjcmVlbnMgamV0cGFjayBjb21wb3Nl

@Preview
@Composable
fun SettingsScreen(modifier: Modifier = Modifier) {
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
                onClick = { }
            )
            SettingsRowItem(
                heading = stringResource(R.string.app_name),
                subHeading = "v1.0 (setembro 2024)", // ver como conseguir string de versao
                //  ${AppModule.ver}
                onClick = { }
            )
        }
    }
}
