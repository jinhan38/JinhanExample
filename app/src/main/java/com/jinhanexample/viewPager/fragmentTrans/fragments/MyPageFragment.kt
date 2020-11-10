package com.jinhanexample.viewPager.fragmentTrans.fragments


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.jinhanexample.R

class MyPageFragment : Fragment() {


    companion object {

        private const val TAG = "GameFragment"

        @Volatile
        private var instance: MyPageFragment? = null

        fun getInstance(): MyPageFragment =
            instance ?: synchronized(this) {
                instance ?: MyPageFragment().also {
                    instance = it
                }
            }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_mypage, container, false)
    }

}