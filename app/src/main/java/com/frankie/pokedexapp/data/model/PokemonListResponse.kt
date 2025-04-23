package com.frankie.pokedexapp.data.model

import com.google.gson.annotations.SerializedName

data class PokemonListResponse(
    @SerializedName("results") val results: List<PokemonResult>
)

data class PokemonResult(
    @SerializedName("name") val name: String,
    @SerializedName("url") val url: String
)