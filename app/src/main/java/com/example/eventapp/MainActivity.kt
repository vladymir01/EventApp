package com.example.eventapp

/*
* -----------------------------
* Code created and Edited by:
* Vladymir Adam
* October 2, 2023
* ------------------------------
* */

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.eventapp.ui.screen.ListEventScreen
import com.example.eventapp.ui.theme.EventAppTheme
import com.example.eventapp.ui.viewModel.EventViewModel

const val TAG = "MainActivity"
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            EventAppTheme {
                Surface(
                    color = MaterialTheme.colorScheme.background
                ) {
                    MainApp()
                }
            }
        }
    }
}

@Composable
fun MainApp(){
    val eventViewModel:EventViewModel = viewModel()
    ListEventScreen(eventViewModel.events,  eventViewModel)
}

