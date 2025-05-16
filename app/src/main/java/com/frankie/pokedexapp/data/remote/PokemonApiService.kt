package com.frankie.pokedexapp.data.remote

import com.frankie.pokedexapp.data.model.PokemonDetailResponse
import com.frankie.pokedexapp.data.model.PokemonResult
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface PokemonApiService {
    @GET("pokemon")
    suspend fun getPokemonList(
        @Query("limit") limit: Int = 20,
        @Query("offset") offset: Int = 0
    ): List<PokemonResult>

    @GET("pokemon/{name}")
    suspend fun getPokemonByName(
        @Path("name") name: String
    ): PokemonDetailResponse
}