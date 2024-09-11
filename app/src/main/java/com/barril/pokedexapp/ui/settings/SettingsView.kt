package com.barril.pokedexapp.ui.settings

import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.FilterQuality
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.res.imageResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.barril.pokedexapp.R

@Preview
@Composable
fun SettingsView(modifier: Modifier = Modifier) {
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
                onClick = { }
            )
        }
    }
}

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
            Box(Modifier.padding(horizontal = 10.dp)
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

@Composable
fun CoolEasterEgg(modifier: Modifier = Modifier) {
    var rotate by remember { mutableStateOf(false) }
    val alpha: Float by animateFloatAsState(
        if (rotate) 360f else 0f,
        label = "alpha",
        animationSpec = tween(durationMillis = 500, easing = FastOutSlowInEasing)
    )
    val interactionSource = remember { MutableInteractionSource() }

    Image (
        ImageBitmap.imageResource(R.drawable.balbasaur),
        contentDescription = null,
        filterQuality = FilterQuality.None,
        modifier = modifier
            .graphicsLayer {
                rotationX = alpha
            }
            .clickable(
                interactionSource = interactionSource,
                indication = null
            ) {
                rotate = !rotate
            }
    )
}

