package com.example.eventapp.data.repository

import android.util.Log
//import androidx.compose.ui.tooling.data.EmptyGroup.data
import com.example.eventapp.TAG
import com.example.eventapp.data.model.Event
import com.example.eventapp.data.model.ResponseApi
import com.example.eventapp.service.EventsApi

class EventRepository() {
    suspend fun getEvents(): List<Event> {
        return EventsApi.retrofitService.getData()._embedded.events
//        return EventsApi.retrofitService.getData("7elxdku9GGG5k8j0Xm8KWdANDgecHMV0")._embedded.events
    }
}