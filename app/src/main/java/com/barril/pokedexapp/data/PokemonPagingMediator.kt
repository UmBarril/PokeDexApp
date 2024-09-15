package com.barril.pokedexapp.data

import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadType
import androidx.paging.PagingState
import androidx.paging.RemoteMediator
import androidx.room.withTransaction
import com.barril.pokedexapp.data.remote.PokemonApiDao
import com.barril.pokedexapp.data.local.PokemonDatabase
import com.barril.pokedexapp.data.local.PokemonEntity
import retrofit2.HttpException
import java.io.IOException

@OptIn(ExperimentalPagingApi::class)
class PokemonPagingMediator(
    private val database: PokemonDatabase,
    private val api: PokemonApiDao
): RemoteMediator<Int, PokemonEntity>() {

    override suspend fun load(
        loadType: LoadType,
        state: PagingState<Int, PokemonEntity>
    ): MediatorResult {
        return try {
            val loadKey = when (loadType) {
                LoadType.REFRESH -> null
                LoadType.PREPEND -> return MediatorResult.Success(endOfPaginationReached = true)
                LoadType.APPEND -> {
                    val lastItem = state.lastItemOrNull()
                        ?: return MediatorResult.Success(endOfPaginationReached = true)

                    lastItem.id
                }
            }
            val offset = if (loadKey != null) {
                loadKey * state.config.pageSize
            } else {
                state.config.initialLoadSize
            }
            val response = api.getPokemonPage(state.config.pageSize, offset)

            database.withTransaction {
                if (loadType == LoadType.REFRESH) {
                    database.pokemonDbDao().clearAllPokemonsExceptFavorites()
                }

                val pokemonDtos = response.results.map {
                    api.getPokemon(it.name)
                }
                val pokemonEntities = pokemonDtos.map {
                    it.toPokemonEntity()
                }
                database.pokemonDbDao().insertPokemons(pokemonEntities)
            }

            MediatorResult.Success(endOfPaginationReached = response.results.isEmpty())
        } catch (e: IOException) {
            MediatorResult.Error(e)
        } catch (e: HttpException) {
            MediatorResult.Error(e)
        }
    }
}
