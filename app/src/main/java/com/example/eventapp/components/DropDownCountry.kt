package com.example.eventapp.ui.screen

import android.util.Log
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.eventapp.TAG
import com.example.eventapp.data.model.countryModel.Country

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DropDownCountry(countries:List<Country>){

    var isExpanded by remember { mutableStateOf(false) }
    var country by remember { mutableStateOf("") }

    var cityIsExpanded by remember { mutableStateOf(false) }
    var city by remember { mutableStateOf("") }
    var cityList:List<String> by remember { mutableStateOf(listOf()) }

    Column() {
        //region DropDown Countries
        ExposedDropdownMenuBox(
            expanded = isExpanded,
            onExpandedChange ={ newValue ->
                isExpanded = newValue
            }
        ) {
            TextField(
                value = country,
                onValueChange = { newValue ->
                    country = newValue
                },
                readOnly = true,
                trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = isExpanded)},
                placeholder = { Text("Please select the country") },
                colors = ExposedDropdownMenuDefaults.textFieldColors(),
                modifier = Modifier.menuAnchor().fillMaxWidth()
            )

            ExposedDropdownMenu(
                expanded = isExpanded,
                onDismissRequest = { isExpanded = false },
                modifier = Modifier.height(300.dp)
            ) {
                countries.forEach{
                    DropdownMenuItem(
                        text = { Text(it.name) },
                        onClick = {
                            country = it.name
                            isExpanded = false
                            cityList = it.cities
                            Log.d(TAG, "${it.cities}")
                        }
                    )
                }

            }
        }

        //endregion

        Spacer(modifier = Modifier.height(20.dp))

        //region DropDown Cities

        ExposedDropdownMenuBox(
            expanded = cityIsExpanded,
            onExpandedChange ={ newValue ->
                cityIsExpanded = newValue
            }
        ) {
            TextField(
                value = city,
                onValueChange = { newValue ->
                    city = newValue
                },
                readOnly = true,
                trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = cityIsExpanded)},
                placeholder = { Text("Please select the city") },
                colors = ExposedDropdownMenuDefaults.textFieldColors(),
                modifier = Modifier.menuAnchor().fillMaxWidth()
            )

            ExposedDropdownMenu(
                expanded = cityIsExpanded,
                onDismissRequest = { cityIsExpanded = false },
                modifier = Modifier.height(if(cityList.isNotEmpty()) 300.dp else 0.dp)
            ) {
                    cityList.forEach{
                        DropdownMenuItem(
                            text = { Text(it) },
                            onClick = {
                                city = it
                                cityIsExpanded = false
                            }
                        )
                }
            }
        }

        //endregion
    }



}