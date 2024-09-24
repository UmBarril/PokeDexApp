package com.barril.pokedexapp.di

import android.content.Context
import androidx.room.Room
import com.barril.pokedexapp.data.local.PokemonDatabase
import com.barril.pokedexapp.data.remote.PokemonApiDao
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

interface AppModule {
    val pokemonApi: PokemonApiDao
    val pokemonDatabase: PokemonDatabase
}

/**
 * Injeção de depend6encia manual
 */
class AppModuleImpl(
    val context: Context
): AppModule {
    companion object {
        private const val BASE_URL = "https://pokeapi.co/api/v2/"
        const val POKEAPI_PAGE_SIZE = 20
    }

    override val pokemonApi: PokemonApiDao by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(PokemonApiDao::class.java)
    }

    override val pokemonDatabase: PokemonDatabase by lazy {
        Room.databaseBuilder(context.applicationContext,
            PokemonDatabase::class.java, "Pokemon.db")
            .build()
    }
}