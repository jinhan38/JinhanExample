package com.jinhanexample.viewPager.survey.question

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.jinhanexample.viewPager.survey.model.SurveyListModel

class SurveyViewPager2Adapter : FragmentStateAdapter {

    var surveyListModel: SurveyListModel

    constructor(
        fragmentActivity: FragmentActivity,
        surveyListModel: SurveyListModel,
        controlViewPagerPage: ControlViewPagerPage,
    ) : super(fragmentActivity) {
        this.surveyListModel = surveyListModel
    }


    override fun getItemCount(): Int {
        return surveyListModel.list.size
    }

    override fun createFragment(position: Int): Fragment {
//        var fragment = SurveyViewPager2Fragment()
//        fragment.arguments?.apply {
//            putParcelable("data", surveyListModel.list[position])
//        }
        return SurveyViewPager2Fragment().newInstance(surveyListModel.list[position])
    }
}