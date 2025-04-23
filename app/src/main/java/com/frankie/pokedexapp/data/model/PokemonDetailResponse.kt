package com.frankie.pokedexapp.data.model

import com.google.gson.annotations.SerializedName

data class PokemonDetailResponse(
    @SerializedName("id") val id: Int,
    @SerializedName("name") val name: String,
    @SerializedName("types") val types: List<Type>,
    @SerializedName("sprites") val sprites: Sprites
)

data class Type(
    @SerializedName("slot") val slot: Int,
    @SerializedName("type") val type: TypeDetails
)

data class TypeDetails(
    @SerializedName("name") val name: String
)

data class Sprites(
    @SerializedName("front_default") val frontDefault: String
)
