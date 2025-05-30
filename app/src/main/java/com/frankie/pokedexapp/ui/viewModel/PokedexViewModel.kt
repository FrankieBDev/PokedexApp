package com.frankie.pokedexapp.ui.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.frankie.pokedexapp.data.model.PokemonDetailResponse
import com.frankie.pokedexapp.data.model.PokemonResponse
import com.frankie.pokedexapp.data.repository.PokemonRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class PokedexViewModel(
    private val repository: PokemonRepository = PokemonRepository()
) : ViewModel() {

    private val _uiState = MutableStateFlow<PokedexUiState>(PokedexUiState.Loading)
    val uiState: StateFlow<PokedexUiState> = _uiState

    private val _pokemonDetail = MutableLiveData<PokemonDetailResponse>()
    val pokemonDetail: LiveData<PokemonDetailResponse> get() = _pokemonDetail

    private var currentOffset = 0
    private val limit = 20
    private val currentList = mutableListOf<PokemonResponse>()

    fun fetchPokemonList() {
        loadPokemonList(reset = true)
    }

    fun fetchMorePokemon() {
        val currentState = _uiState.value
        if (currentState is PokedexUiState.Success && !currentState.isLoadingMore) {
            loadPokemonList(reset = false)
        }
    }

    private fun loadPokemonList(reset: Boolean) {
        viewModelScope.launch {
            if (_uiState.value is PokedexUiState.Success && !reset) {
                _uiState.value = (_uiState.value as PokedexUiState.Success).copy(isLoadingMore = true)
            } else {
                _uiState.value = PokedexUiState.Loading
            }

            if (reset) {
                currentOffset = 0
                currentList.clear()
            }

            try {
                val newPokemon = repository.getPokemonList(limit, currentOffset)
                currentList.addAll(newPokemon)
                currentOffset += limit
                _uiState.value = PokedexUiState.Success(currentList.toList(), isLoadingMore = false)
            } catch (e: Exception) {
                val errorMessage = if (reset) "Failed to load Pokémon list" else "Failed to load more Pokémon"
                _uiState.value = PokedexUiState.Error(errorMessage)
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
    data class Success(
        val pokemonList: List<PokemonResponse>,
        val isLoadingMore: Boolean = false
    ) : PokedexUiState()
    data class Error(val message: String) : PokedexUiState()
}
