package com.example.network

import com.example.network.model.ResponseData
import retrofit2.http.GET
import retrofit2.http.Query

interface APIService {
    @GET("pokemon")
    suspend fun getPokemonName(@Query("limit") limit: Int, @Query("offset") offset: Int): ResponseData

    companion object {
        const val API_URL = "https://pokeapi.co/api/v2/"
    }
}