package com.oguzhanorhan.rawggamedatabaseandroid.scenes.gamelist.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.oguzhanorhan.rawggamedatabaseandroid.databinding.ItemListViewBinding
import com.oguzhanorhan.rawggamedatabaseandroid.datasource.remote.model.Game

class ItemListAdapter(val onClickListener: OnClickListener) :
    ListAdapter<Game, ItemListAdapter.ListItemViewHolder>(DiffCallback) {

    class ListItemViewHolder(private var binding: ItemListViewBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: Game) {
            binding.item = item
            binding.executePendingBindings()
        }
    }

    companion object DiffCallback : DiffUtil.ItemCallback<Game>() {
        override fun areItemsTheSame(oldItem: Game, newItem: Game): Boolean {
            return oldItem === newItem
        }

        override fun areContentsTheSame(oldItem: Game, newItem: Game): Boolean {
            return oldItem.id == newItem.id
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

    class OnClickListener(val clickListener: (item: Game) -> Unit) {
        fun onClick(item: Game) = clickListener(item)
    }
}
