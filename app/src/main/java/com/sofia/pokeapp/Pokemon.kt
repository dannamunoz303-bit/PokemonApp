package com.sofia.pokeapp

// Representa la respuesta completa del endpoint /pokemon
data class PokemonListResponse(
    val count: Int,
    val results: List<Pokemon>
)

// Cada pokémon individual de la lista
data class Pokemon(
    val name: String,
    val url: String
)