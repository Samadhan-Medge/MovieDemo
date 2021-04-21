package com.samadhan.movieapp.service

import com.samadhan.movieapp.models.Movie
import com.samadhan.movieapp.models.MovieSearchResult
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.QueryMap

/**
 * Service callback for get Search Result
 */
interface SearchMovieListService {
    @GET(".")
    fun getSearchResult(@QueryMap options: Map<String, String>): Call<MovieSearchResult?>?
}

/**
 * Service callback for get Movie Details
 */
interface GetMovieDetailsService {
    @GET(".")
    fun getMovieDetails(@QueryMap options: Map<String, String>): Call<Movie?>?
}