package com.djib.football.network.service

import com.djib.football.domain.model.CountryResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface CountryService {
    @GET("?")
    suspend fun getAllGames(
        @Query("action") action: String,
        @Query("APIkey") apiKey: String) : List<CountryResponse>
}