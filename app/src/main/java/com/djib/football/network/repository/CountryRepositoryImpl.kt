package com.djib.football.network.repository

import com.djib.football.domain.model.CountryResponse
import com.djib.football.domain.reporitory.CountryRepository
import com.djib.football.network.service.CountryService
import com.djib.football.utils.Response
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class CountryRepositoryImpl @Inject constructor(private val countryService: CountryService): CountryRepository {

    override fun getAllCountries(action:String, key:String): Flow<Response<List<CountryResponse>>> = flow{
        try {
            emit(Response.Loading)
            val responseApi = countryService.getAllGames(action , key)
            emit(Response.Success(responseApi))

        }catch (e: Exception) {
            emit(Response.Failure(e))
        }
    }.flowOn(Dispatchers.IO)

}
