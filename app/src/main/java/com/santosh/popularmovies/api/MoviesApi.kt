package com.santosh.popularmovies.api

import com.santosh.popularmovies.model.MoviesResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface MoviesApi {
    @GET("discover/movie?sort_by=popularity.desc")
    suspend fun getMovies(
        @Query("api_key") api_key: String
    ): Response<MoviesResponse>
}