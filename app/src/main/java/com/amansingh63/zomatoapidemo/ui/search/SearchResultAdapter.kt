package com.amansingh63.zomatoapidemo.ui.search

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.amansingh63.zomatoapidemo.databinding.ItemListHeaderBinding
import com.amansingh63.zomatoapidemo.databinding.ItemRestrauntBinding
import com.amansingh63.zomatoapidemo.models.ListHeader
import com.amansingh63.zomatoapidemo.models.search.Restaurants

const val ITEM_VIEW_TYPE_HEADER = 0
const val ITEM_VIEW_TYPE_SEARCH_RESULT = 1


class SearchResultAdapter(private val viewModel: SearchViewModel) :
    ListAdapter<Any, RecyclerView.ViewHolder>(SearchDiffCallback()) {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        if (viewType == ITEM_VIEW_TYPE_HEADER) {
            return ListHeaderViewHolder.from(parent)
        } else if (viewType == ITEM_VIEW_TYPE_SEARCH_RESULT) {
            return SearchViewHolder.from(parent)
        } else {
            throw  IllegalArgumentException("viewType=$viewType not handled ")
        }

    }

    override fun onBindViewHolder(viewHolder: RecyclerView.ViewHolder, position: Int) {
        val item = getItem(position)
        if (viewHolder is ListHeaderViewHolder) {
            viewHolder.bind(viewModel, item as ListHeader)
        } else if (viewHolder is SearchViewHolder) {
            viewHolder.bind(viewModel, item as Restaurants)
        } else {
            throw  IllegalArgumentException("viewHolder=${viewHolder.javaClass.simpleName} not handled ")
        }
    }

    override fun getItemViewType(position: Int): Int {
        val item = getItem(position)
        return if (item is ListHeader) {
            ITEM_VIEW_TYPE_HEADER
        } else if (item is Restaurants) {
            ITEM_VIEW_TYPE_SEARCH_RESULT
        } else {
            throw  IllegalArgumentException("item=${item.javaClass.simpleName} not handled ")
        }
    }

    class ListHeaderViewHolder private constructor(val binding: ItemListHeaderBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(viewModel: SearchViewModel, item: ListHeader) {

            binding.viewmodel = viewModel
            binding.listheader = item
            binding.executePendingBindings()
        }

        companion object {
            fun from(parent: ViewGroup): ListHeaderViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = ItemListHeaderBinding.inflate(layoutInflater, parent, false)
                return ListHeaderViewHolder(binding)
            }
        }
    }


    class SearchViewHolder private constructor(val binding: ItemRestrauntBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(viewModel: SearchViewModel, item: Restaurants) {

            binding.viewmodel = viewModel
            binding.restaurants = item
            binding.executePendingBindings()
        }

        companion object {
            fun from(parent: ViewGroup): SearchViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = ItemRestrauntBinding.inflate(layoutInflater, parent, false)
                return SearchViewHolder(binding)
            }
        }
    }
}


/**
 * Callback for calculating the diff between two non-null items in a list.
 *
 * Used by ListAdapter to calculate the minimum number of changes between and old list and a new
 * list that's been passed to `submitList`.
 */
class SearchDiffCallback : DiffUtil.ItemCallback<Any>() {

    override fun areItemsTheSame(oldItem: Any, newItem: Any): Boolean {
        if (oldItem is ListHeader && newItem is ListHeader) {
            return oldItem.title == newItem.title
        } else if (oldItem is Restaurants && newItem is Restaurants) {
            return oldItem.restaurant.id == newItem.restaurant.id
        }
        return false
    }

    @SuppressLint("DiffUtilEquals")
    override fun areContentsTheSame(oldItem: Any, newItem: Any): Boolean {
        return oldItem == newItem
    }
}