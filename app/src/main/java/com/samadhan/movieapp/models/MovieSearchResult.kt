package com.samadhan.movieapp.models

/**
 * Data class for List of Movie
 */
data class MovieSearchResult(
    val Search: List<Movie>,
    val totalResults: Int,
    val Response: Boolean
)
