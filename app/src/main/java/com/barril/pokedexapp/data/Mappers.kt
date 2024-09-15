package com.barril.pokedexapp.data

import com.barril.pokedexapp.data.local.PokemonAbilityEntity
import com.barril.pokedexapp.data.local.PokemonCriesResourceEntity
import com.barril.pokedexapp.data.local.PokemonEntity
import com.barril.pokedexapp.data.local.NamedResourceEntity
import com.barril.pokedexapp.data.local.PokemonSpritesResourceEntity
import com.barril.pokedexapp.data.local.PokemonStatEntity
import com.barril.pokedexapp.data.remote.PokemonDto
import com.barril.pokedexapp.domain.Pokemon
import com.barril.pokedexapp.domain.PokemonAbility
import com.barril.pokedexapp.domain.PokemonCriesResource
import com.barril.pokedexapp.domain.NamedResource
import com.barril.pokedexapp.domain.PokemonSpritesResource
import com.barril.pokedexapp.domain.PokemonStat
import com.barril.pokedexapp.domain.PokemonType
import java.util.EnumSet

fun PokemonDto.toPokemon(): Pokemon {
    return Pokemon(
        id = this.id,
        name = this.name,
        height = this.height,
        order = this.order,
        weight = this.weight,
        isFavorite = false,
        abilities = this.abilities.map {
            PokemonAbility(
                it.ability.name,
                it.is_hidden,
                it.ability.url,
                it.slot
            )
        },
        heldItems = this.held_items.map {
            NamedResource(
                name= it.item.name,
                url = it.item.url
            )
        },
        moves = this.moves.map {
            NamedResource(
                name = it.move.name,
                url = it.move.url
            )
        },
        sprites = this.sprites.let {
            PokemonSpritesResource(
                backDefaultUrl = it.back_default,
                backFemaleUrl = it.back_female,
                backShinyUrl = it.back_shiny,
                backShinyFemaleUrl = it.back_shiny_female,
                frontDefaultUrl = it.front_default,
                frontFemaleUrl = it.front_female,
                frontShinyUrl = it.front_shiny,
                frontShinyFemaleUrl = it.front_shiny_female
            )
        },
        cries = this.cries.let { PokemonCriesResource(it.latest, it.legacy) },
        stats = this.stats.map {
            PokemonStat(
                name = it.stat.name,
                baseStat = it.base_stat,
                effort = it.effort,
                url = it.stat.url
            )
        },
        types = this.types.mapTo(EnumSet.noneOf(PokemonType::class.java)) {
            PokemonType.fromString(it.type.name)
        }
    )
}

fun PokemonDto.toPokemonEntity(): PokemonEntity {
    return PokemonEntity(
        id = this.id,
        name = this.name,
        height = this.height,
        order = this.order,
        weight = this.weight,
//        isFavorite = false,
        abilities = this.abilities.map {
            PokemonAbilityEntity(
                name = it.ability.name,
                isHidden = it.is_hidden,
                url = it.ability.url,
                slot = it.slot
            )
        },
        heldItems = this.held_items.map {
            NamedResourceEntity(
                name = it.item.name,
                url = it.item.url
            )
        },
        moves = this.moves.map {
            NamedResourceEntity(
                name = it.move.name,
                url = it.move.url
            )
        },
        sprites = this.sprites.let {
            PokemonSpritesResourceEntity(
                backDefaultUrl = it.back_default,
                backFemaleUrl = it.back_female,
                backShinyUrl = it.back_shiny,
                backShinyFemaleUrl = it.back_shiny_female,
                frontDefaultUrl = it.front_default,
                frontFemaleUrl = it.front_female,
                frontShinyUrl = it.front_shiny,
                frontShinyFemaleUrl = it.front_shiny_female
            )
        },
        cries = this.cries.let {
            PokemonCriesResourceEntity(
                latestUrl = it.latest,
                legacyUrl = it.legacy
            )
                               },
        stats = this.stats.map {
            PokemonStatEntity(
                name = it.stat.name,
                baseStat = it.base_stat,
                effort = it.effort,
                url = it.stat.url
            )
        },
        types = this.types.map {
            it.type.name
        }
    )
}

