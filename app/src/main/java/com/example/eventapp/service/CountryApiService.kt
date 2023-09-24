package com.example.eventapp.service

import com.example.eventapp.data.model.countryModel.Countries
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET


private const val BASE_URL_COUNTRY = "https://countriesnow.space/api/v0.1/"

val retrofitCountry =  Retrofit.Builder()
    .baseUrl(BASE_URL_COUNTRY)
    .addConverterFactory(GsonConverterFactory.create())
    .build()

interface MyCountryApiService{

    @GET("countries")
    suspend fun getData():Countries
}

object countryApi {
    val retrofitService: MyCountryApiService by lazy {
        retrofitCountry.create(MyCountryApiService::class.java)
    }
}