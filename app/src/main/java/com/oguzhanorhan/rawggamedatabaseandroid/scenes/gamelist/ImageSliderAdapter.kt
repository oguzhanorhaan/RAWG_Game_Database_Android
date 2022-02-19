package com.oguzhanorhan.rawggamedatabaseandroid.scenes.gamelist

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import androidx.viewpager.widget.PagerAdapter
import com.bumptech.glide.Glide
import com.oguzhanorhan.rawggamedatabaseandroid.R
import com.oguzhanorhan.rawggamedatabaseandroid.datasource.model.Game
import java.util.*

internal class ImageSliderAdapter(
    var context: Context?,
    var images: List<Game?>
) : PagerAdapter() {

    var mLayoutInflater: LayoutInflater = context?.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater

    override fun getCount(): Int {
        return images?.size ?: 0
    }

    override fun isViewFromObject(view: View, `object`: Any): Boolean {
        return view === `object` as LinearLayout
    }

    override fun instantiateItem(container: ViewGroup, position: Int): Any {
        val itemView: View = mLayoutInflater.inflate(R.layout.item_image_slider, container, false)
        val imageView = itemView.findViewById<View>(R.id.sliderImage) as ImageView
        context?.let { Glide.with(it).load(images?.get(position)?.background_image).centerCrop().into(imageView) }
        Objects.requireNonNull(container).addView(itemView)
        return itemView
    }

    override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
        container.removeView(`object` as LinearLayout)
    }
}
