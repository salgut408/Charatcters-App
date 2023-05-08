package com.sample.simpsonsviewer.ui.main

import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.sample.simpsonsviewer.domain.domain_models.RelatedTopicModel
import com.sample.simpsonsviewer.ui.main.adapters.ListAdapter

@BindingAdapter("app:items")
fun setItems(listView: RecyclerView, items: List<RelatedTopicModel>?) {
    items?.let {
        (listView.adapter as ListAdapter).differ.submitList(items)
    }
}