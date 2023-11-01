package com.example.basicsample.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.basicsample.R
import com.example.basicsample.databinding.RvListItemBinding
import com.example.basicsample.models.api.ApiDataModelItem

class RvAdapter : RecyclerView.Adapter<RvAdapter.Adapter>() {
    inner class Adapter(val binding : RvListItemBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Adapter {
        val binding =
            RvListItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return Adapter(binding)
    }

    override fun onBindViewHolder(holder: Adapter, position: Int) {
        val data = asyncListDiffer.currentList[position]
        holder.binding.imaeView.apply {
            val layoutParams = ViewGroup.LayoutParams(data.width, data.height)
            this.layoutParams = layoutParams
            Glide
                .with(holder.itemView.context)
                .load(data.url)
                .placeholder(R.drawable.ic_launcher_foreground)
                .into(this)
        }
    }

    override fun getItemCount(): Int = asyncListDiffer.currentList.size

    private val diffUtil = object : DiffUtil.ItemCallback<ApiDataModelItem>(){
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

    private val asyncListDiffer = AsyncListDiffer(this, diffUtil)



    fun saveData( dataResponse: List<ApiDataModelItem>){
        asyncListDiffer.submitList(dataResponse)
    }
}