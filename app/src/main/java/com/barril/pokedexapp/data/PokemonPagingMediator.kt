package com.barril.pokedexapp.data

import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadType
import androidx.paging.PagingState
import androidx.paging.RemoteMediator
import androidx.room.withTransaction
import com.barril.pokedexapp.data.remote.PokemonApiDao
import com.barril.pokedexapp.data.local.PokemonDatabase
import com.barril.pokedexapp.data.local.entities.NamedResourceEntity
import com.barril.pokedexapp.data.local.entities.PokemonAbilityEntity
import com.barril.pokedexapp.data.local.entities.PokemonEntity
import com.barril.pokedexapp.data.local.entities.PokemonStatEntity
import com.barril.pokedexapp.data.local.entities.PokemonTypeEntity
import com.barril.pokedexapp.data.local.entities.embeded.PokemonCriesResourceEmbeded
import com.barril.pokedexapp.data.local.entities.embeded.PokemonSpritesResourceEmbeded
import com.barril.pokedexapp.data.local.entities.relations.PokemonWithRelations
import retrofit2.HttpException
import java.io.IOException

@OptIn(ExperimentalPagingApi::class)
class PokemonPagingMediator(
    private val database: PokemonDatabase,
    private val api: PokemonApiDao
): RemoteMediator<Int, PokemonWithRelations>() {

    override suspend fun load(
        loadType: LoadType,
        state: PagingState<Int, PokemonWithRelations>
    ): MediatorResult {
        return try {
            val offset = when (loadType) {
                LoadType.REFRESH -> null
                LoadType.PREPEND -> return MediatorResult.Success(endOfPaginationReached = true)
                LoadType.APPEND -> {
                    val lastItem = state.lastItemOrNull()
                        ?: return MediatorResult.Success(endOfPaginationReached = true)

                    lastItem.pokemonEntity.pokemonId
                }
            } ?: state.config.initialLoadSize
            val response = api.getPokemonPage(state.config.pageSize, offset)

            database.withTransaction {
                if (loadType == LoadType.REFRESH) {
                    database.pokemonDbDao().clearAllPokemonsExceptFavorites()
                }

                val pokemonDtos = response.results.map {
                    api.getPokemon(it.name)
                }

                val pokemonInsertData = pokemonDtos.map {
                    it.toPokemonInsertData()
                }

                database.pokemonDbDao().insertPokemonData(pokemonInsertData)
            }

            MediatorResult.Success(endOfPaginationReached = response.results.isEmpty())
        } catch (e: IOException) {
            MediatorResult.Error(e)
        } catch (e: HttpException) {
            MediatorResult.Error(e)
        }
    }
}
