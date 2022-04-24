package com.santosh.popularmovies.ui.fragments.movieList

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.santosh.popularmovies.databinding.MovieListItemBinding
import com.santosh.popularmovies.model.Movie

class MovieListAdapter: RecyclerView.Adapter<MovieListAdapter.MovieListVH>() {

    lateinit var onMovieSelectedListener: OnMovieSelectedListener

    inner class MovieListVH(val itemBinding: MovieListItemBinding): RecyclerView.ViewHolder(itemBinding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieListVH {
        val binding = MovieListItemBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )

        return MovieListVH(binding)
    }

    override fun onBindViewHolder(holder: MovieListVH, position: Int) {
        holder.itemBinding.movie = differ.currentList[position]
        holder.itemBinding.listener = onMovieSelectedListener
    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }

    private val diffCallBack = object: DiffUtil.ItemCallback<Movie>(){
        override fun areItemsTheSame(oldItem: Movie, newItem: Movie): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Movie, newItem: Movie): Boolean {
            return oldItem == newItem
        }
    }

    private val differ = AsyncListDiffer(this, diffCallBack)

    fun setMovieList(movieList: List<Movie>) {
        val currentList = differ.currentList.toMutableList()
        currentList.clear()
        currentList.addAll(movieList)
        differ.submitList(currentList)
    }

}