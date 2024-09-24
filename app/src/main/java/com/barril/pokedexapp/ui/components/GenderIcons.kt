package com.barril.pokedexapp.ui.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.barril.pokedexapp.R

@Composable
fun MaleIcon(
    contentDescription: String?,
    modifier: Modifier = Modifier,
    tint: Color = Color.Cyan
) {
    Icon(
        painter = painterResource(id = R.drawable.male_24px),
        contentDescription = contentDescription,
        tint = tint, // TODO: por isso no xml
        modifier = modifier
    )
}

@Composable
fun FemaleIcon(
    contentDescription: String?,
    tint: Color = Color.Magenta,
    modifier: Modifier = Modifier
) {
    Icon(
        painter = painterResource(id = R.drawable.female_24px),
        contentDescription = contentDescription,
        tint = tint, // TODO: por isso no xml
        modifier = modifier
    )
}

@Composable
fun MaleIconWithBackground(modifier: Modifier = Modifier) {
    Surface(
        shape = CircleShape,
        color = Color(0xFF3448FF),
//        color = Color.Cyan,
        modifier = modifier
    ) {
        MaleIcon(
            contentDescription = null,
            tint = Color.White,
            modifier = Modifier
                .fillMaxSize()
        )
    }
}

@Composable
fun FemaleIconWithBackground(modifier: Modifier = Modifier) {
    Surface(
        shape = CircleShape,
        color = Color.Magenta,
        modifier = modifier
    ) {
        FemaleIcon(
            contentDescription = null,
            tint = Color.White,
            modifier = Modifier
                .fillMaxSize()
        )
    }
}
