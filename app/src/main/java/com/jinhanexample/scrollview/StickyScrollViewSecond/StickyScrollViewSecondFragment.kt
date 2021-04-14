package com.jinhanexample.scrollview.StickyScrollViewSecond

import android.animation.Animator
import android.animation.AnimatorListenerAdapter
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AccelerateInterpolator
import android.view.animation.LinearInterpolator
import androidx.fragment.app.Fragment
import com.google.android.material.appbar.AppBarLayout
import com.jinhanexample.Common
import com.jinhanexample.animation.animBuilder.ObjectAnimationBuilder
import com.jinhanexample.databinding.FragmentStickyScrollViewSecondBinding
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch


class StickyScrollViewSecondFragment : Fragment() {

    companion object {
        private const val TAG = "StickyScrollViewSecondF"
    }

    var isShowing = true


    lateinit var b: FragmentStickyScrollViewSecondBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        // Inflate the layout for this fragment
        b = FragmentStickyScrollViewSecondBinding.inflate(layoutInflater)

        b.mainScrollView.setOnScrollChangeListener { view, i, i2, i3, i4 ->
            Log.d(TAG, "onCreate: i2 : $i2")
            Log.d(TAG, "onCreate: headerview : ${b.headerView.top}")
        }

        b.mainScrollView.run {
            header = b.headerView
            stickListener = { _ ->
//                isShowing = true
//                toolbarAnimateShow()
//                b.toolbar.visibility = View.GONE
//                b.toolbar.setExpanded(false, true)
                Log.d("LOGGER_TAG", "stickListener")

            }
            freeListener = { _ ->
//                isShowing = false
//                toolbarAnimateShow()
//                b.toolbar.visibility = View.VISIBLE
//                b.toolbar.setExpanded(true, true)
                Log.d("LOGGER_TAG", "freeListener")
            }
        }

//        b.appbarLayout.addOnOffsetChangedListener(object : AppBarLayout.OnOffsetChangedListener {
//            override fun onOffsetChanged(appBarLayout: AppBarLayout?, verticalOffset: Int) {
//                Log.d("LOGGER_TAG",
//                    "addOnOffsetChangedListener $verticalOffset, ${appBarLayout?.height}")
//            }
//
//        })

        var toolbarY = 0f
        val headerHeight = b.headerView.height
        val toolbarHeight = Common.getDP(requireContext(), 50)
        b.mainScrollView.setOnScrollChangeListener { view, i, i2, i3, i4 ->
            Log.d("스크롤뷰",
                "tvSticky : ${b.headerView.y}, toolbarHeight : $toolbarHeight, i : $i, i2 : $i2, i3 : $i3, i4 : $i4")
//            if (headerHeight < i2){
            toolbarY -= 1f


//            b.toolbar.animate()
//                .translationY(toolbarY)
//                .setInterpolator(LinearInterpolator())
//                .setDuration(1)
//                .setListener(object : AnimatorListenerAdapter() {
//                    override fun onAnimationStart(animation: Animator?) {
//                        b.toolbar.visibility = View.VISIBLE
//                        isShowing = false
//                    }
//                })


                ObjectAnimationBuilder.Builder(b.toolbar, 1, "y", toolbarY)
                    .setInterpolator(AccelerateInterpolator()).build()

                ObjectAnimationBuilder.Builder(b.mainScrollView, 1, "y", toolbarHeight + toolbarY)
                    .setInterpolator(AccelerateInterpolator()).build()

        }


        return b.root
    }

    private fun toolbarAnimateShow() {
        if (!isShowing) {
            isShowing = true
            b.toolbar.animate()
                .translationY(0f)
                .setInterpolator(LinearInterpolator())
                .setDuration(180)
                .setListener(object : AnimatorListenerAdapter() {
                    override fun onAnimationStart(animation: Animator?) {
                        b.toolbar.visibility = View.VISIBLE
                        isShowing = false
                    }
                })
        } else {
            isShowing = false
            b.toolbar.animate()
                .translationY((-b.toolbar.height).toFloat())
                .setInterpolator(LinearInterpolator())
                .setDuration(180)
                .setListener(object : AnimatorListenerAdapter() {
                    override fun onAnimationEnd(animation: Animator?) {
                        b.toolbar.visibility = View.GONE
                        isShowing = false
                    }
                })
        }
    }

//    private fun toolbarAnimateHide() {
//        if (!isHidding) {
//            isHidding = true
//            llTop.animate()
//                .translationY(-llTop.getHeight())
//                .setInterpolator(LinearInterpolator())
//                .setDuration(180)
//                .setListener(object : AnimatorListenerAdapter() {
//                    override fun onAnimationEnd(animation: Animator?) {
//                        llTop.setVisibility(View.GONE)
//                        isHidding = false
//                    }
//                })
//        }
//    }

}