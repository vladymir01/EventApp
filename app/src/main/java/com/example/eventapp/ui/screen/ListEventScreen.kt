package com.example.eventapp.ui.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CardElevation
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.example.eventapp.data.model.Event
import com.example.eventapp.data.model.ResponseApi

@Composable
fun ListEventScreen(events:List<Event>){
    LazyColumn(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(20.dp),
        modifier = Modifier
            .padding(20.dp)
        ){
        items(events){event ->
            EventCard(event)
        }
    }
}

@Composable
fun EventCard(event: Event){

    Surface(
        shape = RoundedCornerShape(20.dp),
        modifier = Modifier.fillMaxWidth(),
        color = Color.Cyan,
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .fillMaxWidth()
        ) {
                AsyncImage(
                    model = event.images[0].url,
                    contentDescription = null,
                    modifier = Modifier.fillMaxWidth(),
                    contentScale = ContentScale.FillWidth,
                )

            CardTextContent(event)
        }
    }
}

@Composable
fun CardTextContent(event: Event){
    Column() {
        Spacer(modifier = Modifier.height(6.dp))
        Text(event.name)
        Text(event.dates.start.localDate)
        Text(event.dates.start.localTime)
        Text(event.classifications[0].segment.name)
        Text(event._embedded.venues[0].name)
        Text(event._embedded.venues[0].address.line1)
        Spacer(modifier = Modifier.height(6.dp))
    }

}