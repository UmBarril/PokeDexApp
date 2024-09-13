package com.barril.pokedexapp.data

import com.barril.pokedexapp.data.api.PokemonAbilityDto
import com.barril.pokedexapp.data.api.PokemonCriesDto
import com.barril.pokedexapp.data.api.PokemonHeldItemDto
import com.barril.pokedexapp.data.api.PokemonMoveDto
import com.barril.pokedexapp.data.api.PokemonDto
import com.barril.pokedexapp.data.api.PokemonSpritesDto
import com.barril.pokedexapp.data.api.PokemonStatDto
import com.barril.pokedexapp.data.api.PokemonTypeDto
import java.util.EnumSet

private fun List<PokemonAbilityDto>.toAbilityList(): List<PokemonAbility> {
    val listOfAbility = mutableListOf<PokemonAbility>()
    this.forEach {
        listOfAbility.add(
            PokemonAbility(
               it.ability.name,
               it.is_hidden,
               it.ability.url,
               it.slot
            )
        )
    }
    return listOfAbility
}

private fun List<PokemonHeldItemDto>.toHeldItemsList(): List<PokemonHeldItemResource> {
    val listOfHeldItem = mutableListOf<PokemonHeldItemResource>()
    this.forEach {
        listOfHeldItem.add(
            PokemonHeldItemResource(
                it.item.name,
                it.item.url
            )
        )
    }
    return listOfHeldItem
}

private fun List<PokemonMoveDto>.toMoveList(): List<PokemonMoveResource> {
    val listOfMove = mutableListOf<PokemonMoveResource>()
    this.forEach {
        listOfMove.add(
            PokemonMoveResource(
                it.move.name,
                it.move.url
            )
        )
    }
    return listOfMove
}

private fun PokemonSpritesDto.toSprites(): PokemonSpritesResource {
    return PokemonSpritesResource(
        backDefaultUrl = this.back_default,
        backFemaleUrl = this.back_female,
        backShinyUrl = this.back_shiny,
        backShinyFemaleUrl = this.back_shiny_female,
        frontDefaultUrl = this.front_default,
        frontFemaleUrl = this.front_female,
        frontShinyUrl = this.front_shiny,
        frontShinyFemaleUrl = this.front_shiny_female
    )
}

private fun List<PokemonStatDto>.toStatList(): List<PokemonStat> {
    val listOfStat = mutableListOf<PokemonStat>()
    this.forEach {
        listOfStat.add(
            PokemonStat(
                it.stat.name,
                it.base_stat,
                it.effort,
                it.stat.url
            )
        )
    }
    return listOfStat
}

private fun List<PokemonTypeDto>.toPokemonTypeSet(): EnumSet<PokemonType> {
    val typeSet = EnumSet.noneOf(PokemonType::class.java)
    this.forEach {
        typeSet.add(PokemonType.fromString(it.type.name))
    }
    return typeSet
}

fun PokemonDto.toPokemon(): Pokemon {
    return Pokemon(
        id = this.id,
        name = this.name,
        height = this.height,
        order = this.order,
        weight = this.weight,
        abilities = this.abilities.toAbilityList(),
        heldItems = this.held_items.toHeldItemsList(),
        moves = this.moves.toMoveList(),
        sprites = this.sprites.toSprites(),
        cries = this.cries.let { PokemonCriesResource(it.latest, it.legacy) },
        stats = this.stats.toStatList(),
        types = this.types.toPokemonTypeSet()
    )
}