package com.santosh.popularmovies.dataBinding

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.santosh.popularmovies.R
import com.santosh.popularmovies.utils.Constants.Companion.IMAGE_BASE_URL

@BindingAdapter("imageUrl")
fun loadImage(imageView: ImageView, url: String) {
    Glide.with(imageView.context)
        .load(IMAGE_BASE_URL+ url)
        .placeholder(R.drawable.ic_no_image)
        .into(imageView)
}