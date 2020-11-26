package com.jinhanexample.animation

import android.content.Context
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.viewpager.widget.PagerAdapter
import com.jinhanexample.R

class ImageAdapter(val context: Context) : PagerAdapter() {


    private var mImageIds = arrayOf(R.drawable.cat2, R.drawable.cat3, R.drawable.winter)


    override fun getCount(): Int {
        return mImageIds.size
    }

    override fun isViewFromObject(view: View, `object`: Any): Boolean {
        return view == `object`
    }

    override fun instantiateItem(container: ViewGroup, position: Int): Any {
        var imageView = ImageView(context)
        imageView.apply {
            scaleType = ImageView.ScaleType.CENTER_CROP
            setImageResource(mImageIds[position])
        }
        container.addView(imageView, 0)
        return imageView
    }

    override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
        container.removeView(`object` as ImageView)
    }
}