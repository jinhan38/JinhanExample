package com.jinhanexample.customChart.fiveDayChart.viewPager

import android.os.Bundle
import android.os.Handler
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.jinhanexample.R
import kotlinx.android.synthetic.main.fragment_progress_first.*

class ProgressFragmentFirst : Fragment() {

    var emotionScoreDataArrayList = ArrayList<EmotionScoreData>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val v = inflater.inflate(R.layout.fragment_progress_first, container, false)


        return view
    }


    fun setData(emotionScoreDataArrayList: ArrayList<EmotionScoreData>) {
        this.emotionScoreDataArrayList = emotionScoreDataArrayList
        setProgressVisibility(emotionScoreDataArrayList.size)
    }

    private fun setProgressVisibility(dataCount: Int) {
        
        fmScoreLayoutWrap1.visibility = View.GONE
        fmScoreLayoutWrap2.visibility = View.GONE
        fmScoreLayoutWrap3.visibility = View.GONE
        fmScoreLayoutWrap4.visibility = View.GONE

        when (dataCount) {
            1 -> fmScoreLayoutWrap1.visibility = View.VISIBLE
            2 -> {
                fmScoreLayoutWrap1.visibility = View.VISIBLE
                fmScoreLayoutWrap2.visibility = View.VISIBLE
            }
            3 -> {
                fmScoreLayoutWrap1.visibility = View.VISIBLE
                fmScoreLayoutWrap2.visibility = View.VISIBLE
                fmScoreLayoutWrap3.visibility = View.VISIBLE
            }
            4 -> {
                fmScoreLayoutWrap1.visibility = View.VISIBLE
                fmScoreLayoutWrap2.visibility = View.VISIBLE
                fmScoreLayoutWrap3.visibility = View.VISIBLE
                fmScoreLayoutWrap4.visibility = View.VISIBLE
            }
        }
    }


}