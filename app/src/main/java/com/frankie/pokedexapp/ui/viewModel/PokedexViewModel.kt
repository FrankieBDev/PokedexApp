package com.frankie.pokedexapp.ui.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.frankie.pokedexapp.data.model.PokemonDetailResponse
import com.frankie.pokedexapp.data.repository.PokemonRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class PokedexViewModel : ViewModel() {

    private val repository= PokemonRepository()

    private val _uiState = MutableStateFlow<PokedexUiState>(PokedexUiState.Loading)
    val uiState: StateFlow <PokedexUiState> = _uiState

    private val _pokemonDetail = MutableLiveData<PokemonDetailResponse>()
    val pokemonDetail: LiveData<PokemonDetailResponse> get() = _pokemonDetail

    fun fetchPokemonList() {
        viewModelScope.launch {
            _uiState.value = PokedexUiState.Loading
            try {
                val response = repository.getPokemonList(limit = 20, offset = 0)
                val names = response.results.map { it.name }
                _uiState.value = PokedexUiState.Success(names)
            } catch (e: Exception) {
                _uiState.value = PokedexUiState.Error("Failed to load Pokémon list")
            }
        }
    }

    fun getPokemonDetail(name: String) {
        viewModelScope.launch {
            try {
                val response = repository.getPokemonDetail(name)
                _pokemonDetail.postValue(response)
            } catch (e: Exception) {
                _uiState.value = PokedexUiState.Error("Failed to load Pokemon Info")
            }
        }
    }
}

sealed class PokedexUiState {
    object Loading : PokedexUiState()
    data class Success(val pokemonList: List<String>) : PokedexUiState()
    data class Error(val message: String) : PokedexUiState()
}


