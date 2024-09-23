package com.barril.pokedexapp.viewmodels

import androidx.annotation.StringRes
import com.barril.pokedexapp.R

enum class PokemonOrderingColumn(
    val databaseName: String,
    @StringRes val description: Int
) {
    POKEMON_ID("pokemonId", R.string.order_by_ids),
    //    GENERATION("", stringResource(R.string.order_by_gen)),
    TYPE("typeName", R.string.order_by_type),
    NAME("name", R.string.order_by_name)
}
