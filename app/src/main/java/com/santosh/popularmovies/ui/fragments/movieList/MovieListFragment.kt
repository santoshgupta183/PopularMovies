package com.santosh.popularmovies.ui.fragments.movieList

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.santosh.popularmovies.api.Resource
import com.santosh.popularmovies.databinding.FragmentMovieListBinding
import com.santosh.popularmovies.model.Movie
import com.santosh.popularmovies.viewModel.MovieViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MovieListFragment: Fragment() {

    private lateinit var binding: FragmentMovieListBinding
    private val movieViewModel: MovieViewModel by viewModels()
    private val movieListAdapter = MovieListAdapter()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentMovieListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        movieListAdapter.onMovieSelectedListener = object: OnMovieSelectedListener {
            override fun onMovieSelected(view: View, movie: Movie) {
                val action = MovieListFragmentDirections.actionMovieListFragmentToMovieDetailsFragment(movie)
                view.findNavController().navigate(action)
            }

        }
        binding.movieListRv.apply {
            layoutManager = LinearLayoutManager(this@MovieListFragment.context)
            adapter = movieListAdapter
        }

        binding.swipeRefreshList.setOnRefreshListener {
            movieViewModel.refreshList()
        }

        movieViewModel.getMovieList().observe(viewLifecycleOwner) { response ->
            when(response) {
                is Resource.Loading->{
                    showLoadingBar(true)
                }
                is Resource.Error->{
                    showLoadingBar(false)
                    Toast.makeText(this@MovieListFragment.context, response.errorMessage, Toast.LENGTH_SHORT).show()
                }
                is Resource.Success-> {
                    showLoadingBar(false)
                    response.data?.let {
                        movieListAdapter.setMovieList(it)
                    }
                }
            }
        }

        movieViewModel.refreshList()
    }

    private fun showLoadingBar(show: Boolean) {
        binding.swipeRefreshList.isRefreshing = show
    }
}