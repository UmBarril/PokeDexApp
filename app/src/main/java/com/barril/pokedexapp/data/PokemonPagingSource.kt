package com.barril.pokedexapp.data

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.barril.pokedexapp.domain.Pokemon

//class PokemonPagingSource(
//    val repository: PokemonRepository
//) : PagingSource<Int, Pokemon>() {
//    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Pokemon> {
//        val position = params.key ?: POKEAPI_STATING_POSITION
//
//        val results = repository.getPokemonPage(position, POKEAPI_PAGE_SIZE)
//
////        val nextKey = if (results. / POKEAPI_PAGE_SIZE)
//
//        return try {
//            LoadResult.Page(
//                ,
//                prevKey = if (position == GITHUB_STARTING_PAGE_INDEX) null else position - 1,
//                nextKey = nextKey
//            )
//        } catch (e: Exception) {
//            LoadResult.Error(e)
//        }
//    }
//
//    override fun getRefreshKey(state: PagingState<Int, Pokemon>): Int? {
//        TODO("Not yet implemented")
//    }
//}
