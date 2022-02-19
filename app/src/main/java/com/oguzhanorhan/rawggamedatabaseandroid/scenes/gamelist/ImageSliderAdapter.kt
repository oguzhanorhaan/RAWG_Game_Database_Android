package com.oguzhanorhan.rawggamedatabaseandroid.scenes.gamelist

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import androidx.viewpager.widget.PagerAdapter
import com.oguzhanorhan.rawggamedatabaseandroid.R
import java.util.*

internal class ImageSliderAdapter(
    var context: Context?,
    var images: IntArray?
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
        images?.get(position)?.let { imageView.setImageResource(it) }
        Objects.requireNonNull(container).addView(itemView)
        return itemView
    }

    override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
        container.removeView(`object` as LinearLayout)
    }
}
