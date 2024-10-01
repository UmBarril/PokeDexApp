package com.barril.pokedexapp.ui.settings

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun SettingsRowItem(
    heading: String,
    subHeading: String?,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    TextButton(
        shape = RectangleShape,
        onClick = onClick,
        modifier = modifier
    ) {
        Row(
            horizontalArrangement = Arrangement.Start,
            modifier = Modifier
                .padding(horizontal = 10.dp, vertical = 15.dp)
                .fillMaxWidth()
        ) {
            Column(
                Modifier.padding(horizontal = 10.dp)
            ) {
                Text(
                    heading,
                    color = MaterialTheme.colorScheme.onSurface,
                    fontWeight = FontWeight.Bold,
                    fontSize = 15.sp,
                    modifier = Modifier
                )
                if (subHeading != null) {
                    Text(
                        subHeading,
                        color = MaterialTheme.colorScheme.onSurface,
                        fontWeight = FontWeight.Normal,
                        fontSize = 13.sp,
                        modifier = Modifier
                    )
                }
            }
        }
    }
}

// TODO: consertar duplicação de código
@Composable
fun SettingsRowItem(
    heading: String,
    subHeading: String?,
    icon: @Composable () -> Unit,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    TextButton(
        shape = RectangleShape,
        onClick = onClick,
        modifier = modifier
    ) {
        Row(
            horizontalArrangement = Arrangement.Start,
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .padding(horizontal = 10.dp, vertical = 15.dp)
                .fillMaxWidth()
        ) {
            Box(
                Modifier.padding(horizontal = 10.dp)
                .align(Alignment.CenterVertically)
            ) {
                icon()
            }
            Column(Modifier.padding(horizontal = 10.dp)) {
                Text(
                    heading,
                    color = MaterialTheme.colorScheme.onSurface,
                    fontWeight = FontWeight.Bold,
                    fontSize = 15.sp,
                    modifier = Modifier
                )
                if (subHeading != null) {
                    Text(
                        subHeading,
                        color = MaterialTheme.colorScheme.onSurface,
                        fontWeight = FontWeight.Normal,
                        fontSize = 13.sp,
                        modifier = Modifier
                    )
                }
            }
        }
    }
}
