package com.example.eventapp.data.model

data class Attraction(
    val _links: Links,
    val aliases: List<String>,
    val classifications: List<Classification>,
    val externalLinks: ExternalLinks,
    val id: String,
//    val images: List<Image>,
    val images: List<ImageXX>,
    val locale: String,
    val name: String,
    val test: Boolean,
    val type: String,
    val upcomingEvents: UpcomingEvents,
    val url: String
)