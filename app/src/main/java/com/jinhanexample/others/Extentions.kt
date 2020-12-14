package com.jinhanexample.others

import android.animation.ObjectAnimator
import android.app.Activity
import android.content.ContentValues
import android.content.Context
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.View
import android.view.ViewGroup
import android.view.animation.Interpolator
import android.widget.EditText
import android.widget.FrameLayout
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.commit
import com.google.android.material.tabs.TabLayout
import com.jinhanexample.R

object Extentions {

    /**
     * 부모뷰는 frameLayout
     */
    fun View.setLayoutParamsMargin(leftMargin: Int, topMargin: Int) {

        val params = this.layoutParams as FrameLayout.LayoutParams
        params.leftMargin = leftMargin
        params.topMargin = topMargin
        this.layoutParams = params

    }

    fun EditText.onMyTextChanged(completion: (Editable?) -> Unit) {
        this.addTextChangedListener(object : TextWatcher {

            override fun afterTextChanged(editable: Editable?) {
                completion(editable)
            }

            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

            }


        })

    }

    fun Fragment.replaceFragment(containerView: Int, activity: FragmentActivity) {

        var fragment = this
        Log.d(
            ContentValues.TAG,
            "replaceFragment: 진입 fragment : $fragment, containerView : $containerView"
        )
        activity.supportFragmentManager?.commit {
            setCustomAnimations(
                R.anim.slide_in,
                R.anim.fade_out,
                R.anim.fade_in,
                R.anim.slide_out
            )
            replace(containerView, fragment)
            addToBackStack(null)
        }

    }

    fun TabLayout.tabLayoutController(completion: (tab: TabLayout.Tab?) -> Unit) {
        this.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab?) {
                Log.d(ContentValues.TAG, "onTabSelected: completion")
                completion(tab)
            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {


            }

            override fun onTabReselected(tab: TabLayout.Tab?) {

            }

        })

    }




    fun View.setObjectAnimator(type : String, value : Float, duration : Int, interpolator : Interpolator){
        val anim = ObjectAnimator.ofFloat(this, type, value)
        anim.duration = duration.toLong()
        anim.interpolator = interpolator
        anim.start()
    }
}