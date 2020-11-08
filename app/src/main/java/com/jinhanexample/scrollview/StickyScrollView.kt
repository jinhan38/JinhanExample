package com.jinhanexample.scrollview

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.databinding.DataBindingUtil
import com.jinhanexample.R
import com.jinhanexample.databinding.ActivityStickyScrollViewBinding

class StickyScrollView : AppCompatActivity() {

    companion object{
        private const val TAG = "StickyScrollView"
    }

    lateinit var b : ActivityStickyScrollViewBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        b = DataBindingUtil.setContentView(this, R.layout.activity_sticky_scroll_view)

        b.mainScrollView.setOnScrollChangeListener { view, i, i2, i3, i4 ->
            Log.d(TAG, "onCreate: i2 : $i2")
            Log.d(TAG, "onCreate: headerview : ${b.headerView.top}")
        }

        b.mainScrollView.run {
            header = b.headerView
            stickListener = { _ ->
                Log.d("LOGGER_TAG", "stickListener")
            }
            freeListener = { _ ->
                Log.d("LOGGER_TAG", "freeListener")
            }
        }
    }
}
