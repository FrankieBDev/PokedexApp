package com.example.pokedexapp.ui.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class PokedexViewModel : ViewModel() {

    private val _pokemonList = MutableStateFlow<List<String>>(emptyList())
    val pokemonList: StateFlow<List<String>> = _pokemonList

    fun fetchPokemonList() {
        viewModelScope.launch {
            delay(2000) // Simulates a network call
            // replace with API call
            _pokemonList.value = listOf("Bulbasaur", "Charmander", "Squirtle")
        }
    }

}


