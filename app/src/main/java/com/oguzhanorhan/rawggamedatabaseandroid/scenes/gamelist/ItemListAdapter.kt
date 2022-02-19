package com.oguzhanorhan.rawggamedatabaseandroid.scenes.gamelist

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.oguzhanorhan.rawggamedatabaseandroid.databinding.ItemListViewBinding
import com.oguzhanorhan.rawggamedatabaseandroid.domain.model.GameItem

class ItemListAdapter(val onClickListener: OnClickListener) :
    ListAdapter<GameItem, ItemListAdapter.ListItemViewHolder>(DiffCallback) {

    class ListItemViewHolder(private var binding: ItemListViewBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: GameItem) {
            binding.item = item
            binding.executePendingBindings()
        }
    }

    companion object DiffCallback : DiffUtil.ItemCallback<GameItem>() {
        override fun areItemsTheSame(oldItem: GameItem, newItem: GameItem): Boolean {
            return oldItem === newItem
        }

        override fun areContentsTheSame(oldItem: GameItem, newItem: GameItem): Boolean {
            return oldItem == newItem
        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ListItemViewHolder {
        return ListItemViewHolder(ItemListViewBinding.inflate(LayoutInflater.from(parent.context)))
    }

    override fun onBindViewHolder(holder: ListItemViewHolder, position: Int) {
        val item = getItem(position)
        holder.itemView.setOnClickListener {
            onClickListener.onClick(item)
        }
        holder.bind(item)
    }

    class OnClickListener(val clickListener: (item: GameItem) -> Unit) {
        fun onClick(item: GameItem) = clickListener(item)
    }
}