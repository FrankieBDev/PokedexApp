package com.example.pokedexapp.ui.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.pokedexapp.data.remote.RetrofitInstance
import com.example.pokedexapp.data.repository.PokemonRepository
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class PokedexViewModel : ViewModel() {

    private val repository: PokemonRepository = PokemonRepository(RetrofitInstance.apiService)

    private val _uiState = MutableStateFlow<PokedexUiState>(PokedexUiState.Loading)
    val uiState: StateFlow <PokedexUiState> = _uiState

    fun fetchPokemonList() {
        viewModelScope.launch {
            _uiState.value = PokedexUiState.Loading
            try {
                val pokemonList = repository.getPokemonList()
                _uiState.value = PokedexUiState.Success(pokemonList)
            } catch (e: Exception) {
                _uiState.value = PokedexUiState.Error("Failed to load Pokemon")
            }
        }
    }
}

sealed class PokedexUiState {
    object Loading : PokedexUiState()
    data class Success(val pokemonList: List<String>) : PokedexUiState()
    data class Error(val message: String) : PokedexUiState()
}


