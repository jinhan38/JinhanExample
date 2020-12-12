package com.jinhanexample.mpchart.calendar

import com.google.gson.annotations.Until
import java.util.*
import kotlin.collections.ArrayList

class DayInfo(val date: Int) {
    lateinit var arrScore: ArrayList<ScoreInfo>
    var x: Float = 0.0f
    var y: Float = 0.0f


    fun addScore(scoreInfo: ScoreInfo) {
        if (arrScore.isNullOrEmpty()) {
            arrScore = ArrayList()
        }
        arrScore.add(scoreInfo)
    }

    //TODO::데이터가 3개가 안되는 경우 고려 필요
    fun getTop3AverageScore(): Float {
        var top3 = getTop3()
        if (top3.size <= 0) return 0f

        var sum = 0f
        for (i in 0..top3.size) sum += top3[i].score

        return sum / top3.size
    }

    fun getTop3(): ArrayList<ScoreInfo> {
        val top3 = ArrayList<ScoreInfo>()

        if (!arrScore.isNullOrEmpty()) {
            arrScore.sortWith { p0, p1 -> p0!!.score.compareTo(p1!!.score) * -1 }
        }

        for (i in 0 until 3) if (arrScore.size > i) top3.add(arrScore[i])

        return top3
    }


    fun hasScoreData(): Boolean {
        return !((arrScore.isNullOrEmpty()) or (arrScore.size == 0))
    }


}