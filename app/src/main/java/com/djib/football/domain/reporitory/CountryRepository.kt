package com.djib.football.domain.reporitory

import com.djib.football.domain.model.CountryResponse
import com.djib.football.utils.Response
import kotlinx.coroutines.flow.Flow

interface CountryRepository {
    fun getAllCountries(action:String , key:String): Flow<Response<List<CountryResponse>>>
}