package com.example.eventapp.data.model

data class PriceRange(
    val currency: String,
    val max: Double,
    val min: Double,
    val type: String
)