package com.jinhanexample.viewPager.survey.start

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.google.gson.Gson
import com.jinhanexample.R
import com.jinhanexample.databinding.FragmentSurveyStartBinding
import com.jinhanexample.viewPager.survey.model.SurveyListModel
import com.jinhanexample.viewPager.survey.model.SurveyType

class SurveyStartFragment : Fragment(), View.OnClickListener {

    lateinit var b: FragmentSurveyStartBinding
    lateinit var mContext: Context


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {

        b = FragmentSurveyStartBinding.inflate(layoutInflater)
        mContext = requireContext()
        setupListener()

        return b.root
    }


    fun setupListener() {

        b.btnSurvey1.setOnClickListener(this)
        b.btnSurvey2.setOnClickListener(this)
        b.btnSurvey3.setOnClickListener(this)
        b.btnSurvey4.setOnClickListener(this)

    }


    override fun onClick(v: View?) {

        var surveyType: SurveyType? = null

        when (v!!.id) {
            R.id.btnSurvey1 -> {
                surveyType = SurveyType.QUESTION_ONE
                Log.d(TAG, "onClick: one : ${getJsonDataFromAsset(mContext, "survey.json")}")
            }
            R.id.btnSurvey2 -> {
                surveyType = SurveyType.QUESTION_TWO
            }
            R.id.btnSurvey3 -> {
                surveyType = SurveyType.QUESTION_THREE
            }
            R.id.btnSurvey4 -> {
                surveyType = SurveyType.QUESTION_FOUR
            }

        }
        val bundle = Bundle()
        Log.d(TAG, "onClick: 모델 확인 : ${getModel(surveyType!!)}")
        bundle.putString("surveyType", surveyType.toString())
        bundle.putParcelable("surveyData", getModel(surveyType!!))
        findNavController().navigate(R.id.action_surveyStartFragment_to_surveyQuestionFragment,
            bundle)
    }


    fun getModel(surveyType: SurveyType): SurveyListModel? {

        var surveyListModel: SurveyListModel? = null
        var jsonFileString: String? = null
        when (surveyType) {
            SurveyType.QUESTION_ONE -> {
                jsonFileString = getJsonDataFromAsset(mContext, "survey.json")
                val gson = Gson()
                surveyListModel = gson.fromJson(jsonFileString, SurveyListModel::class.java)
            }
            SurveyType.QUESTION_TWO -> {

            }
            SurveyType.QUESTION_THREE -> {

            }
            SurveyType.QUESTION_FOUR -> {

            }

        }

        return surveyListModel
    }


    private fun getJsonDataFromAsset(context: Context, fileName: String): String? {
        val jsonString: String
        try {
            jsonString = context.assets.open(fileName).bufferedReader().use {
                it.readText()
            }
        } catch (e: Exception) {
            Log.d(TAG, "getJsonDataFromAsset: $e ")
            return null
        }

        return jsonString

    }


    companion object {
        private const val TAG = "SurveyStartFragment"
    }


}