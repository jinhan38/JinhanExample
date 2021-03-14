package com.jinhanexample.viewPager.survey.result

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.jinhanexample.databinding.FragmentSurveyResultBinding
import com.jinhanexample.viewPager.survey.model.SurveyListModel

class SurveyResultFragment : Fragment() {


    lateinit var b: FragmentSurveyResultBinding
    var surveyType = ""
    var scoreHashMap = HashMap<Int, Int>()
    lateinit var surveyListModel: SurveyListModel
    var totalScore = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            surveyType = requireArguments()["surveyType"] as String
            scoreHashMap = requireArguments()["resultValue"] as HashMap<Int, Int>
            surveyListModel = requireArguments()["surveyData"] as SurveyListModel
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {


        b = FragmentSurveyResultBinding.inflate(layoutInflater)

        b.tvSurveyType.text = "question type : $surveyType"

        for (i in 0 until scoreHashMap.size) {
            if (surveyListModel.list[i].answerList[scoreHashMap[i + 1]!!].answer) {
                totalScore += 10
            }
        }

        val thread = Thread {
            Handler(Looper.getMainLooper()).post {
                b.tvResultScore.text = "Score : $totalScore"
            }
        }

        thread.start()

        return b.root
    }
}