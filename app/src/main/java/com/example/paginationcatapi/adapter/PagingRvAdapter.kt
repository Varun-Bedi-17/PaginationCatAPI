package com.example.paginationcatapi.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.paginationcatapi.R
import com.example.paginationcatapi.databinding.RvListItemBinding
import com.example.paginationcatapi.models.api.ApiDataModelItem

class PagingRvAdapter : PagingDataAdapter<ApiDataModelItem, PagingRvAdapter.Adapter>(diffCallback = diffUtil) {
    inner class Adapter(val binding : RvListItemBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Adapter {
        val binding =
            RvListItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return Adapter(binding)
    }

    override fun onBindViewHolder(holder: Adapter, position: Int) {
        val data = getItem(position)
        holder.binding.imaeView.apply {
            val layoutParams = data?.width?.let { ViewGroup.LayoutParams(it, data.height) }
            this.layoutParams = layoutParams
            Glide
                .with(holder.itemView.context)
                .load(data?.url)
                .placeholder(R.drawable.ic_launcher_foreground)
                .into(this)
        }
    }

    companion object {

        private val diffUtil = object : DiffUtil.ItemCallback<ApiDataModelItem>() {
            override fun areItemsTheSame(
                oldItem: ApiDataModelItem,
                newItem: ApiDataModelItem,
            ): Boolean = oldItem.id == newItem.id


            override fun areContentsTheSame(
                oldItem: ApiDataModelItem,
                newItem: ApiDataModelItem,
            ): Boolean {
                return oldItem == newItem
            }

        }
    }
}