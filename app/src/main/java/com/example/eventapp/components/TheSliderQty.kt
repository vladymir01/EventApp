package com.example.eventapp.components

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Slider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue

@Composable
fun TheSliderQty(label:String){
    var sliderPosition by remember { mutableFloatStateOf(0f) }
    Column {
        Text(label)
        Slider(
            value = sliderPosition,
            onValueChange = {sliderPosition = it},
            valueRange = 1f..200f
        )
        Text(text = sliderPosition.toInt().toString())
    }
}
