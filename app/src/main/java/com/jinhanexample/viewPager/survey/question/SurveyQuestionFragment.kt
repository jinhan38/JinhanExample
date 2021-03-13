package com.jinhanexample.viewPager.survey.question

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.jinhanexample.R
import com.jinhanexample.databinding.FragmentSurveyQuestionBinding
import com.jinhanexample.eventBus.event.GlobalBus
import com.jinhanexample.viewPager.survey.model.SurveyListModel
import com.squareup.otto.Subscribe

class SurveyQuestionFragment : Fragment(), ControlViewPagerPage, View.OnClickListener {

    companion object {
        private const val TAG = "SurveyQuestionFragment"
    }

    lateinit var b: FragmentSurveyQuestionBinding
    var surveyType = ""
    lateinit var surveyListModel: SurveyListModel
    var currentPage = 0


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        GlobalBus.bus!!.register(this)
        Log.d(TAG, "onDestroy: 글로벌버스 등록")
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

//        Toast.makeText(requireContext(), "전달받은 번호 : $surveyType", Toast.LENGTH_SHORT).show()
        Log.d(TAG, "onCreateView: 전달받은 데이터 : ${surveyListModel.list.size}")
        val surveyViewPager2Adapter =
            SurveyViewPager2Adapter(requireActivity(), surveyListModel, this)

        b.vp2SurveyQuestion.adapter = surveyViewPager2Adapter

        setupListener()

        return b.root
    }


    fun setupListener() {
        b.btnBack.setOnClickListener(this)
        b.btnNext.setOnClickListener(this)
    }

    override fun onChangeViewPagerPage(num: Int) {
        Toast.makeText(requireContext(), "클릭한 항목 : $num", Toast.LENGTH_SHORT).show()
        // 리사이클러뷰 클릭리스너 이용해서 페이지 전환 만들기
    }


    private fun changeViewPagerCurrentPage(value: String) {
        if (value == "plus") {
            currentPage++
        } else {
            currentPage--
        }
        b.vp2SurveyQuestion.currentItem = currentPage
    }


    override fun onClick(v: View?) {

        when (v!!.id) {

            R.id.btnBack -> {
                if (currentPage <= 0) {
                    Toast.makeText(requireContext(), "It is firstPage", Toast.LENGTH_SHORT).show()
                } else {
                    changeViewPagerCurrentPage("minus")
                }
            }

            R.id.btnNext -> {
                if (currentPage >= surveyListModel.list.size - 1) {
                    Toast.makeText(requireContext(), "It is lastPage", Toast.LENGTH_SHORT).show()
                } else {
                    changeViewPagerCurrentPage("plus")
                }
            }
        }
    }


    // TODO:: 글로벌버스 작동 안함
    // 클릭한 포지션 저장할 리스너 필요
    @Subscribe
    fun changeViewPagerPage(position: Int) {
        Log.d(TAG, "changeViewPagerPage: 진입 : $position")
        when (position) {

            0 -> changeViewPagerCurrentPage("plus")
            1 -> changeViewPagerCurrentPage("plus")
            2 -> {
                Toast.makeText(requireContext(), "last page", Toast.LENGTH_SHORT).show()
            }
        }

    }

    override fun onDestroy() {
        Log.d(TAG, "onDestroy: 글로벌버스 삭제")
        GlobalBus.bus!!.unregister(this)
        super.onDestroy()
    }
}