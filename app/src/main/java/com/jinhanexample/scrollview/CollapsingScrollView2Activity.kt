package com.jinhanexample.scrollview

import android.annotation.SuppressLint
import android.app.Activity
import android.content.res.Configuration
import android.graphics.Color
import android.graphics.PorterDuff
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import android.widget.Toolbar
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.databinding.DataBindingUtil
import com.google.android.material.appbar.AppBarLayout.OnOffsetChangedListener
import com.jinhanexample.R
import com.jinhanexample.databinding.ActivityCollapsingScrollView2Binding

class CollapsingScrollView2Activity : AppCompatActivity() {

    lateinit var b: ActivityCollapsingScrollView2Binding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        b = DataBindingUtil.setContentView(this, R.layout.activity_collapsing_scroll_view2)

        setToolbar()
//        extendStatusBarLayout(this)
//        statusBarControl()
//        setStatusBarColor(this, Color.WHITE)
    }


    /**
     * 상태바의 높이 구해서 그만큼 marginTop 설정
     */
    private fun statusBarControl() {

        var statusBarHeight = 0
        val resourceId = resources.getIdentifier("status_bar_height", "dimen", "android")
        if (resourceId > 0) {
            statusBarHeight = resources.getDimensionPixelSize(resourceId)
        }
        //StatusBar height 만큼 top margin
        val params = Toolbar.LayoutParams(
            ViewGroup.LayoutParams.MATCH_PARENT,
            ViewGroup.LayoutParams.WRAP_CONTENT
        )
        params.setMargins(0, statusBarHeight, 0, 0)
//        b.toolbar.layoutParams = params
        val decorView = window.decorView
        decorView.systemUiVisibility =
            decorView.systemUiVisibility and View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR.inv()
    }


    /**
     * 상태바 영역까지 fullScrenn
     */
    @SuppressLint("ObsoleteSdkInt")
    fun extendStatusBarLayout(activity: Activity) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Log.d(TAG, "extendStatusBarLayout: 첫번째")
            activity.window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
            activity.window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
            activity.window.statusBarColor = Color.TRANSPARENT
        } else {
            Log.d(TAG, "extendStatusBarLayout: 두번째")
            activity.window.addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
        }
        activity.window.decorView.systemUiVisibility =
            View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN or View.SYSTEM_UI_FLAG_LAYOUT_STABLE or View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR
    }

    private fun setToolbar() {
        setSupportActionBar(b.toolbar)
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        b.toolbar.setNavigationOnClickListener { onBackPressed() }

        b.toolbar.navigationIcon?.setColorFilter(
            resources.getColor(R.color.white),
            PorterDuff.Mode.SRC_ATOP
        )

        b.collapseToolbar.title = "고양이"
        b.collapseToolbar.setCollapsedTitleTextColor(resources.getColor(R.color.black))
        b.collapseToolbar.setExpandedTitleColor(resources.getColor(R.color.white))

        b.appBar.addOnOffsetChangedListener(OnOffsetChangedListener { appBarLayout, verticalOffset ->
            if (b.collapseToolbar.height + verticalOffset < 2 * ViewCompat.getMinimumHeight(b.collapseToolbar)) {
                b.toolbar.navigationIcon!!.setColorFilter(
                    resources.getColor(R.color.black),
                    PorterDuff.Mode.SRC_ATOP
                )
            } else {
                b.toolbar.navigationIcon!!.setColorFilter(
                    resources.getColor(R.color.white),
                    PorterDuff.Mode.SRC_ATOP
                )
            }
        })
    }

    fun setStatusBarColor(activity: Activity, color: Int) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            activity.window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
            activity.window.statusBarColor = color
            activity.window.decorView.systemUiVisibility = Configuration.UI_MODE_NIGHT_MASK
        }
    }

    companion object{
        private const val TAG = "CollapsingScrollView2Ac"
    }
}