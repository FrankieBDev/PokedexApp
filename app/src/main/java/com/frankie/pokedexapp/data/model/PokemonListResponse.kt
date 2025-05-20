package com.frankie.pokedexapp.data.model

import com.google.gson.annotations.SerializedName

data class PokemonResponse(
    @SerializedName("name") val name: String,
    @SerializedName("imageUrl") val imageUrl: String,
    @SerializedName("primaryType") val primaryType: String
)
