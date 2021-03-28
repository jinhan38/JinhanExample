package com.jinhanexample.viewPager.touchTestViewPager.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.jinhanexample.databinding.TouchTestChildPagerFragmentBinding

class TouchTestChildPagerFragment : Fragment() {

    lateinit var b : TouchTestChildPagerFragmentBinding
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        b = TouchTestChildPagerFragmentBinding.inflate(layoutInflater)

        return b.root
    }


}