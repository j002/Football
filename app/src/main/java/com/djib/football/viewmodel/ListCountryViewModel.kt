package com.djib.football.viewmodel

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.djib.football.domain.model.CountryResponse
import com.djib.football.domain.reporitory.CountryRepository
import com.djib.football.utils.Const.API_KEY
import com.djib.football.utils.Const.GETCONTRIESACTION
import com.djib.football.utils.Response
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ListCountryViewModel @Inject constructor(private val countryRepository: CountryRepository ) : ViewModel(){

    private val _countryListState = mutableStateOf<Response<List<CountryResponse>>>(Response.Success(null))
    val countryListState: State<Response<List<CountryResponse>>>  = _countryListState


    fun getAllCountries(){
       viewModelScope.launch {
           countryRepository.getAllCountries(GETCONTRIESACTION,API_KEY).collect{ response->
               _countryListState.value = response
           }
       }
    }
}