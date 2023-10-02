package com.example.eventapp.components

/*
* -----------------------------
* Code created and Edited by:
* Vladymir Adam
* October 2, 2023
* ------------------------------
* */

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Slider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable

@Composable
fun TheSliderQty(label:String, sliderPosition:Float, onSliderChange:(value:Float)->Unit){
    Column {
        Text(label)
        Slider(
            value = sliderPosition,
            onValueChange = {onSliderChange(it)},
            valueRange = 1f..200f
        )
        Text(text = sliderPosition.toInt().toString())
    }
}
