package com.example.eventapp.data.repository

/*
* -----------------------------
* Code created and Edited by:
* Vladymir Adam
* October 2, 2023
* ------------------------------
* */

import com.example.eventapp.data.model.Event
import com.example.eventapp.service.EventsApi

class EventRepository() {
    suspend fun getEvents(city:String="",size:Int = 200, classificationName:String = ""): List<Event> {
        return EventsApi
            .retrofitService
            .getData( city=city,size = size, classificationName = classificationName)
            ._embedded.events
    }
}