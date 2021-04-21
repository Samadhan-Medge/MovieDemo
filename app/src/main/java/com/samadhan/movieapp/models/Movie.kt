package com.samadhan.movieapp.models

/**
 * Data class for Movie item
 */
data class Movie(
    val Title: String,
    val Year: String,
    val Rated: String,
    val Released: String,
    val Runtime: String,
    val Genre: String,
    val director: String,
    val writer: String,
    val actors: String,
    val Plot: String,
    val language: String,
    val country: String,
    val awards: String,
    val Poster: String,
    val metascore: Int,
    val imdbRating: Double,
    val imdbVotes: String,
    val imdbID: String,
    val type: String,
    val dVD: String,
    val boxOffice: String,
    val production: String,
    val website: String,
    val response: Boolean
)