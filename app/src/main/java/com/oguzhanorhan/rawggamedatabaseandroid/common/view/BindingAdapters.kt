package com.oguzhanorhan.rawggamedatabaseandroid.common

import android.view.View
import android.widget.ImageView
import androidx.core.net.toUri
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.oguzhanorhan.rawggamedatabaseandroid.R
import com.oguzhanorhan.rawggamedatabaseandroid.data.model.RawgApiStatus
import com.oguzhanorhan.rawggamedatabaseandroid.datasource.remote.model.Game
import com.oguzhanorhan.rawggamedatabaseandroid.scenes.gamelist.ItemListAdapter

@BindingAdapter("listData")
fun bindGameItemsRecyclerView(recyclerView: RecyclerView, data: List<Game>?) {
    val adapter = recyclerView.adapter as ItemListAdapter
    adapter.submitList(data)
}

@BindingAdapter("imageUrl")
fun ImageView.bindImage(imgUrl: String?) {
    imgUrl?.let {
        val imgUri = imgUrl.toUri().buildUpon().scheme("https").build()
        Glide.with(this.context)
            .load(imgUri)
            .centerCrop()
            .apply(
                RequestOptions()
                    .placeholder(R.drawable.loading_animation)
                    .error(R.drawable.ic_broken_image)
            )
            .into(this)
    }
}

@BindingAdapter("apiStatus")
fun bindStatus(statusImageView: ImageView, status: RawgApiStatus?) {
    when (status) {
        RawgApiStatus.LOADING -> {
            statusImageView.visibility = View.VISIBLE
            statusImageView.setImageResource(R.drawable.loading_animation)
        }
        RawgApiStatus.ERROR -> {
            statusImageView.visibility = View.VISIBLE
            statusImageView.setImageResource(R.drawable.ic_connection_error)
        }
        RawgApiStatus.DONE -> {
            statusImageView.visibility = View.GONE
        }
    }
}
