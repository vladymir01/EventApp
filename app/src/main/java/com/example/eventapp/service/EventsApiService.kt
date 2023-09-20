package com.example.eventapp.service

import com.example.eventapp.data.model.Event
import com.example.eventapp.data.model.ResponseApi
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query


//private const val Base_URL = "https://app.ticketmaster.com/discovery/v2/events.json?apikey=7elxdku9GGG5k8j0Xm8KWdANDgecHMV0"
private const val Base_URL = "https://app.ticketmaster.com/discovery/v2/"
//private const val API_KEY = "7elxdku9GGG5k8j0Xm8KWdANDgecHMV0"

val retrofit = Retrofit.Builder()
    .baseUrl(Base_URL)
    .addConverterFactory(GsonConverterFactory.create())
    .build()

interface MyApiService{
    @GET("events?countryCode=CA&size=200&city=Ottawa&apikey=7elxdku9GGG5k8j0Xm8KWdANDgecHMV0")
//    @GET("events.json")
        suspend  fun getData():ResponseApi
//        suspend  fun getData(@Query("apikey") apikey:String):ResponseApi
}

/**
 * A public Api object that exposes the lazy-initialized Retrofit service
 */

object  EventsApi {
    val retrofitService: MyApiService by lazy {
        retrofit.create(MyApiService::class.java)
    }
}
