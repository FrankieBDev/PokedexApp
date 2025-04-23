package com.frankie.pokedexapp.data.repository

import com.frankie.pokedexapp.data.model.PokemonDetailResponse
import com.frankie.pokedexapp.data.remote.PokemonApiService

class PokemonRepository(private val apiService: PokemonApiService) {

    suspend fun getPokemonList(limit: Int = 20, offset: Int = 0): List<String> {
        return try {
            val response = apiService.getPokemonList(limit, offset)
            response.results.map { it.name }
        } catch (e: Exception) {
            emptyList()
        }
    }

    suspend fun getPokemonDetail(name: String): PokemonDetailResponse{
        return apiService.getPokemonByName(name)
    }
}