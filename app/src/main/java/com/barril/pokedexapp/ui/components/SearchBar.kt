package com.barril.pokedexapp.ui.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.barril.pokedexapp.R

@Composable
fun SearchBar(
    onValueChange: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    TextField(
        value = "",
        onValueChange = onValueChange,
        leadingIcon = {
            Icon(imageVector = Icons.Default.Search, contentDescription = null)
        },
        colors = TextFieldDefaults.colors(),
        placeholder = {
            Text(stringResource(R.string.search_icon_description))
        },
        modifier = modifier
            .fillMaxWidth()
            .heightIn(min = 56.dp)
    )
}
