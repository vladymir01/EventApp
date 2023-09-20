package com.example.eventapp.data.model

data class ExternalLinks(
    val facebook: List<Facebook>,
    val homepage: List<Homepage>,
    val instagram: List<Instagram>,
    val itunes: List<Itune>,
    val lastfm: List<Lastfm>,
    val musicbrainz: List<Musicbrainz>,
    val spotify: List<Spotify>,
    val twitter: List<Twitter>,
    val wiki: List<Wiki>,
    val youtube: List<Youtube>
)