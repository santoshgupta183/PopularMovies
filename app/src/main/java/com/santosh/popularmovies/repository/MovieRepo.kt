package com.santosh.popularmovies.repository

import com.santosh.popularmovies.api.MoviesApi
import com.santosh.popularmovies.model.MoviesResponse
import com.santosh.popularmovies.utils.Constants.Companion.API_KEY
import retrofit2.Response
import javax.inject.Inject

class MovieRepo @Inject constructor(
    private val moviesApi: MoviesApi
) {
    suspend fun fetchMovies(): Response<MoviesResponse>{
        return moviesApi.getMovies(API_KEY)
    }
}
