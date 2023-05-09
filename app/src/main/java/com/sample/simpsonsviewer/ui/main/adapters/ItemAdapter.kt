package com.sample.simpsonsviewer.ui.main.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.sample.simpsonsviewer.databinding.ItemPreviewBinding
import com.sample.simpsonsviewer.domain.domain_models.RelatedTopicModel

class ItemAdapter(): RecyclerView.Adapter< ItemAdapter.ItemViewHolder>() {

    inner class ItemViewHolder(val binding: ItemPreviewBinding): RecyclerView.ViewHolder(binding.root) {}

    private val differCallBack = object : DiffUtil.ItemCallback<RelatedTopicModel>() {
        override fun areItemsTheSame(
            oldItem: RelatedTopicModel,
            newItem: RelatedTopicModel
        ): Boolean {
            return oldItem.text == newItem.text
        }

        override fun areContentsTheSame(
            oldItem: RelatedTopicModel,
            newItem: RelatedTopicModel,
        ): Boolean {
            return oldItem == newItem
        }

    }
    val differ = AsyncListDiffer(this, differCallBack)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        return ItemViewHolder(
            ItemPreviewBinding.inflate(
                LayoutInflater.from(parent.context),
                parent, false
            )
        )
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        val item = differ.currentList[position]
        holder.binding.apply {
            itemName.text = item.name
            itemName.setOnClickListener {
                onItemClickListener?.let {
                    it(item)
                }
            }
        }
    }

    private var onItemClickListener:((RelatedTopicModel)-> Unit)? = null

    fun setOnItemClickListener(listener: (RelatedTopicModel) -> Unit) {
        onItemClickListener = listener
    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }
}




