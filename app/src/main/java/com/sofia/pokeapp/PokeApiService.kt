package com.sofia.pokeapp

import retrofit2.http.GET
import retrofit2.http.Query

interface PokeApiService {

    // GET https://pokeapi.co/api/v2/pokemon?limit=20
    @GET("pokemon")
    suspend fun getPokemonList(
        @Query("limit") limit: Int = 20
    ): PokemonListResponse
}