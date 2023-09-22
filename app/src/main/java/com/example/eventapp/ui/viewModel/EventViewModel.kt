package com.example.eventapp.ui.viewModel

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.eventapp.TAG
import com.example.eventapp.data.model.Embedded
import com.example.eventapp.data.model.Event
import com.example.eventapp.data.model.First
import com.example.eventapp.data.model.Last
import com.example.eventapp.data.model.Links
import com.example.eventapp.data.model.LinksXXX
import com.example.eventapp.data.model.Next
import com.example.eventapp.data.model.Page
import com.example.eventapp.data.model.ResponseApi
import com.example.eventapp.data.model.Self
import com.example.eventapp.data.repository.EventRepository
import kotlinx.coroutines.launch
import java.io.IOException

class EventViewModel:ViewModel() {

    private val eventRepository = EventRepository()
    var events:List<Event> by mutableStateOf(listOf())

    init {
        getTheEvents()
    }

    private  fun getTheEvents(){
        viewModelScope.launch {
            try {
                events = eventRepository.getEvents()
                Log.d(TAG, "The number of Events: ${events.size}")

                //region Determine the categories
                    val categoryCounts = mutableMapOf<String, Int>() // that is a list of category
                    events.forEach {
                        val category = it.classifications[0].segment.name
                        categoryCounts[category] = (categoryCounts[category] ?: 0) + 1
                    }

                    for ((category, count) in categoryCounts){
                        Log.d(TAG, "$category: $count")
                    }
                //endregion

            }catch (e:Exception){
                Log.d(TAG, "Something went wrong")
            }
        }
    }
}