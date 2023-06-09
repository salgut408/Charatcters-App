package com.sample.ui.main.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.sample.databinding.ItemPreviewBinding
import com.sample.domain.domain_models.RelatedTopicModel
import com.sample.ui.main.MainViewModel

class ItemAdapter(
): RecyclerView.Adapter<ItemAdapter.ItemViewHolder>() {

    inner class ItemViewHolder(val binding: ItemPreviewBinding): RecyclerView.ViewHolder(binding.root) {
        fun bind(relatedTopicModel: RelatedTopicModel){
            binding.relatedTopicModel = relatedTopicModel
            binding.executePendingBindings()
        }
    }

    private val differCallBack = object : DiffUtil.ItemCallback<RelatedTopicModel>() {
        override fun areItemsTheSame(
            oldItem: RelatedTopicModel,
            newItem: RelatedTopicModel
        ): Boolean {
            return oldItem.name == newItem.name
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
        holder.bind(item)
        holder.binding.apply {
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




