package com.santosh.popularmovies.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.santosh.popularmovies.api.Resource
import com.santosh.popularmovies.model.Movie
import com.santosh.popularmovies.repository.MovieRepo
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MovieViewModel @Inject constructor(
    private val movieRepo: MovieRepo
): ViewModel() {
    private val movieList = MutableLiveData<Resource<List<Movie>>>()

    private fun fetchMovies() = viewModelScope.launch {
        movieList.postValue(Resource.Loading())
        val apiResponse = movieRepo.fetchMovies()
        if (apiResponse.isSuccessful) {
            apiResponse.body()?.let { moviesResponse ->
                movieList.postValue(Resource.Success(moviesResponse.movies))
            }
        } else {
            movieList.postValue(Resource.Error(null, apiResponse.message()))
        }
    }

    fun getMovieList(): LiveData<Resource<List<Movie>>> {
        return movieList
    }

    fun refreshList() {
        fetchMovies()
    }
}