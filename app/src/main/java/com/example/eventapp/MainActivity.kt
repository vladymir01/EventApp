package com.example.eventapp

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.eventapp.data.repository.EventRepository
import com.example.eventapp.ui.screen.DropDownCountry
import com.example.eventapp.ui.screen.GenderDropDownMenu
import com.example.eventapp.ui.screen.ListEventScreen
import com.example.eventapp.ui.theme.EventAppTheme
import com.example.eventapp.ui.viewModel.CountryViewModel
import com.example.eventapp.ui.viewModel.EventViewModel

const val TAG = "MainActivity"
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            EventAppTheme {
                // A surface container using the 'background' color from the theme
                Surface(
//                    modifier = Modifier.fillMaxSize(),
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
//    GenderDropDownMenu()
//    val eventViewModel:EventViewModel = viewModel()
//    ListEventScreen(eventViewModel.events)
    val countryViewModel:CountryViewModel = viewModel()
    DropDownCountry(countryViewModel.countries)
}

