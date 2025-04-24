package com.frankie.pokedexapp.data.repository

import com.frankie.pokedexapp.data.model.PokemonDetailResponse
import com.frankie.pokedexapp.data.model.PokemonListResponse
import com.frankie.pokedexapp.data.remote.PokemonApiService
import com.frankie.pokedexapp.data.remote.RetrofitClient


class PokemonRepository {

    private val apiService: PokemonApiService =
        RetrofitClient.instance.create(PokemonApiService::class.java)

    suspend fun getPokemonList(limit: Int, offset: Int): PokemonListResponse {
        return apiService.getPokemonList(limit, offset)
    }

    suspend fun getPokemonDetail(name: String): PokemonDetailResponse {
        return apiService.getPokemonByName(name)
    }
}