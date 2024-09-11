package com.barril.pokedexapp.data

import com.barril.pokedexapp.data.api.AbilityDto
import com.barril.pokedexapp.data.api.CriesDto
import com.barril.pokedexapp.data.api.HeldItemDto
import com.barril.pokedexapp.data.api.MoveDto
import com.barril.pokedexapp.data.api.PokemonDto
import com.barril.pokedexapp.data.api.SpritesDto
import com.barril.pokedexapp.data.api.StatDto
import com.barril.pokedexapp.data.api.TypeDto

private fun List<AbilityDto>.toAbilityList(): List<Ability> {
    val listOfAbility = mutableListOf<Ability>()
    this.forEach {
        listOfAbility.add(
            Ability(
               it.ability.name,
               it.isHidden,
               it.ability.url,
               it.slot
            )
        )
    }
    return listOfAbility
}

private fun List<HeldItemDto>.toHeldItemsList(): List<HeldItem> {
    val listOfHeldItem = mutableListOf<HeldItem>()
    this.forEach {
        listOfHeldItem.add(
            HeldItem(
                it.item.name,
                it.item.url
            )
        )
    }
    return listOfHeldItem
}

private fun List<MoveDto>.toMoveList(): List<Move> {
    val listOfMove = mutableListOf<Move>()
    this.forEach {
        listOfMove.add(
            Move(
                it.move.name,
                it.move.url
            )
        )
    }
    return listOfMove
}

private fun SpritesDto.toSprites(): Sprites {
    return Sprites(
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

private fun List<CriesDto>.toCryList(): List<Cry> {
    val listOfCries = mutableListOf<Cry>()
    this.forEach {
        listOfCries.add(
            Cry(
                it.latest,
                it.legacy,
            )
        )
    }
    return listOfCries
}

private fun List<StatDto>.toStatList(): List<Stat> {
    val listOfStat = mutableListOf<Stat>()
    this.forEach {
        listOfStat.add(
            Stat(
                it.stat.name,
                it.baseStat,
                it.effort,
                it.stat.url
            )
        )
    }
    return listOfStat
}

private fun List<TypeDto>.toPokemonTypeList(): List<PokemonType> {
    val listOfPokemonType = mutableListOf<PokemonType>()
    this.forEach {
        listOfPokemonType.add(PokemonType.fromString(it.type.name))
    }
    return listOfPokemonType
}

fun PokemonDto.toPokemon(): Pokemon {
    return Pokemon(
        id = this.id,
        name = this.name,
        height = this.height,
        order = this.order,
        weight = this.weight,
        abilities = this.abilities.toAbilityList(),
        heldItems = this.heldItems.toHeldItemsList(),
        moves = this.moves.toMoveList(),
        sprites = this.sprites.toSprites(),
        cries = this.cries.toCryList(),
        stats = this.stats.toStatList(),
        types = this.types.toPokemonTypeList()
    )
}