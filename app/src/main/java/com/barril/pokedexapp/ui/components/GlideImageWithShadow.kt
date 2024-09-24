package com.barril.pokedexapp.ui.components

import android.graphics.Bitmap
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.offset
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.blur
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.asAndroidBitmap
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.DpOffset
import androidx.compose.ui.unit.dp
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage

@OptIn(ExperimentalGlideComposeApi::class)
@Composable
fun GlideImageWithShadow(
    model: Any?,
    modifier: Modifier = Modifier,
    contentDescription: String?,
    contentScale: ContentScale = ContentScale.Fit,
    shadowOffset: DpOffset = DpOffset(3.dp, 3.dp)
) {
    Box(modifier) {
        // sombra
        GlideImage(
            model = model,
            contentDescription = contentDescription,
            contentScale = contentScale,
            colorFilter = ColorFilter.tint(Color.Black),
            alpha = 0.4f,
            modifier = modifier
                .offset(x = shadowOffset.x, y = shadowOffset.y)
                .blur(radius = 1.dp)
        )
        // imagem
        GlideImage(
            model = model,
            contentDescription = contentDescription,
            contentScale = contentScale,
            modifier = modifier
        )
    }
}

fun ImageBitmap.scaleImageBitMap(
    targetWidth: Int,
    targetHeight: Int
): ImageBitmap {
    return Bitmap.createScaledBitmap(this.asAndroidBitmap(), targetWidth, targetHeight, false)
        .asImageBitmap()
}
