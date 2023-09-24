package com.example.eventapp.data.repository

import com.example.eventapp.data.model.countryModel.Country
import com.example.eventapp.service.countryApi

class CountryRepository() {
    suspend fun getCountries():List<Country>{
        return countryApi.retrofitService.getData().data
    }
}