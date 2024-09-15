package com.barril.pokedexapp

import android.app.Application
import com.barril.pokedexapp.di.AppModule
import com.barril.pokedexapp.di.AppModuleImpl

class PokeDexApplication : Application() {

    companion object {
        lateinit var appModule: AppModule
    }

    override fun onCreate() {
        super.onCreate()
        appModule = AppModuleImpl(this)
    }

}