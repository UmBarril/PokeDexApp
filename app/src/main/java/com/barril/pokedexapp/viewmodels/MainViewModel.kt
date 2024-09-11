package com.barril.pokedexapp.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MainViewModel : ViewModel() {

    val teste: MutableLiveData<String> by lazy {
        MutableLiveData<String>()
    }

    fun teste2() {
//        teste.
    }

}