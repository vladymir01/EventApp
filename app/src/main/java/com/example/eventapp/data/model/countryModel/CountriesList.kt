package com.example.eventapp.data.model.countryModel

data class Countries(
    val `data`: List<Country>,
    val error: Boolean,
    val msg: String
)