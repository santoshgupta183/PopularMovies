package com.santosh.popularmovies.ui.fragments.movieList

import android.view.View
import com.santosh.popularmovies.model.Movie

interface OnMovieSelectedListener {
    fun onMovieSelected(view: View, movie: Movie)
}