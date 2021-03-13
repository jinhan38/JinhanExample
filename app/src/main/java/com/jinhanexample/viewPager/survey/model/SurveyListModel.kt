package com.jinhanexample.viewPager.survey.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize


@Parcelize
data class SurveyListModel(

    var totalScore: Int,
    var type: Int,
    var list: ArrayList<List>
) : Parcelable {

    @Parcelize
    data class List(
        var number: Int,
        var question: String,
        var answerList: ArrayList<AnswerList>
    ) : Parcelable {

        @Parcelize
        data class AnswerList(
            var answer: Boolean,
            var item: String,
        ) : Parcelable
    }
}