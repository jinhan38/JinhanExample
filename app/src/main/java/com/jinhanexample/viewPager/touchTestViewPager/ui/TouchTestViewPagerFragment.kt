package com.jinhanexample.viewPager.touchTestViewPager.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.jinhanexample.databinding.FragmentTouchTestViewPagerBinding
import com.jinhanexample.viewPager.touchTestViewPager.adapter.TouchTestRecyclerViewAdapter


class TouchTestViewPagerFragment : Fragment() {




    lateinit var b: FragmentTouchTestViewPagerBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {

        b = FragmentTouchTestViewPagerBinding.inflate(layoutInflater)

        b.recyclerView.apply{
            layoutManager = LinearLayoutManager(requireContext(),
                LinearLayoutManager.VERTICAL,
                false)
            adapter = TouchTestRecyclerViewAdapter(childFragmentManager)
        }

        return b.root
    }

}