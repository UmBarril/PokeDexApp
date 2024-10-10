package com.barril.pokedexapp.ui.settings

import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.FilterQuality
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.res.imageResource
import com.barril.pokedexapp.R

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
