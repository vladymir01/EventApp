package com.example.eventapp.components

/*
* -----------------------------
* Code created and Edited by:
* Vladymir Adam
* October 2, 2023
* ------------------------------
* */

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

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DropDownCategory(classification:String,onValueChange:(value:String)->Unit){

    //region The variables
    var isExpanded:Boolean by remember { mutableStateOf(false) }
    val listCategory:List<String> = listOf("Sports", "Music", "Art & Theatre", "Miscellaneous", "All")
    //endregion

    //region DropDownBox that contains the ExposedDropDownMenu for the Categories
    ExposedDropdownMenuBox(
        expanded = isExpanded,
        onExpandedChange ={ newValue ->
            isExpanded = newValue
        },
    ) {
        //region The TextField
        TextField(
            value = classification,
            onValueChange = {onValueChange(it)},
            readOnly = true,
            trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = isExpanded)},
            placeholder = { Text("Please select a category") },
            colors = ExposedDropdownMenuDefaults.textFieldColors(),
            modifier = Modifier
                .menuAnchor()
                .fillMaxWidth()
        )
        //endregion

        //region The ExposedDropDownMenu
        ExposedDropdownMenu(
            expanded = isExpanded,
            onDismissRequest = { isExpanded = false },
            modifier = Modifier.height(250.dp)
        ) {
            listCategory.forEach{
                DropdownMenuItem(
                    text = { Text(it) },
                    onClick = {
                        isExpanded = false
                        onValueChange(it)
                    }
                )
            }

        }
        //endregion
    }

    //endregion
}