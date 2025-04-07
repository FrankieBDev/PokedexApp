package com.example.pokedexapp.data.repository

import com.example.pokedexapp.data.remote.PokemonApiService

class PokemonRepository(private val apiService: PokemonApiService) {

    suspend fun getPokemonList(limit: Int = 20, offset: Int = 0): List<String> {
        return try {
            val response = apiService.getPokemonList(limit, offset)
            response.results.map { it.name }
        } catch (e: Exception) {
            emptyList()
        }
    }
}