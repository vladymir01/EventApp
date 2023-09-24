package com.example.eventapp.ui.viewModel

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.eventapp.TAG
import com.example.eventapp.data.model.countryModel.Country
import com.example.eventapp.data.repository.CountryRepository
import kotlinx.coroutines.launch

class CountryViewModel:ViewModel() {
    val countryRepository = CountryRepository()
    var countries:List<Country> by mutableStateOf(listOf())

    init {
        getTheCountries()
    }

    private fun getTheCountries(){
        viewModelScope.launch {
            try {
                countries = countryRepository.getCountries()
                Log.d(TAG, "The number of countries is: ${countries.size}")
            }catch (e:Exception){

            }
        }
    }
}