package com.example.eventapp.service

/*
* -----------------------------
* Code created and Edited by:
* Vladymir Adam
* October 2, 2023
* ------------------------------
* */

import com.example.eventapp.data.model.ResponseApi
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query


private const val Base_URL = "https://app.ticketmaster.com/discovery/v2/"

val retrofit = Retrofit.Builder()
    .baseUrl(Base_URL)
    .addConverterFactory(GsonConverterFactory.create())
    .build()
interface MyApiService{
    @GET("events.json")
        suspend  fun getData(
                                @Query("apikey") apikey:String = "7elxdku9GGG5k8j0Xm8KWdANDgecHMV0",
                                @Query("countryCode") countryCode:String = "CA",
                                @Query("city") city:String = "",
                                @Query("size") size:Int ,
                                @Query("classificationName") classificationName:String = ""
                            ):ResponseApi
}

/**
 * A public Api object that exposes the lazy-initialized Retrofit service
 */

object  EventsApi {
    val retrofitService: MyApiService by lazy {
        retrofit.create(MyApiService::class.java)
    }
}
