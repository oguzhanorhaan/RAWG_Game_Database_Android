package com.oguzhanorhan.rawggamedatabaseandroid.scenes.gamelist.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.databinding.DataBindingUtil
import androidx.viewpager.widget.PagerAdapter
import com.oguzhanorhan.rawggamedatabaseandroid.databinding.ItemListViewBinding
import com.oguzhanorhan.rawggamedatabaseandroid.datasource.remote.model.Game

internal class ImageSliderAdapter(
    var context: Context?,
    var games: List<Game?>,
    val onClickListener: OnClickListener
) : PagerAdapter() {

    var mLayoutInflater: LayoutInflater = context?.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater

    override fun getCount(): Int {
        return games?.size ?: 0
    }

    override fun isViewFromObject(view: View, `object`: Any): Boolean {
        return view === `object` as ConstraintLayout
    }

    override fun instantiateItem(container: ViewGroup, position: Int): Any {
        val binding: ItemListViewBinding =
            DataBindingUtil.inflate(mLayoutInflater, com.oguzhanorhan.rawggamedatabaseandroid.R.layout.item_list_view, container, false)
        binding.imageView.setOnClickListener {
            games[position]?.let { game -> onClickListener.onClick(game) }
        }
        binding.item = games[position]
        container.addView(binding.root)
        return binding.root
    }

    override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
        container.removeView(`object` as ConstraintLayout)
    }

    class OnClickListener(val clickListener: (item: Game) -> Unit) {
        fun onClick(item: Game) = clickListener(item)
    }
}
