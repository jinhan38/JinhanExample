package com.jinhanexample.viewPager.survey.question

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.jinhanexample.viewPager.survey.model.SurveyListModel

class SurveyViewPager2Adapter : FragmentStateAdapter {

    private var surveyListModel: SurveyListModel
    var fragmentArray = ArrayList<SurveyViewPager2Fragment>()

    constructor(
        fragmentActivity: FragmentActivity,
        surveyListModel: SurveyListModel,
    ) : super(fragmentActivity) {
        this.surveyListModel = surveyListModel
        for (i in 0 until surveyListModel.list.size) {
            fragmentArray.add(SurveyViewPager2Fragment().newInstance(surveyListModel.list[i]))
        }
    }


    override fun getItemCount(): Int {
        return surveyListModel.list.size
    }

    override fun createFragment(position: Int): Fragment {
        return fragmentArray[position]
    }

    fun uiUpdate(pageNum: Int) {
        fragmentArray[pageNum].surveyRecyclerViewAdapter.clickedPosition = -1
        fragmentArray[pageNum].surveyRecyclerViewAdapter.notifyDataSetChanged()

    }
}