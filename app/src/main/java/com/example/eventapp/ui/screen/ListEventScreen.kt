package com.example.eventapp.ui.screen

/*
* -----------------------------
* Code created and Edited by:
* Vladymir Adam
* October 2, 2023
* ------------------------------
* */

import android.annotation.SuppressLint
import android.util.Log
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
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
import com.example.eventapp.ui.viewModel.EventViewModel
import kotlinx.coroutines.launch

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ListEventScreen(events:List<Event>, eventViewModel: EventViewModel){

    //region Variables
        val sheetState = rememberModalBottomSheetState(skipPartiallyExpanded = true)
        val scope = rememberCoroutineScope()
        var showBottomSheet by remember { mutableStateOf(false) }
        val size by eventViewModel.size.observeAsState()
        val category by eventViewModel.category.observeAsState()
        var sliderPosition by remember { mutableFloatStateOf(0f) }
        var classification by remember { mutableStateOf("") }
    //endregion

    //region SideEffects
    LaunchedEffect(size,category){
        size?.let { category?.let { it1 ->
                                    if (it1 != "All") eventViewModel.getTheEvents(it, it1)
                                    //if the user choose "All", we will not pass the classificationName
                                    else eventViewModel.getTheEvents(it)
                                }
                }
    }
    //endregion

    //region The Composable
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
                        TheSliderQty(
                            "Choose the quantity you want for the results",
                            sliderPosition,
                            onSliderChange = {theValue -> sliderPosition = theValue}
                        )
                        Spacer(Modifier.height(20.dp))
                        DropDownCategory(classification, onValueChange = {theValue -> classification = theValue})
                        Button(onClick = {
                            scope.launch { sheetState.hide() }.invokeOnCompletion {
                                Log.d(TAG,"The selected quantity is: ${sliderPosition.toInt()}")
                                eventViewModel.setSize(sliderPosition.toInt())
                                eventViewModel.setCategory(classification)
                                Log.d(TAG, "The slider: $sliderPosition")
                                Log.d(TAG, "The Category: $classification")
                                if (!sheetState.isVisible){ showBottomSheet = false} }
                        }) { Text("Submit") }
                    }

                }
        }
        //endregion
    }

    //endregion
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
        Text(event._embedded.venues[0].city.name)
        Spacer(modifier = Modifier.height(6.dp))
    }

}