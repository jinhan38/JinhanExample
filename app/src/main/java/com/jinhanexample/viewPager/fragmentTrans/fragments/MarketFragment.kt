package com.jinhanexample.viewPager.fragmentTrans.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.jinhanexample.R

class MarketFragment : Fragment() {


    companion object {

        private const val TAG = "GameFragment"

        @Volatile
        private var instance: MarketFragment? = null

        fun getInstance(): MarketFragment =
            instance ?: synchronized(this) {
                instance ?: MarketFragment().also {
                    instance = it
                }
            }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_market, container, false)
    }

}