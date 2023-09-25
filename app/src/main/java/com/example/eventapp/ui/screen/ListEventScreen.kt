package com.example.eventapp.ui.screen

import android.annotation.SuppressLint
import android.util.Log
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.BottomAppBarDefaults
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CardElevation
import androidx.compose.material3.Divider
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.ExtendedFloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Slider
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.example.eventapp.TAG
import com.example.eventapp.components.DropDownCategory
import com.example.eventapp.components.TheSliderQty
import com.example.eventapp.data.model.Event
import com.example.eventapp.data.model.ResponseApi
import com.example.eventapp.data.model.countryModel.Country
import kotlinx.coroutines.launch

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ListEventScreen(events:List<Event>, countries:List<Country>){

    val sheetState = rememberModalBottomSheetState(skipPartiallyExpanded = true)
    val scope = rememberCoroutineScope()
    var showBottomSheet by remember { mutableStateOf(false) }

    Scaffold(
        topBar = { FilterAndSort(onClick = {showBottomSheet = true}) }


    ) {
        //region Page Content
        Column(
            modifier = Modifier.padding(start = 20.dp, top = 60.dp, bottom = 20.dp, end = 20.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(20.dp)
        ) {
            Divider(thickness = 4.dp)
            LazyColumn(
                verticalArrangement = Arrangement.spacedBy(20.dp),
            ){
                items(events){event ->
                    EventCard(event)
                }
            }
        }

        //endregion

        //region BottomSheet
        if (showBottomSheet){
            ModalBottomSheet(
                onDismissRequest = { showBottomSheet = false},
                sheetState = sheetState,
                modifier = Modifier.height(700.dp)
            ) {

                    Column(
                        modifier = Modifier.padding(30.dp)
                    ) {
                        DropDownCountry(countries)
                        Spacer(Modifier.height(20.dp))
                        DropDownCategory()
                        Spacer(Modifier.height(20.dp))
                        TheSliderQty("Choose the quantity you want for the results")
                        Spacer(Modifier.height(20.dp))
                        Button(onClick = {
                            scope.launch { sheetState.hide() }.invokeOnCompletion {
                                if (!sheetState.isVisible){ showBottomSheet = false} }
                        }) { Text("Submit") }
                    }

                }


        }
        //endregion

    }
}
@Composable
fun FilterAndSort(onClick:()->Unit) {
    Row(
        horizontalArrangement = Arrangement.SpaceBetween,
        modifier = Modifier
            .fillMaxWidth()
            .padding(20.dp)
    ) {
        Text("Filter", modifier = Modifier.clickable { onClick() })
        Text("Sort")
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