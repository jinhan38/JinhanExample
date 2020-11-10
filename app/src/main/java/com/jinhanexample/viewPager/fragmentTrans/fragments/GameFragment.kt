package com.jinhanexample.viewPager.fragmentTrans.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.jinhanexample.R

class GameFragment : Fragment() {


    companion object {

        private const val TAG = "GameFragment"

        @Volatile
        private var instance: GameFragment? = null

        fun getInstance(): GameFragment =
            instance ?: synchronized(this) {
                instance ?: GameFragment().also {
                    instance = it
                }
            }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

//        if (arguments != null) {
//            val args = arguments
//            // MainActivity에서 받아온 Resource를 ImageView에 셋팅
//            view.setImageResource(args!!.getInt("imgRes"))
//        }
        
        return inflater.inflate(R.layout.fragment_game, container, false)
    }

}