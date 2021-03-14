package com.jinhanexample.viewPager.survey.question

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.NavOptions
import androidx.navigation.Navigation
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import com.jinhanexample.R
import com.jinhanexample.databinding.FragmentSurveyQuestionBinding
import com.jinhanexample.eventBus.event.GlobalBus
import com.jinhanexample.viewPager.survey.model.SurveyChoiceModel
import com.jinhanexample.viewPager.survey.model.SurveyListModel
import com.squareup.otto.Subscribe
import kotlin.collections.HashMap
import kotlin.collections.set


class SurveyQuestionFragment : Fragment(), View.OnClickListener {


    lateinit var b: FragmentSurveyQuestionBinding
    lateinit var mContext: Context
    var surveyType = ""
    lateinit var surveyListModel: SurveyListModel
    var currentPage = 0
    var scoreHashMap = HashMap<Int, Int>()
    lateinit var surveyChoiceModel: SurveyChoiceModel
    private lateinit var surveyViewPager2Adapter: SurveyViewPager2Adapter


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        GlobalBus.bus!!.register(this)
        arguments.let {
            surveyType = requireArguments()["surveyType"] as String
            surveyListModel = requireArguments()["surveyData"] as SurveyListModel

        }

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {

        b = FragmentSurveyQuestionBinding.inflate(layoutInflater)

        mContext = requireContext()


        surveyViewPager2Adapter = SurveyViewPager2Adapter(requireActivity(), surveyListModel)

        b.vp2SurveyQuestion.adapter = surveyViewPager2Adapter

        b.vp2SurveyQuestion.isUserInputEnabled = false

        setupListener()

        return b.root
    }


    fun setupListener() {
        b.btnBack.setOnClickListener(this)
        b.btnNext.setOnClickListener(this)
    }


    private fun changeViewPagerCurrentPage(value: String) {
        if (value == "plus") {
            currentPage++
        } else {
            if (currentPage > 0) {
                currentPage--
                surveyViewPager2Adapter.uiUpdate(currentPage)
            }
        }
        b.vp2SurveyQuestion.currentItem = currentPage
    }


    override fun onClick(v: View?) {

        when (v!!.id) {

            R.id.btnBack -> {
                if (scoreHashMap.size != 0) {
                    if (scoreHashMap.containsKey(b.vp2SurveyQuestion.currentItem)) {
                        scoreHashMap.remove(b.vp2SurveyQuestion.currentItem)
                    }
                    changeViewPagerCurrentPage("minus")

                } else {
                    Toast.makeText(mContext, "It is firstPage", Toast.LENGTH_SHORT).show()
                }
            }

            R.id.btnNext -> {

                when {

                    scoreHashMap.size == surveyListModel.list.size -> {
                        val bundle = Bundle()
                        bundle.putString("surveyType", surveyType)
                        bundle.putSerializable("resultValue", scoreHashMap)
                        bundle.putParcelable("surveyData", surveyListModel)

                        findNavController().navigate(R.id.action_surveyQuestionFragment_to_surveyResultFragment,
                            bundle)

                    }

                    b.vp2SurveyQuestion.currentItem == scoreHashMap.size - 1 -> {
                        changeViewPagerCurrentPage("plus")
                    }

                    else -> {
                        Toast.makeText(requireActivity(),
                            "Please, choice answer",
                            Toast.LENGTH_SHORT).show()
                    }
                }

            }
        }
    }


    @Subscribe
    fun changeViewPagerPage(surveyChoiceModel: SurveyChoiceModel) {
        this.surveyChoiceModel = surveyChoiceModel

        scoreHashMap[surveyChoiceModel.questionNumber] = surveyChoiceModel.clickedNumber

    }


    override fun onDestroy() {
        GlobalBus.bus!!.unregister(this)
        super.onDestroy()
    }


}