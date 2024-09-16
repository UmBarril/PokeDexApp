package com.barril.pokedexapp.data

import com.barril.pokedexapp.data.local.entities.PokemonAbilityEntity
import com.barril.pokedexapp.data.local.entities.embeded.PokemonCriesResourceEmbeded
import com.barril.pokedexapp.data.local.entities.PokemonEntity
import com.barril.pokedexapp.data.local.entities.NamedResourceEntity
import com.barril.pokedexapp.data.local.entities.insert.PokemonInsertData
import com.barril.pokedexapp.data.local.entities.embeded.PokemonSpritesResourceEmbeded
import com.barril.pokedexapp.data.local.entities.PokemonStatEntity
import com.barril.pokedexapp.data.local.entities.PokemonTypeEntity
import com.barril.pokedexapp.data.local.entities.relations.PokemonWithRelations
import com.barril.pokedexapp.data.remote.dtos.PokemonDto
import com.barril.pokedexapp.domain.Pokemon
import com.barril.pokedexapp.domain.PokemonAbility
import com.barril.pokedexapp.domain.PokemonCriesResource
import com.barril.pokedexapp.domain.NamedResource
import com.barril.pokedexapp.domain.PokemonSpritesResource
import com.barril.pokedexapp.domain.PokemonStat
import com.barril.pokedexapp.domain.PokemonType
import java.util.EnumSet

fun PokemonDto.toPokemonInsertData(): PokemonInsertData {
    return PokemonInsertData(
        pokemonEntity = PokemonEntity(
            pokemonId = this.id,
            name = this.name,
            height = this.height,
            order = this.order,
            weight = this.weight,
            isFavorite = null,
            sprites = this.sprites.let {
                PokemonSpritesResourceEmbeded(
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
                PokemonCriesResourceEmbeded(
                    latestUrl = it.latest,
                    legacyUrl = it.legacy
                )
            },
        ),
        abilities = this.abilities.map {
            PokemonAbilityEntity(
                pokemonId = this.id,
                name = it.ability.name,
                isHidden = it.is_hidden,
                url = it.ability.url,
                slot = it.slot
            )
        },
        heldItems = this.held_items.map {
            NamedResourceEntity(
                name = it.item.name,
                url = it.item.url,
                type = "helditem"
            )
        },
        moves = this.moves.map {
            NamedResourceEntity(
                name = it.move.name,
                url = it.move.url,
                type = "move"
            )
        },
        stats = this.stats.map {
            PokemonStatEntity(
                pokemonId = this.id,
                name = it.stat.name,
                baseStat = it.base_stat,
                effort = it.effort,
                url = it.stat.url
            )
        },
        types = this.types.map {
            PokemonTypeEntity(typeName = it.type.name)
        },
//        favorite = null
    )
}

fun PokemonWithRelations.toPokemon(): Pokemon {
    return Pokemon(
        id = this.pokemonEntity.pokemonId,
        name = this.pokemonEntity.name,
        height = this.pokemonEntity.height,
        order = this.pokemonEntity.order,
        weight = this.pokemonEntity.weight,
        isFavorite = this.pokemonEntity.isFavorite ?: false,
        sprites = this.pokemonEntity.sprites.let {
            PokemonSpritesResource(
                backDefaultUrl = it.backDefaultUrl,
                backFemaleUrl = it.backFemaleUrl,
                backShinyUrl = it.backShinyUrl,
                backShinyFemaleUrl = it.backShinyFemaleUrl,
                frontDefaultUrl = it.frontDefaultUrl,
                frontFemaleUrl = it.frontFemaleUrl,
                frontShinyUrl = it.frontShinyUrl,
                frontShinyFemaleUrl = it.frontFemaleUrl
            )
        },
        cries = this.pokemonEntity.cries.let {
            PokemonCriesResource(
                latestUrl = it.latestUrl,
                legacyUrl = it.legacyUrl
            )
        },
        stats = this.stats.map {
            PokemonStat(
                name = it.name,
                baseStat = it.baseStat,
                effort = it.effort,
                url = it.url
            )
        },
        types = this.types.mapTo(EnumSet.noneOf(PokemonType::class.java)) {
            PokemonType.fromString(it.typeName)
        },
        abilities = this.abilities.map {
            PokemonAbility(
                name = it.name,
                isHidden = it.isHidden,
                url = it.url,
                slot = it.slot
            )
        },
        heldItems = this.heldItems.map {
            NamedResource(
                name = it.name,
                url = it.url
            )
        },
        moves = this.moves.map {
            NamedResource(
                name = it.name,
                url = it.url
            )
        },
    )
}

