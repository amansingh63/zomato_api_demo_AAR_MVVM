package com.amansingh63.zomatoapidemo.ui.search

import androidx.appcompat.widget.AppCompatImageView
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import coil.load
import coil.transform.CircleCropTransformation
import com.amansingh63.zomatoapidemo.R
import com.amansingh63.zomatoapidemo.models.search.Restaurants

/**
 * [BindingAdapter]s for the [Restaurants]s list.
 */
@BindingAdapter("app:searchItems")
fun setItems(listView: RecyclerView, items: List<Any>) {
    (listView.adapter as SearchResultAdapter).submitList(items)
}

@BindingAdapter("app:restaurantsImage")
fun setRestaurantImage(imageView: AppCompatImageView, featured_image: String) {
    imageView.load(featured_image) {
        placeholder(R.mipmap.ic_launcher)
        error(R.mipmap.ic_launcher)
        transformations(CircleCropTransformation())
    }
}