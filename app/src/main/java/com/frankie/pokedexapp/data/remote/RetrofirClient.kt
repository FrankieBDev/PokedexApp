package com.frankie.pokedexapp.data.remote

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

val retrofit = Retrofit.Builder()
    .baseUrl("https://pokeapi.co/api/v2/")
    .addConverterFactory(GsonConverterFactory.create())
    .build()

val pokemonApiService = retrofit.create(PokemonApiService::class.java)
