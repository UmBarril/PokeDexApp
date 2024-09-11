package com.barril.pokedexapp.ui.home

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material3.Checkbox
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.LocalContentColor
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.SecondaryTabRow
import androidx.compose.material3.SheetState
import androidx.compose.material3.Tab
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.barril.pokedexapp.domain.PokemonType
import com.barril.pokedexapp.ui.components.PokemonTypeIcon

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FilterBottomSheet(
    onDismissRequest: () -> Unit,
    sheetState: SheetState,
    modifier: Modifier = Modifier
) {
    var state by remember { mutableIntStateOf(0) }
    val titles = listOf("Filtrar por Gen", "Filtrar por Tipo", "Ordenação")

    ModalBottomSheet(
        onDismissRequest = onDismissRequest,
        sheetState = sheetState,
        dragHandle = {
            Column {
                SecondaryTabRow(selectedTabIndex = state) {
                    titles.forEachIndexed { index, title ->
                        Tab(
                            text = {
                                Text(titles[index])
                            },
                            onClick = { state = index },
                            selected = (index == state)
                        )
                    }
                }
            }
        },
        modifier = modifier
    ) {
        when(state) {
            0 -> {
                PokemonGenFilterTab()
            }
            1 -> {
                PokemonTypeFilterTab()
            }
            2 -> {
                PokemonOrderingTab()
            }
        }
    }
}

@Composable
fun PokemonOrderingTab(modifier: Modifier = Modifier) {
    val defaultIsDescending = false

    // TODO: por no res
    val orderingTypes = listOf("Ordenar por numeração", "Ordenar por geração", "Ordenar por Tipo", "Ordenar por nome")
    var isDescending by remember { mutableStateOf(defaultIsDescending) }

    var selected by remember { mutableIntStateOf(0) }
    for(i in orderingTypes.indices) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = modifier
                .padding(20.dp)
                .clickable {
                    if (selected == i) {
                        isDescending = !isDescending
                    } else {
                        selected = i
                        isDescending = defaultIsDescending
                    }
                }
        ) {
            if (selected != i) {
                Icon(
                    Icons.Default.KeyboardArrowDown,
                    contentDescription = null,
                    // se não estiver selecionado, não mostrar icone
                    tint = if(selected == i) LocalContentColor.current else Color.Transparent
                )
            } else if (isDescending) {
                Icon(
                    Icons.Default.KeyboardArrowUp,
                    contentDescription = null,
                )
            } else {
                Icon(
                    Icons.Default.KeyboardArrowDown,
                    contentDescription = null,
                )
            }
            Text(orderingTypes[i])
        }
    }
}

@Composable
fun PokemonTypeFilterTab(modifier: Modifier = Modifier) {
    val pokemonTypes = PokemonType.entries.toTypedArray()

    val checkBoxes = remember { mutableStateListOf<Boolean>() }
    for (i in pokemonTypes.indices) {
        checkBoxes.add(false)

        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = modifier.padding(10.dp, 0.dp)
        ) {
            Checkbox(
                checked = checkBoxes[i],
                onCheckedChange = { checkBoxes[i] = it }
            )
            PokemonTypeIcon(pokemonTypes[i], fontSize = 12.sp)
        }
    }
}

@Composable
fun PokemonGenFilterTab(modifier: Modifier = Modifier) {
    val amountOfGenerations = 9 // TODO: por isso no res
    val checkBoxesTitles = List(amountOfGenerations) { i -> "Gen ${i + 1}"}

    val checkBoxes = remember { mutableStateListOf<Boolean>() }

    for (i in checkBoxesTitles.indices) {
        checkBoxes.add(false)

        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = modifier.padding(10.dp, 5.dp)
        ) {
            Checkbox(
                checked = checkBoxes[i],
                onCheckedChange = { checkBoxes[i] = it }
            )
            Text(
                checkBoxesTitles[i]
            )
        }
    }
}
