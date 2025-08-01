package com.example.androidboilerplate.utils

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions

@BindingAdapter("imageUrl")
fun setImageUrl(imageView: ImageView, url: String?) {
    url?.let {
        Glide.with(imageView.context)
            .load(it)
            .transition(DrawableTransitionOptions.withCrossFade())
            .into(imageView)
    }
}

@BindingAdapter("imageUrlWithPlaceholder")
fun setImageUrlWithPlaceholder(imageView: ImageView, url: String?) {
    url?.let {
        Glide.with(imageView.context)
            .load(it)
            .transition(DrawableTransitionOptions.withCrossFade())
            .into(imageView)
    }
}

@BindingAdapter("circleImageUrl")
fun setCircleImageUrl(imageView: ImageView, url: String?) {
    url?.let {
        Glide.with(imageView.context)
            .load(it)
            .circleCrop()
            .transition(DrawableTransitionOptions.withCrossFade())
            .into(imageView)
    }
} 