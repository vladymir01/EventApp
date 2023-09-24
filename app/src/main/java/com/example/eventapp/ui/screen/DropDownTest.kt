package com.example.eventapp.ui.screen

import androidx.compose.foundation.background
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
import androidx.compose.ui.graphics.Color

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun GenderDropDownMenu(){
    var isExpanded by remember { mutableStateOf(false) }
    var gender by remember { mutableStateOf("") }

    ExposedDropdownMenuBox(
        expanded = isExpanded,
        onExpandedChange ={ newValue ->
            isExpanded = newValue
        }
    ) {
       TextField(
           value = gender,
           onValueChange = {},
           readOnly = true,
           trailingIcon = {ExposedDropdownMenuDefaults.TrailingIcon(expanded = isExpanded)},
           placeholder = {Text("Please select your gender")},
           colors = ExposedDropdownMenuDefaults.textFieldColors(),
           modifier = Modifier.menuAnchor()
       )

        ExposedDropdownMenu(
            expanded = isExpanded,
            onDismissRequest = { isExpanded = false }
        ) {

            DropdownMenuItem(
                text = { Text("Male")},
                onClick = {
                    gender = "Male"
                    isExpanded = false
                }
            )

            DropdownMenuItem(
                text = { Text("Female")},
                onClick = {
                    gender = "Female"
                    isExpanded = false
                }
            )

            DropdownMenuItem(
                text = { Text("Other")},
                onClick = {
                    gender = "Other"
                    isExpanded = false
                }
            )
        }
    }
}