package com.barril.pokedexapp.ui.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.RadioButton
import androidx.compose.material3.SecondaryTabRow
import androidx.compose.material3.SheetState
import androidx.compose.material3.Surface
import androidx.compose.material3.Tab
import androidx.compose.material3.Text
import androidx.compose.material3.surfaceColorAtElevation
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.room.Index
import com.barril.pokedexapp.R
import com.barril.pokedexapp.domain.PokemonType
import com.barril.pokedexapp.domain.PokemonOrderingColumn

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FilterAndOrderBottomSheet(
    selectedFilter: PokemonType?,
    onFilterClicked: (PokemonType) -> Unit,
    currentOrderingDirection: Index.Order,
    currentOrderingColumn: PokemonOrderingColumn,
    onOrderingClicked: (PokemonOrderingColumn) -> Unit,
    onDismissRequest: () -> Unit,
    sheetState: SheetState,
    modifier: Modifier = Modifier
) {
    var state by remember { mutableIntStateOf(0) }
    val titles = listOf(
//        stringResource(R.string.filter_by_gen), // TODO: talvez
        stringResource(R.string.filter_by_type),
        stringResource(R.string.order)
    )

    ModalBottomSheet(
        onDismissRequest = onDismissRequest,
        sheetState = sheetState,
        dragHandle = {
            Column {
                SecondaryTabRow(selectedTabIndex = state) {
                    titles.forEachIndexed { index, title ->
                        Tab(
                            text = {
                                Text(title)
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
                PokemonTypeFilterTab(
                    selectedFilter = selectedFilter,
                    onFilterClicked = onFilterClicked
                )
            }
            1 -> {
                PokemonOrderingTab(
                    onRowClicked = onOrderingClicked,
                    currentOrderingColumn = currentOrderingColumn,
                    currentOrderingDirection = currentOrderingDirection,
                )
            }
        }
    }
}

@Composable
fun PokemonOrderingTab(
    currentOrderingColumn: PokemonOrderingColumn,
    onRowClicked: (PokemonOrderingColumn) -> Unit,
    currentOrderingDirection: Index.Order,
    modifier: Modifier = Modifier
) {
    Column {
        for (column in PokemonOrderingColumn.entries) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = modifier
                    .fillMaxWidth()
                    .padding(20.dp)
                    .clickable {
                        onRowClicked(column)
                    }
            ) {
                if (column != currentOrderingColumn) {
                    Icon(
                        Icons.Default.KeyboardArrowDown,
                        contentDescription = null,
                        // se não estiver selecionado, não mostrar icone
                        tint = Color.Transparent
                    )
                } else if (currentOrderingDirection == Index.Order.ASC) {
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
                Text(stringResource(column.description))
            }
        }
    }
}

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun PokemonTypeFilterTab(
    selectedFilter: PokemonType?,
    onFilterClicked: (PokemonType) -> Unit,
    modifier: Modifier = Modifier
) {
    val pokemonTypes = PokemonType.entries.toTypedArray()

    var selected by remember { mutableIntStateOf(-1) }
    FlowRow(
        modifier = modifier.padding(10.dp, 0.dp)
    ) {
        pokemonTypes.forEachIndexed { i, pokemonType ->
            val isThisRadioSelected = selectedFilter == pokemonType
            if (isThisRadioSelected) {
                selected = i
            }
            Surface(
                color = MaterialTheme.colorScheme.surfaceColorAtElevation(20.dp),
                shape = RoundedCornerShape(20.dp),
                modifier = Modifier
                    .padding(8.dp)
            ) {
                Row {
                    RadioButton(
                        selected = isThisRadioSelected,
                        onClick = {
                            selected = if (isThisRadioSelected) {
                                -1
                            } else {
                                i
                            }
                            onFilterClicked(pokemonType)
                        },
                    )
                    PokemonTypeIcon(
                        pokemonType,
                        fontSize = 12.sp,
                        modifier = Modifier
                            .align(Alignment.CenterVertically)
                            .padding(end = 10.dp)
                    )
                }
            }
        }
    }
}

//
//@Composable
//fun PokemonGenFilterTab(modifier: Modifier = Modifier) {
//    val amountOfGenerations = 9
//    val checkBoxesTitles = List(amountOfGenerations) { i -> "Gen ${i + 1}"}
//
//    val checkBoxes = remember { mutableStateListOf<Boolean>() }
//
//    for (i in checkBoxesTitles.indices) {
//        checkBoxes.add(false)
//
//        Row(
//            verticalAlignment = Alignment.CenterVertically,
//            modifier = modifier.padding(10.dp, 5.dp)
//        ) {
//            Checkbox(
//                checked = checkBoxes[i],
//                onCheckedChange = { checkBoxes[i] = it }
//            )
//            Text(
//                checkBoxesTitles[i]
//            )
//        }
//    }
//}
