package com.example.eventapp.data.model.countryModel

import com.google.gson.annotations.SerializedName

data class Country(
    val cities: List<String>,
    @SerializedName("country")
    val name: String,
    val iso2: String,
    val iso3: String
)